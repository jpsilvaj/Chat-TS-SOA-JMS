package br.edu.ifce.chat.webserver.controller;

import javax.xml.ws.Endpoint;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class PublishTopicMessageWebServerController {

    public static void main(String [] args){
        Endpoint.publish("http://localhost:9999/MessageWebServerControllerImpl", new MessageWebServerControllerImpl());
    }
}
