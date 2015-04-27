package br.edu.ifce.chat.client.view;

import java.awt.Container;
import java.util.List;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import br.edu.ifce.chat.client.bean.User;
import br.edu.ifce.chat.client.controller.ChatClientController;
import br.edu.ifce.chat.client.handler.ChatClientListener;
import br.edu.ifce.chat.client.handler.ClickUserListener;
import br.edu.ifce.chat.client.handler.MenuListener;
import br.edu.ifce.chat.commons.utils.ChatListener;
import br.edu.ifce.chat.commons.view.ChatInputDialog;
import br.edu.ifce.chat.commons.view.ChatMenuPanel;
import br.edu.ifce.chat.commons.view.ChatPanel;
import br.edu.ifce.chat.commons.view.ListOfUsersPanel;


/**
 * Created by jp-desktop on 13/04/2015.
 */
public class ChatClientView  extends JFrame{
        private static final long serialVersionUID = -7716037243160876089L;
        private ChatMenuPanel chatMenuPanel;
        private ChatPanel chatPanel;
        private ListOfUsersPanel listOfUsersPanel;
        private String username = "";

        public ChatClientView(){
            super("Chat client - Tuple Space - SOA - JMS ");
            chatPanel = new ChatPanel( new ChatClientListener());
            listOfUsersPanel = new ListOfUsersPanel( new ClickUserListener());
            this.setSize(1080,720);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setResizable(false);
            this.login();
            this.setTitle("Chat client - Tuple Space - SOA - JMS : " + username);
            this.addPanels();
            this.pack();
            this.addWindowListener(new ChatListener());
        }

        private void addPanels(){
            chatMenuPanel = new ChatMenuPanel(new MenuListener());
            Container c = getContentPane();
            c.setLayout(new MigLayout("insets 15 15 15 15"));
            c.add(chatMenuPanel,"dock north");
            c.add(listOfUsersPanel,"dock east, gapright 15, gapbottom 20");
            c.add(chatPanel,"span");
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

        public ListOfUsersPanel getListOfUsersPanel() {
            return listOfUsersPanel;
        }

        public void setListOfUsersPanel(ListOfUsersPanel listOfUsersPanel) {
            this.listOfUsersPanel = listOfUsersPanel;
        }

		public void login() {
			String username = ChatInputDialog.showInputDialog("Insert your username");
			boolean loginSucessed = ChatClientController.login(username);
			if(!loginSucessed){
				login();
			}
			this.username = username;
		}
		
		public void updateListOfUsers(List<User> users){
			listOfUsersPanel.removeAllUsers();
			for(User user : users){
				if(!user.username.equals(username))
					listOfUsersPanel.addUserToPanel(user.username);
			}
		}
		public void addMessageToPanel(String message){
			chatPanel.addMessageToPanel(message);
		}
}