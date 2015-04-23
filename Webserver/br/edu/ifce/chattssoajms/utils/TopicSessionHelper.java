package br.edu.ifce.chattssoajms.utils;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class TopicSessionHelper {

    private TopicSession topicSession = null;
    private Context context = null;
    private TopicConnection tconnection = null;

    public TopicSessionHelper() throws JMSException, NamingException {
        getTopicSessionJMS();
    }

    private void getTopicSessionJMS() throws NamingException, JMSException {
        if (topicSession ==null){
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(properties);
            TopicConnectionFactory tfactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
            tconnection = tfactory.createTopicConnection();
            topicSession = tconnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        }
    }

    public void publishMessageInTopic(String message, String nameTopic) throws JMSException, NamingException {
        TextMessage textMessage = topicSession.createTextMessage();
        textMessage.setText(message);
        Topic dest = (Topic) context.lookup("topic1");
        TopicPublisher publisher = topicSession.createPublisher(dest);
        publisher.publish(textMessage);
        context.close();
        tconnection.close();
    }
}
