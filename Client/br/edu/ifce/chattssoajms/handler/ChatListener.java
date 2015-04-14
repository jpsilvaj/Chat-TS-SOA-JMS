package br.edu.ifce.chattssoajms.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class ChatListener implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "send_message"){
          /*  String message = ClientController.getChatClient().getChatPanel().getTextBox().getText();
            if(message == null || message.trim().equals("")){
                JOptionPane.showMessageDialog(null, "Insert a quote to send");
            }
            else{
                String username = ClientController.getChatClient().getClient().getUsername();
                ClientController.sendMessageToRoom(messageSingleton.sendMessage(username, message));
                ClientController.getChatClient().getChatPanel().getTextBox().setText("");
                Document d = ClientController.getChatClient().getChatPanel().getTextBox().getDocument();
                ClientController.getChatClient().getChatPanel().getTextBox().select(d.getLength(), d.getLength());
            }*/
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
