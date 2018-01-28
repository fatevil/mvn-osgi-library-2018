package org.lib.serverxml;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibServer implements Runnable {

    ExecutorService es;

    public LibServer(ExecutorService es) {
        this.es = es;
    }

    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(3333);
            for (;;) {
                LOG.info("waiting for client");
                Socket s = ss.accept();
                LOG.info("client accepted");
                es.submit(new ClientTask(s));
            }
        } catch (IOException ex) {
            Logger.getLogger(LibServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static final Logger LOG = Logger.getLogger(LibServer.class.getName());

}
