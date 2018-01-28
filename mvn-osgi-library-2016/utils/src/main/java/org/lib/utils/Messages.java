/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public enum Messages {

    Library,
    Books, Id, Title, Author, Exit, File, Empty_author, Empty_title, Create_book,
    Delete_all_books, Port, Host, Empty_host,
    Invalid_port, Connect, Disconnect, Not_connected, Delete_selected, Refresh;

    private static final Logger LOG = Logger.getLogger(Messages.class.getName());
    private static final ResourceBundle rb = ResourceBundle.getBundle("org.lib.utils.Messages");

    public String createMessage(String... args) {
        try {
            String m = (String) rb.getString(this.name());
            return MessageFormat.format(m, args);
        } catch (MissingResourceException ex) {
            //   LOG.log(Level.SEVERE, "missing resource", ex);
            return name().replace('_', ' ');
        }
    }

}
