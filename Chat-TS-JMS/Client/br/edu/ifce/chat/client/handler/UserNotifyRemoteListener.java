package br.edu.ifce.chat.client.handler;

import br.edu.ifce.chat.client.controller.ChatClientController;
import net.jini.core.event.RemoteEvent;
import net.jini.core.event.RemoteEventListener;
import net.jini.export.Exporter;
import net.jini.jeri.BasicILFactory;
import net.jini.jeri.BasicJeriExporter;
import net.jini.jeri.tcp.TcpServerEndpoint;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class UserNotifyRemoteListener implements RemoteEventListener, Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -2274698856413185111L;
	private RemoteEventListener theStub;

     public UserNotifyRemoteListener() throws RemoteException {
    	 Exporter myDefaultExporter = 
                 new BasicJeriExporter(TcpServerEndpoint.getInstance(0),
                                       new BasicILFactory(), false, true);

             theStub = (RemoteEventListener) myDefaultExporter.export(this);
     }

     public RemoteEventListener getStub() {
         return theStub;
     }
	
    @Override
    public void notify(RemoteEvent remoteEvent){
    	//ChatClientController.updateListOfUsersConnected();
    }
}