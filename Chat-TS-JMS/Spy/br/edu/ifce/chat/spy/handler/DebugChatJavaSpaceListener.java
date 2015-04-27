package br.edu.ifce.chat.spy.handler;

import net.jini.lease.LeaseListener;
import net.jini.lease.LeaseRenewalEvent;

public class DebugChatJavaSpaceListener implements LeaseListener {
	
	@Override
	public void notify(LeaseRenewalEvent arg0) {
		System.err.println("Got lease renewal problem");
		System.err.println(arg0.getException());
		System.err.println(arg0.getExpiration());
		System.err.println(arg0.getLease());
	}
}
