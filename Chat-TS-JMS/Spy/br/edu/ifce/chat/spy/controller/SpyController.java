package br.edu.ifce.chat.spy.controller;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import net.jini.core.entry.UnusableEntryException;
import net.jini.core.event.EventRegistration;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import net.jini.lease.LeaseRenewalManager;
import net.jini.space.JavaSpace;
import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.controller.Lookup;
import br.edu.ifce.chat.commons.utils.DateUtils;
import br.edu.ifce.chat.spy.handler.DebugChatJavaSpaceListener;
import br.edu.ifce.chat.spy.handler.NotifyMessageToChatSpyListener;
import br.edu.ifce.chat.webserver.controller.MessageWebServerController;
import br.edu.ifce.chat.webserver.controller.MessageWebServerControllerImplService;

public class SpyController {
	private static JavaSpace space;
	private static boolean isConnected=false;
	private static List<String> listOfWordsIllegal;
	private static NotifyMessageToChatSpyListener notifyListener;
	private static LeaseRenewalManager manager;
	
	private static MessageWebServerControllerImplService messageWebServerControllerImplService;
	private static MessageWebServerController messageWebServerController;
	
	public static void main(String [] args){
		listOfWordsIllegal = Arrays.asList(args);
		startSpaceConnection();
		try {
			register();
		} catch (TransactionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageWebServerControllerImplService = new MessageWebServerControllerImplService();
		messageWebServerController = messageWebServerControllerImplService.getMessageWebServerControllerImplPort();
	}
	
	public static void startSpaceConnection() {
		if(!isConnected){
			try {
				System.out.println("Searching JavaSpace service...");
				Lookup finder = new Lookup(JavaSpace.class);
				space = (JavaSpace) finder.getService();
				if (space == null) {
					System.out.println("JavaSpace service cannot found. Finishing...");
					System.exit(-1);
				} 
				System.out.println("JavaSpace service has been found.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeSpaceConnection() throws RemoteException, UnusableEntryException, TransactionException, InterruptedException{
		if(isConnected){
			//TODO:Deregistrar
		}
	}
	
	public static void register() throws RemoteException, TransactionException, IOException{
		notifyListener = new NotifyMessageToChatSpyListener();
		manager = new LeaseRenewalManager();

		Message message = new Message();
		
		EventRegistration myReg = 
				space.notify(message, null,
						notifyListener.getStub(),
						1000,
						new MarshalledObject(new Integer(12345)));

		manager.renewFor(myReg.getLease(), Lease.FOREVER,
				30000, new DebugChatJavaSpaceListener());
	}
	
	public static void messageHasBeenInsertOnJavaSpace(){
		Message template = new Message();
	    
	    try {
			Message msg = (Message) space.read(template, null, 60 * 1000);
			if(msg != null){
				verifyMessageContainsWordIllegal(msg);	
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnusableEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void verifyMessageContainsWordIllegal(Message message) {
		for(String word : listOfWordsIllegal){
			if(message.message.contains(word)){
				sendToWebserverIllegalMessage(message);
				return;
			}
		}
	}
	
	private static void sendToWebserverIllegalMessage(Message message){
		String messageTextFormated = DateUtils.getFormatedHourFromDate(message.date) + message.sender + ": " + message.message + "\n";
		messageWebServerController.registerMessageIllegal(messageTextFormated);
		System.out.println(messageTextFormated);
	}
}
