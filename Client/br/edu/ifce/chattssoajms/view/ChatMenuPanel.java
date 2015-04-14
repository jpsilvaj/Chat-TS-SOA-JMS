package br.edu.ifce.chattssoajms.view;

import br.edu.ifce.chattssoajms.handler.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class ChatMenuPanel extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 3774041554169510911L;
    JMenuBar menuBar;

    public ChatMenuPanel(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));;
        configureMenuBar();
    }

    private void configureMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        ImageIcon exitIcon = new ImageIcon("exit.png");
        JMenuItem eMenuItem = new JMenuItem("Exit", exitIcon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.setActionCommand("exit");
        eMenuItem.addActionListener( new MenuListener());

        JMenu chat = new JMenu("Chat");
        chat.setMnemonic(KeyEvent.VK_R);

        ImageIcon roomIcon = new ImageIcon("chat.png");

        JMenuItem connectRoomMenuItem = new JMenuItem("Connect chat", roomIcon);
        connectRoomMenuItem.setMnemonic(KeyEvent.VK_C);
        connectRoomMenuItem.setToolTipText("Connect chat");
        connectRoomMenuItem.setActionCommand("connect_chat");
        connectRoomMenuItem.addActionListener(new MenuListener());

        JMenuItem disconnectRoomMenuItem = new JMenuItem("Disconnect chat", roomIcon);
        disconnectRoomMenuItem.setMnemonic(KeyEvent.VK_D);
        disconnectRoomMenuItem.setToolTipText("Disconnect chat");
        disconnectRoomMenuItem.setActionCommand("disconnect_room");
        disconnectRoomMenuItem.addActionListener(new MenuListener());

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);

        ImageIcon aboutIcon = new ImageIcon("about.png");
        JMenuItem aboutMenuItem = new JMenuItem("About", aboutIcon);
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.setToolTipText("About application");
        aboutMenuItem.setActionCommand("about");
        aboutMenuItem.addActionListener(new MenuListener());

        file.add(eMenuItem);
        chat.add(connectRoomMenuItem);
        chat.add(disconnectRoomMenuItem);
        help.add(aboutMenuItem);

        menuBar.add(file);
        menuBar.add(chat);
        menuBar.add(help);

        this.add(menuBar);
        this.setVisible(true);
    }
}