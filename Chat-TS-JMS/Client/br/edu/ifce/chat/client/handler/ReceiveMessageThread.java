package br.edu.ifce.chat.client.handler;

import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.bean.User;
import br.edu.ifce.chat.client.controller.ChatClientController;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.TimerTask;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ReceiveMessageThread extends TimerTask {

    private String username;
    private JavaSpace space;

    public ReceiveMessageThread(String username, JavaSpace space){
        this.username = username;
        this.space = space;
    }

    @Override
    public void run() {
        Message template = new Message();
        template.receiver = username;
        
        try {
			Message msg = (Message) space.take(template, null, 1 * 1000);
			ChatClientController.receiveMessage();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnusableEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
