package br.edu.ifce.chattssoajms.bean;

import net.jini.core.entry.Entry;

import java.io.Serializable;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class User implements Entry,Serializable{

    public String username;

    public User(){
    }
}
