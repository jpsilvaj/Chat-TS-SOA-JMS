package br.edu.ifce.chat.client.handler;

import java.util.TimerTask;
import br.edu.ifce.chat.client.controller.ChatClientController;

public class ReceiveUserThread extends TimerTask{

    public ReceiveUserThread(){
    }

    @Override
    public void run() {
        ChatClientController.updateListOfUsersConnected();        
    }
}
