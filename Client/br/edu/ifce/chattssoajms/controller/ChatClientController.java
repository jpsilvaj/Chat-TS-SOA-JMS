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
        DiscoveryListenerManagement dlm = null;
        try {
            dlm = new LookupDiscovery(LookupDiscovery.ALL_GROUPS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LeaseRenewalManager lrm = new LeaseRenewalManager();
        ServiceDiscoveryManager sdm = new ServiceDiscoveryManager(dlm, lrm);

        try {
            Thread.sleep(500); //need to wait a little bit for the Lookup Service to generate the events to the sdm
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ServiceTemplate srTemplate = new ServiceTemplate(null, new Class[] { ServiceRegistrar.class }, null);

        ServiceItem[] sis = sdm.lookup(srTemplate, 10, null);
        for(ServiceItem si : sis) {
            System.out.println("Service Registrar: "+si.serviceID);
        }

        dlm.terminate();

    }

    public static void sendMessage(Message message) {

    }

    public static Message receiveMessage() {
        return null;
    }
}
