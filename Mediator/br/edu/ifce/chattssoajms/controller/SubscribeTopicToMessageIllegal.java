package br.edu.ifce.chattssoajms.controller;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class SubscribeTopicToMessageIllegal implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String textMessage = message.toString();
        MediatorController.addMessageIllegal(textMessage);
    }
}
