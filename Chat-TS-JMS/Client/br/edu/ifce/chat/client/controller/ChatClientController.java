package br.edu.ifce.chat.client.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.client.view.ChatClientView;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ChatClientController{
    public ChatClientView chatClientView;

    public static void main(String[] args){
        startupSpaceConnection();
    }

    public static void startupSpaceConnection() {
    	LookupLocator ll;
	
		try {
			ll = new LookupLocator("jini://localhost:4160");
			ServiceRegistrar sr;
			sr = ll.getRegistrar();
			System.out.println("Service Registrar: "+sr.getServiceID());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
    }

    public static void sendMessage(Message message) {

    }

    public static Message receiveMessage() {
        return null;
    }
}
