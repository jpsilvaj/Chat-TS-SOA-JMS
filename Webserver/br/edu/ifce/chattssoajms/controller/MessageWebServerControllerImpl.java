package br.edu.ifce.chattssoajms.controller;

import br.edu.ifce.chattssoajms.utils.Constants;
import br.edu.ifce.chattssoajms.utils.TopicSessionHelper;

import javax.jms.*;
import javax.jws.WebService;
import javax.naming.NamingException;

/**
 * Created by jp-desktop on 22/04/2015.
 */
@WebService(endpointInterface = "br.edu.ifce.chattssoajms.controller.MessageWebServerController")
public class MessageWebServerControllerImpl {
    private TopicSessionHelper topicSessionHelper;
    public void registerMessageIllegal(String message){
        try {
            topicSessionHelper = new TopicSessionHelper();
            topicSessionHelper.publishMessageInTopic(message, Constants.TOPIC_DEFAULT);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
