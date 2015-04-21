package br.edu.ifce.chattssoajms.handler;

import br.edu.ifce.chattssoajms.bean.Message;
import br.edu.ifce.chattssoajms.bean.User;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.TimerTask;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ReceiveMessageThread extends TimerTask {

    public User user;
    public JavaSpace space;

    public ReceiveMessageThread(User user, JavaSpace space){
        this.user = user;
    }

    @Override
    public void run() {
            Message template = new Message();

        Message msg = null;
        try {
            msg = (Message) space.take(template, null, 60 * 1000);
            if (msg == null) {
                System.out.println("WARN-Doesn't exist message");
            }
            space.write(msg, null, 60 * 1000);
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
