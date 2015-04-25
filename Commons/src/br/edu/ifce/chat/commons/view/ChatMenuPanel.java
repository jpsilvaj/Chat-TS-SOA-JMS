package br.edu.ifce.chat.commons.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    public ChatMenuPanel(ActionListener actionListener){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));;
        configureMenuBar(actionListener);
    }

    private void configureMenuBar(ActionListener actionListener){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        ImageIcon exitIcon = new ImageIcon("exit.png");
        JMenuItem eMenuItem = new JMenuItem("Exit", exitIcon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.setActionCommand("exit");
        eMenuItem.addActionListener(actionListener);

        JMenu chat = new JMenu("Connect");
        chat.setMnemonic(KeyEvent.VK_R);

        ImageIcon roomIcon = new ImageIcon("connect.png");

        JMenuItem connectRoomMenuItem = new JMenuItem("Connect", roomIcon);
        connectRoomMenuItem.setMnemonic(KeyEvent.VK_C);
        connectRoomMenuItem.setToolTipText("Connect");
        connectRoomMenuItem.setActionCommand("connect");
        connectRoomMenuItem.addActionListener(actionListener);

        JMenuItem disconnectRoomMenuItem = new JMenuItem("Disconnect", roomIcon);
        disconnectRoomMenuItem.setMnemonic(KeyEvent.VK_D);
        disconnectRoomMenuItem.setToolTipText("Disconnect");
        disconnectRoomMenuItem.setActionCommand("disconnect");
        disconnectRoomMenuItem.addActionListener(actionListener);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);

        ImageIcon aboutIcon = new ImageIcon("about.png");
        JMenuItem aboutMenuItem = new JMenuItem("About", aboutIcon);
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.setToolTipText("About application");
        aboutMenuItem.setActionCommand("about");
        aboutMenuItem.addActionListener(actionListener);

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