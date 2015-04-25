package br.edu.ifce.chat.mediator.controller;

import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import br.edu.ifce.chat.mediator.view.MediatorView;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class MediatorController {
    
    private static MediatorView mediatorView;
    private static String historyMessage ="";
    private static boolean isConnected=false;
    private static TopicConnectionFactory tfactory;
    private static TopicConnection tconnection;
    private static TopicSession topicSession;
    private static Context context;
    
    public static void main(String []args){
        try {
        	
        	mediatorView = new MediatorView();
        	connect();
        	        
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void addMessageIllegal(String message){
        historyMessage += message + "\n";
    }
    
    public static void updateView(){
    	mediatorView.getChatPanel().setHistoryMessage(historyMessage);
    }
    
	public static void connect() throws NamingException, JMSException {
		if(!isConnected){
			Hashtable properties = new Hashtable();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
	        properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
	        context = new InitialContext(properties);
	        tfactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
	        tconnection = tfactory.createTopicConnection();
	        topicSession = tconnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	 	
	        tconnection.start();
	        
	        Topic dest = (Topic) context.lookup("topic1");
	    	TopicSubscriber tsubscriber = topicSession.createSubscriber(dest);
	    	
	    	tsubscriber.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					if(message instanceof TextMessage){
						String textMessage;
						try {
							textMessage = ((TextMessage)message).getText();
						    addMessageIllegal(textMessage);
						    updateView();
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
	    	isConnected = true;
		}
	}

	public static void disconnect() throws JMSException, NamingException {
		if(isConnected){
			tconnection.stop();
			tconnection.close();
			context.close();
	    	isConnected = false;
		}
	}

}