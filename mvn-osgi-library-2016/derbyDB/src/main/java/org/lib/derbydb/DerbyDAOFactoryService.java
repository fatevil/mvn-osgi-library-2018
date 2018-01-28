/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.lib.integration.BookDAO;
import org.lib.integration.DAOFactoryService;

/**
 *
 * @author danecek
 */
public class DerbyDAOFactoryService extends DAOFactoryService {

    private Connection conn;

    public DerbyDAOFactoryService() {
        try {
            new EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/libraryDB; create=true";
            conn = DriverManager.getConnection(url);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "BOOKS", null);
            if (!rs.next()) {
                LOG.info("CREATE TABLE BOOKS");
                Statement stm = conn.createStatement();
                stm.executeUpdate("CREATE TABLE BOOKS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "TITLE   VARCHAR(50),"
                        + "AUTHOR  VARCHAR(50),"
                        + "PRIMARY KEY (ID))");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(DerbyDAOFactoryService.class.getName());

    DerbyBookDAO derbyBookDAO;

    @Override
    public BookDAO getBookDAO() {
        if (derbyBookDAO == null) {
            derbyBookDAO = new DerbyBookDAO(conn);
        }
        return derbyBookDAO;
    }

}
