package br.edu.ifce.chat.client.bean;

import net.jini.core.entry.Entry;
import java.util.Date;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class Message implements Entry{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4306927694562149253L;
	public String sender;
    public String receiver;
    public String message;
    public Date date;

    public Message(){
    }
}
