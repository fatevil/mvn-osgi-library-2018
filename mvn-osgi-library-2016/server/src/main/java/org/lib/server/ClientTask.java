/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.model.MyBook;
import org.lib.protocol.Command;
import org.lib.protocol.DeleteBook;
import org.lib.utils.LibException;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    ObjectInputStream ois;
    ObjectOutputStream oos;
    static Class[] x = {MyBook.class, DeleteBook.class};

    public ClientTask(Socket s) throws IOException {
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());

    }

    @Override
    public void run() {
        try {
            for (;;) {
                LOG.info("waiting for command");
                try {
                    Command com = (Command) ois.readObject();
                    LOG.info(com.toString());
                    Object result = com.execute();
                    oos.writeObject(result);
                } catch (LibException ex) {
                    oos.writeObject(ex);
                }
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());

}
