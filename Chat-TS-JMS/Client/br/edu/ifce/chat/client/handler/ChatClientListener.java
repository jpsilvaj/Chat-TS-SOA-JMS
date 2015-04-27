package br.edu.ifce.chat.client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;
import javax.swing.text.Document;

import net.jini.core.transaction.TransactionException;
import br.edu.ifce.chat.client.controller.ChatClientController;

public class ChatClientListener implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "send_message"){
            String message = ChatClientController.getChatClientView().getChatPanel().getTextBox().getText();
            if(message == null || message.trim().equals("")){
                JOptionPane.showMessageDialog(null, "Insert a quote to send");
            }
            else{
                try {
					ChatClientController.sendMessage(message);
					ChatClientController.getChatClientView().getChatPanel().getTextBox().setText("");
	                Document d = ChatClientController.getChatClientView().getChatPanel().getTextBox().getDocument();
	                ChatClientController.getChatClientView().getChatPanel().getTextBox().select(d.getLength(), d.getLength());
				} catch (RemoteException | TransactionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            }
            //TODO: Implement send message
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Implement address of the URI RMI

    }

    @Override
    public void windowClosing(WindowEvent e) {
        windowClosed(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        try{
            //ClientController.exitFromRoom();
            //TODO:implement exit
        }catch(Exception exception){
            exception.printStackTrace();
        }
        finally{
            System.exit(0);
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}