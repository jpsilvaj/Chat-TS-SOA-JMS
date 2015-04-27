package br.edu.ifce.chat.client.controller;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import net.jini.core.entry.UnusableEntryException;
import net.jini.core.event.EventRegistration;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import net.jini.core.transaction.TransactionFactory;
import net.jini.lease.LeaseRenewalManager;
import net.jini.space.JavaSpace;
import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.bean.User;
import br.edu.ifce.chat.client.handler.DebugChatJavaSpaceListener;
import br.edu.ifce.chat.client.handler.ReceiveMessageThread;
import br.edu.ifce.chat.client.handler.ReceiveUserThread;
import br.edu.ifce.chat.client.handler.UserNotifyRemoteListener;
import br.edu.ifce.chat.client.view.ChatClientView;
import br.edu.ifce.chat.commons.utils.DateUtils;
import br.edu.ifce.chat.spy.handler.NotifyMessageToChatSpyListener;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ChatClientController{
    private static ChatClientView chatClientView;
    private static JavaSpace space;
    private static Timer timerReceiveMessage;
    private static Timer timerReceiveUser;
    private static UserNotifyRemoteListener notifyRemoteListener;
    private static LeaseRenewalManager manager;
    private static boolean isConnected=false;
    private static String usernameLogged;
    private static String usernameInComunnication;
    private static List<User> users;
    
    public static void main(String[] args){
    	startSpaceConnection();
    	setChatClientView(new ChatClientView());
    	connectToSpace();
    	register();
    	updateListOfUsersConnected();
    }

	public static void connectToSpace() {
		if(!isConnected){
			User user = new User();
			user.username = usernameLogged;
	        isConnected = true;
	        timerReceiveMessage = new Timer();
	    	timerReceiveMessage.schedule(new ReceiveMessageThread(usernameLogged, space), 0,3*1000);
	        timerReceiveUser = new Timer();
	    	timerReceiveUser.schedule(new ReceiveUserThread(), 0, 45*1000);
		}
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
    
	public static void register(){
		try {
			notifyRemoteListener = new UserNotifyRemoteListener();
			manager = new LeaseRenewalManager();
			User user = new User();
			EventRegistration myReg;
			myReg = space.notify(user, null,
					notifyRemoteListener.getStub(),
					1000,
					new MarshalledObject(new Integer(12345)));
			manager.renewFor(myReg.getLease(), Lease.FOREVER,
					30000, new DebugChatJavaSpaceListener());
		} catch (TransactionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void closeSpaceConnection() throws RemoteException, UnusableEntryException, TransactionException, InterruptedException{
    	if(isConnected){
    		User user = new User();
    		user.username = usernameLogged;
    		space.take(user, null, Long.MAX_VALUE);
    		space = null;
    		timerReceiveMessage.cancel();
    	}
    }

    public static void sendMessage(String messageText) throws RemoteException, TransactionException {
    	Message message = new Message();
    	message.message = messageText;
    	message.sender = usernameLogged;
    	message.receiver = usernameInComunnication;
    	message.date = Calendar.getInstance().getTime();
    	
    	space.write(message, null, 60*1000);
    	receiveMessage(message);
    }

    public static String getUsernameLogged() {
		return usernameLogged;
	}

	public static void setUsernameLogged(String usernameLogged) {
		ChatClientController.usernameLogged = usernameLogged;
	}

	public static String getUsernameInComunnication() {
		return usernameInComunnication;
	}

	public static void setUsernameInComunnication(String usernameInComunnication) {
		ChatClientController.usernameInComunnication = usernameInComunnication;
	}

	public static void receiveMessage(Message msg) {
    	String messageTextFormated = formatMessage(msg);
    	chatClientView.addMessageToPanel(messageTextFormated);
    }
    
    public static boolean login(String username){
    	usernameLogged = username;
    	User user = new User();
    	user.username = username;
    	
    	if(!verifyIfUsernameAlreadyExists(user)){
    	    try {
				space.write(user, null, Long.MAX_VALUE);
				return true;
			} catch (RemoteException | TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return false;
    }
    public static void setUserInCommunication(String usernameToCommunication) {
    	usernameInComunnication = usernameToCommunication;
    	chatClientView.setTitle("Chat client - Tuple Space - SOA - JMS : " + usernameLogged +" conversando com  " + usernameInComunnication);
		
	}

	public static ChatClientView getChatClientView() {
		return chatClientView;
	}

	public static void setChatClientView(ChatClientView chatClientView) {
		ChatClientController.chatClientView = chatClientView;
	}
    
    public synchronized static void updateListOfUsersConnected(){
    	users = new ArrayList<User>();
    	User userReturned = null;
    	User user = new User();
		try {
	    	do{
				userReturned = (User) space.take(user, null, 1*1000);
				if(userReturned != null && userReturned.username != null){
					if(!users.contains(userReturned))
						users.add(userReturned);
	    		}
	    	}while(userReturned != null);
	    	chatClientView.updateListOfUsers(users);
	    	for( User userToInsert : users){
	    		space.write(userToInsert, null, Long.MAX_VALUE);
	    	}
		} catch (RemoteException | UnusableEntryException
				| TransactionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private static boolean verifyIfUsernameAlreadyExists(User user){
    	//TODO:Fix the message when user already exists. Always return false
    	try {
			User userReturned = (User) space.read(user, null, 1000);
			if(userReturned == null){
				return false;
			}
		} catch (RemoteException | UnusableEntryException
				| TransactionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    private static String formatMessage(Message message){
    	String messageTextFormated = DateUtils.getFormatedHourFromDate(message.date) + message.sender + ": " + message.message + "\n";
    	return messageTextFormated;
    }
}
