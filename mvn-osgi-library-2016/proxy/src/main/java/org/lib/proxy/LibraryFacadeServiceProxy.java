/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy;

import org.lib.business.LibraryFacadeService;
import org.lib.connection.LibConnection;
import org.lib.model.BookId;
import org.lib.model.MyBook;
import org.lib.model.MyBooks;
import org.lib.protocol.AllBook;
import org.lib.protocol.ClearAllBook;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBook;
import org.lib.utils.LibException;

public class LibraryFacadeServiceProxy extends LibraryFacadeService {

    @Override
    public void createBook(MyBook book) throws LibException {
        LibConnection.getService().send(new CreateBook(book));
    }

    @Override
    public MyBooks allBook() throws LibException {
        return LibConnection.getService().send(new AllBook());
    }

    @Override
    public void clearAllBook() throws LibException {
        LibConnection.getService().send(new ClearAllBook());
    }

    @Override
    public boolean isConnected() {
        return LibConnection.getService().isConnected();
    }

    @Override
    public void delete(BookId bookId) throws LibException {
        LibConnection.getService().send(new DeleteBook(bookId));
    }

}
