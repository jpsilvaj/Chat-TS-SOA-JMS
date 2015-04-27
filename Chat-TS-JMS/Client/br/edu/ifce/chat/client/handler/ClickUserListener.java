package br.edu.ifce.chat.client.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import br.edu.ifce.chat.client.controller.ChatClientController;

public class ClickUserListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JLabel label = (JLabel) arg0.getComponent();
		String username = label.getText();
		ChatClientController.setUserInCommunication(username);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}	