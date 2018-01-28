/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public class LibServer implements Runnable {

    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(3333);
            for (;;) {
                LOG.info("waiting for client");
                Socket s = ss.accept();
                LOG.info("client accepted");
                new Thread(new ClientTask(s)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(LibServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static final Logger LOG = Logger.getLogger(LibServer.class.getName());

}
