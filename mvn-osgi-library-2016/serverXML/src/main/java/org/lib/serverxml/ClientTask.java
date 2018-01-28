/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.serverxml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lib.model.MyBook;
import org.lib.model.MyBooks;
import org.lib.protocol.AllBook;
import org.lib.protocol.ClearAllBook;
import org.lib.protocol.Command;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBook;
import org.lib.protocol.Ok;
import org.lib.utils.LibException;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    DataInputStream ois;
    DataOutputStream oos;
   // static Class[] x = {MyBook.class, DeleteBook.class, CreateBook.class, Ok.class};
    private Unmarshaller u;
    private Marshaller m;

    public ClientTask(Socket s) throws IOException {
        oos = new DataOutputStream(s.getOutputStream());
        ois = new DataInputStream(s.getInputStream());
        try {
            JAXBContext jxbc = JAXBContext.newInstance(AllBook.class, 
                    CreateBook.class, ClearAllBook.class, DeleteBook.class,
                    MyBooks.class, Ok.class);
            m = jxbc.createMarshaller();
            u = jxbc.createUnmarshaller();
        } catch (JAXBException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            for (;;) {
                LOG.info("waiting for command");
                try {
                    String comStr = ois.readUTF();
                    LOG.info(comStr);
                    Command com = (Command) u.unmarshal(new StringReader(comStr));
                    Object res = com.execute();
                    StringWriter sw = new StringWriter();
                    m.marshal(res, sw);
                    String resStr = sw.toString();
                    LOG.info(resStr);

                    oos.writeUTF(resStr);
                    oos.flush();
                } catch (LibException ex) {
                    throw new RuntimeException(ex);  // todo
                } catch (JAXBException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
                oos.flush();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());

}
