package br.edu.ifce.chattssoajms.controller;

import br.edu.ifce.chattssoajms.utils.Constants;
import br.edu.ifce.chattssoajms.utils.TopicSessionHelper;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.ArrayList;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class MediatorController {
    private static TopicSessionHelper topicSessionHelper;
    private static ArrayList<String> listOfMessageIllegal;
    public static void main(String []args){
        try {
            topicSessionHelper = new TopicSessionHelper();
            topicSessionHelper.subscribeMessageListener(new SubscribeTopicToMessageIllegal(), Constants.TOPIC_DEFAULT);
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
