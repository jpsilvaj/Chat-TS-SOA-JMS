package br.edu.ifce.chattssoajms.controller;

import br.edu.ifce.chattssoajms.bean.Message;
import br.edu.ifce.chattssoajms.bean.User;
import br.edu.ifce.chattssoajms.view.ChatClientView;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ChatClientController{
    public static ChatClientView chatClientView;
    public static JavaSpace javaSpace;

    public static void main(String[] args){
        startupSpaceConnection();
    }

    private static void startupSpaceConnection() {
        System.out.println("Procurando pelo servico JavaSpace...");
        Lookup finder = new Lookup(JavaSpace.class);
        javaSpace = (JavaSpace) finder.getService();
        if (javaSpace == null) {
            System.out.println("O servico JavaSpace nao foi encontrado. Encerrando a aplicação");
            System.exit(-1);
        }
        System.out.println("O servico JavaSpace foi encontrado. E a aplicação está em execução");
        Message msg = new Message();

        msg.receiver = "JP";
        msg.sender = "MANU";
        msg.date = Calendar.getInstance().getTime();
        msg.message="TESTE";
        try {
            javaSpace.write(msg, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Message message) {

    }

    public static Message receiveMessage() {
        return null;
    }
}
