package br.edu.ifce.chat.mediator.controller;

import java.util.ArrayList;
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

import br.edu.ifce.chat.commons.utils.TopicSessionHelper;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class MediatorController {
    private static TopicSessionHelper topicSessionHelper;
    private static ArrayList<String> listOfMessageIllegal;
    public static void main(String []args){
        try {
        	listOfMessageIllegal = new ArrayList<String>();
        	Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            Context context = new InitialContext(properties);
            TopicConnectionFactory tfactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
            TopicConnection tconnection = tfactory.createTopicConnection();
            TopicSession topicSession = tconnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
     	
            tconnection.start();
            
            Topic dest = (Topic) context.lookup("topic1");
        	TopicSubscriber tsubscriber = topicSession.createSubscriber(dest);
        	tsubscriber.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					if(message instanceof TextMessage){
						String textMessage;
						try {
							textMessage = ((TextMessage)message).getText();
						    listOfMessageIllegal.add(textMessage);
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
					}
				}
			});
            
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void addMessageIllegal(String message){
        listOfMessageIllegal.add(message);
    }

}