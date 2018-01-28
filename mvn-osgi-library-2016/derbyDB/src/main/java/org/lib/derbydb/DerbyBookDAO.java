/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.BookDAO;
import org.lib.model.BookId;
import org.lib.model.MyBook;
import org.lib.utils.LibException;

/**
 *
 * @author danecek
 */
public class DerbyBookDAO implements BookDAO {

    PreparedStatement createPs;
    PreparedStatement allPs;
    PreparedStatement clearPs;

    public DerbyBookDAO(Connection conn) {
        try {
            allPs = conn.prepareStatement("SELECT * FROM BOOKS");
            createPs = conn.prepareStatement("INSERT INTO BOOKS VALUES(DEFAULT, ?, ?)");
            clearPs = conn.prepareStatement("DELETE FROM BOOKS");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyBookDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void create(MyBook book) throws LibException {
        try {
            createPs.setString(1, book.getTitle());
            createPs.setString(2, book.getAuthor());
            createPs.executeUpdate();
        } catch (SQLException ex) {
            throw new LibException(ex);
        }
    }

    @Override
    public Collection<MyBook> all() throws LibException {
        ArrayList<MyBook> books = new ArrayList<MyBook>();
        try {
            ResultSet rs = allPs.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String author = rs.getString(2);
                String title = rs.getString(3);
                books.add(new MyBook(new BookId(id), title, author));

            }
            return books;
        } catch (SQLException ex) {
            throw new LibException(ex);
        }
    }

    @Override
    public void clear() throws LibException {
        try {
            clearPs.executeUpdate();
        } catch (SQLException ex) {
            throw new LibException(ex);
        }
    }

    @Override
    public void delete(BookId bookId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
