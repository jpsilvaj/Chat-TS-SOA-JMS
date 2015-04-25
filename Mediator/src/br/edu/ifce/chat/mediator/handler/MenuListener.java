package br.edu.ifce.chat.mediator.handler;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.*;

import br.edu.ifce.chat.commons.utils.Constants;
import br.edu.ifce.chat.mediator.controller.MediatorController;

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
                MediatorController.disconnect();
            }catch(Exception exception){
                exception.printStackTrace();
            }
            finally{
                System.exit(0);
            }
        }
        else if(e.getActionCommand() == "connect"){
        	try {
				MediatorController.connect();
				JOptionPane.showMessageDialog(null, "Conectado ao serviço JMS com sucesso");
			} catch (NamingException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao se conectar com serviço JMS com sucesso");
				e1.printStackTrace();
			} catch (JMSException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao se conectar com serviço JMS com sucesso");
				e1.printStackTrace();
			}
        }
        else if(e.getActionCommand() == "disconnect"){
        	try {
				MediatorController.disconnect();
				JOptionPane.showMessageDialog(null, "Disconectado ao serviço JMS com sucesso");
			} catch (JMSException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao se desconectar com serviço JMS com sucesso");
				e1.printStackTrace();
			} catch (NamingException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao se desconectar com serviço JMS com sucesso");
				e1.printStackTrace();
			}
        }
        else if(e.getActionCommand() == "about"){
        	JOptionPane.showMessageDialog(null, Constants.ABOUT);
        }

    }
}