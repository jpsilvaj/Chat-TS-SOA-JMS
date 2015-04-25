package br.edu.ifce.chat.spy.controller;

import br.edu.ifce.chat.webserver.controller.MessageWebServerController;
import br.edu.ifce.chat.webserver.controller.MessageWebServerControllerImplService;

/**
 * Created by jpsilvaj on 23/04/15.
 */
public class SendToWebserverIllegalMessageController {

    public static void main(String [] args){
    	MessageWebServerControllerImplService messageWebServerControllerImplService = new MessageWebServerControllerImplService();
    	MessageWebServerController messageWebServerController = messageWebServerControllerImplService.getMessageWebServerControllerImplPort();
    	messageWebServerController.registerMessageIllegal("teste");
    	//TODO:Implement the analysis in space
    }
}
