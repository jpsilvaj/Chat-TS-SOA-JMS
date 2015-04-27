package br.edu.ifce.chat.commons.view;

import javax.swing.JOptionPane;

public class ChatInputDialog {
	public static String showInputDialog(String message){
	    String inputValue = JOptionPane.showInputDialog(message);
	    if(inputValue == null || inputValue.isEmpty() || !inputValue.matches("[A-Za-z]+[1-9]*")){
	        inputValue = showInputDialog(message);
	    }
	    return inputValue;
	}	
}
