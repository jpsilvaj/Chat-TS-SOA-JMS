package br.edu.ifce.chat.mediator.controller;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class SubscribeTopicToMessageIllegal implements MessageListener {

    public void onMessage(Message message) {
        String textMessage = message.toString();
        MediatorController.addMessageIllegal(textMessage);
    }
}
