package br.edu.ifce.chat.client.controller;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Timer;

import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.bean.User;
import br.edu.ifce.chat.client.handler.ReceiveMessageThread;
import br.edu.ifce.chat.client.view.ChatClientView;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ChatClientController{
    private static ChatClientView chatClientView;
    private static JavaSpace space;
    private static Timer timerReceiveMessage;
    private static boolean isConnected=false;
    private static String username="jp";
    
    public static void main(String[] args){
    	chatClientView = new ChatClientView();
        startSpaceConnection();
        try {
			sendMessage("teste", "jp");
			System.out.println("Mensgem enviada");
			Message message = new Message();
			message.receiver = "jp";
			Message messageReturned = (Message) space.take(message, null, 60*1000);
			System.out.println("Mensagem recebida: " + message.message + " UserReme: " + message.sender + " UserDest: " + message.receiver + " data: " + message.date.parse("DD/MM/YYYY"));
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException | UnusableEntryException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void startSpaceConnection() {
    	if(!isConnected){
	    	try {
	    		User user = new User();
	    		user.username = username;
	            System.out.println("Searching JavaSpace service...");
	            Lookup finder = new Lookup(JavaSpace.class);
	            space = (JavaSpace) finder.getService();
	            if (space == null) {
	                    System.out.println("JavaSpace service cannot found. Finishing...");
	                    System.exit(-1);
	            } 
	            System.out.println("JavaSpace service has been found.");
//	            space.write(user, null, Long.MAX_VALUE);
//	            timerReceiveMessage = new Timer();
//	        	timerReceiveMessage.schedule(new ReceiveMessageThread(username, space), 0,1*1000);
//	            //TODO:Verify if user already exist
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}

    }
    public static void closeSpaceConnection() throws RemoteException, UnusableEntryException, TransactionException, InterruptedException{
    	if(isConnected){
    		User user = new User();
    		user.username = username;
    		space.take(user, null, Long.MAX_VALUE);
    		space = null;
    		timerReceiveMessage.cancel();
    	}
    }

    public static void sendMessage(String messageText, String usernameDest) throws RemoteException, TransactionException {
    	Message message = new Message();
    	message.message = messageText;
    	message.sender = username;
    	message.receiver = usernameDest;
    	message.date = Calendar.getInstance().getTime();
    	
    	space.write(message, null, Long.MAX_VALUE);
    }

    public static void receiveMessage() {
    	User user = new User();
    	user.username = username;
    }
    
    public static void verifyIfUserAlreadyExist(){
    
    }
}
