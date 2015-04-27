package br.edu.ifce.chat.spy.handler;

import br.edu.ifce.chat.client.bean.Message;
import br.edu.ifce.chat.spy.controller.SpyController;
import net.jini.core.event.RemoteEvent;
import net.jini.core.event.RemoteEventListener;
import net.jini.core.event.UnknownEventException;
import net.jini.export.Exporter;
import net.jini.jeri.BasicILFactory;
import net.jini.jeri.BasicJeriExporter;
import net.jini.jeri.tcp.TcpServerEndpoint;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class NotifyMessageToChatSpyListener implements RemoteEventListener, Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -2274698856413185111L;
	private RemoteEventListener theStub;

     public NotifyMessageToChatSpyListener() throws RemoteException {
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
    	SpyController.messageHasBeenInsertOnJavaSpace();
    }
}
