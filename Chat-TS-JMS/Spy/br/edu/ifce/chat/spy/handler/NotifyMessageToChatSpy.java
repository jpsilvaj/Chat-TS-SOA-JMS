package br.edu.ifce.chat.spy.handler;

import br.edu.ifce.chat.client.bean.Message;
import net.jini.core.event.RemoteEvent;
import net.jini.core.event.RemoteEventListener;
import net.jini.core.event.UnknownEventException;

import java.rmi.RemoteException;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class NotifyMessageToChatSpy implements RemoteEventListener{

    @Override
    public void notify(RemoteEvent remoteEvent) throws UnknownEventException, RemoteException {
        System.out.println(((Message) remoteEvent.getSource()).toString());
    }
}
