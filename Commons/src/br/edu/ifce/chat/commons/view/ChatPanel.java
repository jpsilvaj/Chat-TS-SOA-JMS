package br.edu.ifce.chat.commons.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class ChatPanel extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4280770700598570237L;
	JScrollPane historyMessageScrolledPane;
	private JTextArea historyMessage;
	JPanel sendMessagePanel;
	private JTextField textBox;

	public ChatPanel(ActionListener actionListener){
		this.configureChat(actionListener);
	}

	private void configureChat(ActionListener actionListener){
		historyMessage = new JTextArea(35,90);
		historyMessage.setEditable(false);
		historyMessage.setLineWrap(true);
		historyMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		
		historyMessageScrolledPane = new JScrollPane(historyMessage);
		historyMessageScrolledPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		historyMessageScrolledPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		textBox = new JTextField(80);
		textBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		textBox.setActionCommand("send_message");
		textBox.addActionListener(actionListener);
		
		JButton send = new JButton("Send");
		send.setActionCommand("send_message");
		send.addActionListener(actionListener);
		
		sendMessagePanel = new JPanel();
		sendMessagePanel.setLayout(new FlowLayout());
		sendMessagePanel.add(textBox);
		sendMessagePanel.add(send);
	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(historyMessageScrolledPane);
		this.add(sendMessagePanel);
		this.setVisible(true);
		
	}

	public JTextArea getHistoryMessage() {
		return historyMessage;
	}

	public void setHistoryMessage(JTextArea historyMessage) {
		this.historyMessage = historyMessage;
	}
	
	public JTextField getTextBox() {
		return textBox;
	}

	public void setTextBox(JTextField textBox) {
		this.textBox = textBox;
	}
}
