/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business.impl;

import java.util.ArrayList;
import org.lib.business.LibraryFacadeService;
import org.lib.integration.DAOFactoryService;
import org.lib.model.BookId;
import org.lib.model.MyBook;
import org.lib.model.MyBooks;
import org.lib.utils.LibException;

/**
 *
 * @author danecek
 */
public class LibraryFacadeServiceImpl extends LibraryFacadeService {

    @Override
    public void createBook(MyBook book) throws LibException {
        DAOFactoryService.service().getBookDAO().create(book);
    }

    @Override
    public MyBooks allBook() throws LibException {
        return new MyBooks(new ArrayList<>(DAOFactoryService.service().getBookDAO().all()));
    }

    @Override
    public void clearAllBook() throws LibException {
        DAOFactoryService.service().getBookDAO().clear();
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void delete(BookId bookId) {
        DAOFactoryService.service().getBookDAO().delete(bookId);
    }

}
