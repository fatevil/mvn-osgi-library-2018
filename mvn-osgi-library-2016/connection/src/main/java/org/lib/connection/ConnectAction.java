/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import org.lib.richclient.LibAbstractAction;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class ConnectAction extends LibAbstractAction {

    public static ConnectAction instance = new ConnectAction();

    @Override
    public void execute() {
        new ConnectDialog().execute();
    }

    @Override
    public boolean checkDisable() {
        return LibConnection.getService().isConnected();
    }

    private ConnectAction() {
        super(Messages.Connect.createMessage());
    }

}
