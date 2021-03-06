package br.edu.ifce.chat.mediator.view;

import java.awt.Container;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import br.edu.ifce.chat.commons.utils.ChatListener;
import br.edu.ifce.chat.commons.view.ChatMenuPanel;
import br.edu.ifce.chat.commons.view.ChatPanel;
import br.edu.ifce.chat.commons.view.ListOfUsersPanel;
import br.edu.ifce.chat.mediator.handler.ChatMediatorListener;
import br.edu.ifce.chat.mediator.handler.MenuListener;

/**
 * Created by jp-desktop on 22/04/2015.
 */
public class MediatorView extends JFrame{
    private static final long serialVersionUID = -7716037243160876089L;
    private ChatMenuPanel chatMenuPanel;
    private ChatPanel chatPanel = new ChatPanel(new ChatMediatorListener());
    //private ListOfUsersPanel listOfUsersPanel = new ListOfUsersPanel();

    public MediatorView(){
        super("Mediator");
        this.setSize(1080,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setNameClient();
        this.addPanels();
        this.chatPanel.remove(1);
        this.revalidate();
        this.pack();
        this.addWindowListener(new ChatListener());
    }

    private void addPanels(){
        chatMenuPanel = new ChatMenuPanel(new MenuListener());
        Container c = getContentPane();
        c.setLayout(new MigLayout("insets 15 15 15 15"));
        c.add(chatMenuPanel,"dock north");
        //c.add(listOfUsersPanel,"dock east, gapright 15, gapbottom 20");
        c.add(chatPanel,"span");
    }

    private void setNameClient(){
        // TODO:Implement the login
    }

    public ChatMenuPanel getChatMenuPanel() {
        return chatMenuPanel;
    }

    public void setChatMenuPanel(ChatMenuPanel chatMenuPanel) {
        this.chatMenuPanel = chatMenuPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    /*public ListOfUsersPanel getListOfUsersPanel() {
        return listOfUsersPanel;
    }

    public void setListOfUsersPanel(ListOfUsersPanel listOfUsersPanel) {
        this.listOfUsersPanel = listOfUsersPanel;
    }*/
}