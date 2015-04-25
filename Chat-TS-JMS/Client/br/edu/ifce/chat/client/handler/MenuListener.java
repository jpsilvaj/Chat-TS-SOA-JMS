package br.edu.ifce.chat.client.handler;

import javax.swing.*;

import br.edu.ifce.chat.commons.utils.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "exit"){
            try{
                //TODO:Implement exit
            }catch(Exception exception){
                exception.printStackTrace();
            }
            finally{
                System.exit(0);
            }
        }
        else if(e.getActionCommand() == "connect"){

        }
        else if(e.getActionCommand() == "disconnect"){

        }
        else if(e.getActionCommand() == "about"){
        	JOptionPane.showMessageDialog(null, Constants.ABOUT);
        }

    }

    private String showInputDialog(String message){
        String inputValue = JOptionPane.showInputDialog(message);
        if(inputValue == null || inputValue.isEmpty() || !inputValue.matches("[A-Za-z]+[1-9]*")){
            inputValue = showInputDialog(message);
        }
        return inputValue;
    }
}