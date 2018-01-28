/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connectionxml;

import java.io.DataInput;
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
import org.lib.connection.LibConnection;
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
public class LibConnectionXML extends LibConnection {

    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Marshaller m;
    private Unmarshaller u;

    public LibConnectionXML() {
        try {
            JAXBContext jxbc = JAXBContext.newInstance(AllBook.class,
                    CreateBook.class, ClearAllBook.class, DeleteBook.class,
                    MyBooks.class, Ok.class);
            m = jxbc.createMarshaller();
            u = jxbc.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(LibConnectionXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void connect(String host, int port) throws IOException {
        s = new Socket(host, port);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
    }

    @Override
    public void disconnect() throws IOException {

    }

    @Override
    public <T> T send(Command com) throws LibException {
        try {
            StringWriter sw = new StringWriter();
            m.marshal(com, sw);
            String comStr = sw.toString();
            LOG.info(comStr);
            dos.writeUTF(comStr);
            dos.flush();
            String resStr = dis.readUTF();
            LOG.info(resStr);
            Object res = u.unmarshal(new StringReader(resStr));
            if (res instanceof Exception) {
                throw new LibException(resStr);
            }
            return (T) res;
        } catch (JAXBException ex) {
            Logger.getLogger(LibConnectionXML.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new LibException(ex);
        }

    }
    private static final Logger LOG = Logger.getLogger(LibConnectionXML.class.getName());

    @Override
    public boolean isConnected() {
        return s != null;
    }

}
