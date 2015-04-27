package br.edu.ifce.chat.client.bean;

import net.jini.core.entry.Entry;

import java.io.Serializable;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class User implements Entry{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;

    public User(){
    }
    
    @Override
    public boolean equals(Object O){
    	if(O != null && O instanceof User){
	    	User newUser = (User) O;
	    	if(newUser.username.equals(this.username)){
	    		return true;
	    	}
    	}
		return false;
    }
    
    /*public hashcode(){
    	
    }*/
}
