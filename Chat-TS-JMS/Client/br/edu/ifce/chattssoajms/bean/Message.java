package br.edu.ifce.chattssoajms.bean;

import net.jini.core.entry.Entry;
import java.util.Date;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class Message implements Entry{

    public String sender;
    public String receiver;
    public String message;
    public Date date;

    public Message(){
    }
}
