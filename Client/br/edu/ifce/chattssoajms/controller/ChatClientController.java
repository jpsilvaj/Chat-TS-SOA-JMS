package br.edu.ifce.chattssoajms.controller;

import br.edu.ifce.chattssoajms.bean.Message;
import br.edu.ifce.chattssoajms.bean.User;
import br.edu.ifce.chattssoajms.view.ChatClientView;
import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceItem;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.transaction.TransactionException;
import net.jini.discovery.LookupDiscovery;
import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.ServiceDiscoveryManager;
import net.jini.space.JavaSpace;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * Created by jp-desktop on 21/04/2015.
 */
public class ChatClientController{
    public static ChatClientView chatClientView;
    public static JavaSpace javaSpace;

    public static void main(String[] args){
        startupSpaceConnection();
    }

    private static void startupSpaceConnection() {

    }

    public static void sendMessage(Message message) {

    }

    public static Message receiveMessage() {
        return null;
    }
}
