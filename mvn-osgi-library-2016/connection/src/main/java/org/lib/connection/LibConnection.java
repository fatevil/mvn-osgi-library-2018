/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.IOException;
import org.lib.protocol.Command;
import org.lib.utils.LibException;
import org.osgi.util.tracker.ServiceTracker;


public abstract class LibConnection {

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker<LibConnection, LibConnection> aSt) {
        st = aSt;
    }

    private static ServiceTracker<LibConnection, LibConnection> st;

    public static LibConnection getService() {
        if (service == null) {
            service = st.getService();
            if (service == null)
            service = new LibConnectionDefault();
        }
        return service;
    }
    
    private static LibConnection service;

    public abstract void connect(String host, int port) throws IOException;

    public abstract void disconnect() throws IOException;

    public abstract <T> T send(Command com) throws LibException;

    public abstract boolean isConnected();
    
    
}
