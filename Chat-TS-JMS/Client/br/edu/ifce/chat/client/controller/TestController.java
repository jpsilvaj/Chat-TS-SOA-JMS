package br.edu.ifce.chat.client.controller;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Timer;

import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.bean.User;
import br.edu.ifce.chat.client.handler.ReceiveMessageThread;
import br.edu.ifce.chat.client.view.ChatClientView;
import br.edu.ifce.chat.commons.utils.DateUtils;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class TestController{
    private static ChatClientView chatClientView;
    private static JavaSpace space;
    private static Timer timerReceiveMessage;
    private static boolean isConnected=false;
    private static String username;
    private static String usernameInComunnication;
    
    public static void main(String[] args){
    	startSpaceConnection();
    	
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
	            Message message = new Message();
	            message.sender = "jp";
	            message.receiver = "manu";
	            message.message = "ola mundo";
	            message.date = Calendar.getInstance().getTime();
	            space.write(message, null, 60*1000);
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

    public static void sendMessage(String messageText) throws RemoteException, TransactionException {
    	Message message = new Message();
    	message.message = messageText;
    	message.sender = username;
    	message.receiver = usernameInComunnication;
    	message.date = Calendar.getInstance().getTime();
    	
    	space.write(message, null, 60*1000);
    }

    public static void receiveMessage(Message msg) {
    	String messageTextFormated = formatMessage(msg);
    	chatClientView.addMessageToPanel(messageTextFormated);
    }
    
    
    public static boolean login(String username){
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
    public static void setUserInCommunication(String username) {
		usernameInComunnication = username;
	}

	public static ChatClientView getChatClientView() {
		return chatClientView;
	}

	public static void setChatClientView(ChatClientView chatClientView) {
		TestController.chatClientView = chatClientView;
	}
    
    private static boolean verifyIfUsernameAlreadyExists(User user){
    	
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
    	return true;
    }
    private static String formatMessage(Message message){
    	String messageTextFormated = DateUtils.getFormatedHourFromDate(message.date) + message.sender + ": " + message.message + "\n";
    	return messageTextFormated;
    }
	
}
