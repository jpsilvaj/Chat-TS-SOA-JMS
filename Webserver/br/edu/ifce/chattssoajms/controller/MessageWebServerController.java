package br.edu.ifce.chattssoajms.controller;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by jp-desktop on 22/04/2015.
 */
@WebService
public interface MessageWebServerController {
    @WebMethod void registerMessageIllegal(String message);
}
