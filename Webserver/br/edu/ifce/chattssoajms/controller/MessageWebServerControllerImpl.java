package br.edu.ifce.chattssoajms.controller;

import br.edu.ifce.chattssoajms.utils.TopicSessionHelper;

import javax.jms.*;
import javax.jws.WebService;
import javax.naming.NamingException;

/**
 * Created by jp-desktop on 22/04/2015.
 */
@WebService(endpointInterface = "br.edu.ifce.chattssoajms.controller.MessageWebServerController")
public class MessageWebServerControllerImpl {

    public void registerMessageIllegal(String message){
        try {
            TopicSessionHelper topicSessionHelper = new TopicSessionHelper();
            topicSessionHelper.publishMessageInTopic(message,"topic1");
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
