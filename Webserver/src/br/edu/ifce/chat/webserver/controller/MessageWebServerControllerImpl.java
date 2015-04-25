package br.edu.ifce.chat.webserver.controller;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.edu.ifce.chat.commons.utils.Constants;

/**
 * Created by jp-desktop on 22/04/2015.
 */
@WebService(endpointInterface = "br.edu.ifce.chat.webserver.controller.MessageWebServerController")
public class MessageWebServerControllerImpl {
    
    public void registerMessageIllegal(String message){
        try {
        	
    	   Hashtable properties = new Hashtable();
           properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
           properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
           Context context = new InitialContext(properties);
           TopicConnectionFactory tfactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
           TopicConnection tconnection = tfactory.createTopicConnection();
           TopicSession topicSession = tconnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    	
           TextMessage textMessage = topicSession.createTextMessage();
           textMessage.setText(message);
           Topic dest = (Topic) context.lookup("topic1");
           TopicPublisher publisher = topicSession.createPublisher(dest);
           publisher.publish(textMessage);
           
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
