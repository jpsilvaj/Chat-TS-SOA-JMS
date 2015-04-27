package br.edu.ifce.chat.commons.view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class ListOfUsersPanel extends JPanel {

	private JPanel listOfUsers;
	
	private Boolean isEmpty=true;
	private JLabel empty = new JLabel("Empty");
	private MouseListener mouseListener;
	
	public ListOfUsersPanel(MouseListener mouseListener){
		initListUsersPanel();
		mouseListener = mouseListener;
	}
	
	private void initListUsersPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	
		JLabel listOfNotesLabel = new JLabel("List of users",JLabel.LEFT);
		listOfNotesLabel.setAlignmentX(LEFT_ALIGNMENT);
		listOfNotesLabel.setHorizontalAlignment(JLabel.LEFT);
		
		listOfUsers = new JPanel();
		listOfUsers.setLayout(new BoxLayout(listOfUsers,BoxLayout.Y_AXIS));
		listOfUsers.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		
//		this.listOfNotes.add(empty);
		
		this.add(listOfNotesLabel);
		this.add(listOfUsers);
		
	}
	
	public JPanel getListOfUsers() {
		return listOfUsers;
	}
	
	public void setListOfUsers(JPanel listOfUsers) {
		this.listOfUsers = listOfUsers;
	}
	
	public void addUserToPanel(String username){
		JLabel userleLabel = new JLabel(username);
		userleLabel.setText(username);
		userleLabel.setVisible(true);
		userleLabel.addMouseListener(mouseListener);
		listOfUsers.add(userleLabel);
	}
	
	public void removeAllUsers(){
		listOfUsers.removeAll();
	}
}
