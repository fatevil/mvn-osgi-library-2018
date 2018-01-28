/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import org.lib.integration.BookDAO;
import org.lib.integration.DAOFactoryService;

public class DAOFactoryServiceImpl extends DAOFactoryService {

    private BookDAO bookDAO;

    @Override
    public BookDAO getBookDAO() {
        if (bookDAO == null) {
            bookDAO = new BookDAODefault();
        }
        return bookDAO;//BookDAODefault.instance;
    }

}
