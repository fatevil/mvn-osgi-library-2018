/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import org.lib.business.impl.LibraryFacadeServiceImpl;
import org.lib.model.BookId;
import org.lib.model.MyBook;
import org.lib.model.MyBooks;
import org.lib.utils.LibException;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class LibraryFacadeService {

    private static LibraryFacadeService service;
    private static ServiceTracker<LibraryFacadeService, LibraryFacadeService> xst;

    public static LibraryFacadeService service() {
        if (service == null) {
            service = xst.getService();
            if (service == null) {
                service = new LibraryFacadeServiceImpl();
            }
        }
        return service;
    }

    static void setSt(ServiceTracker<LibraryFacadeService, LibraryFacadeService> st) {
        xst = st;
    }

    public abstract void createBook(MyBook book) throws LibException;

    public abstract MyBooks allBook() throws LibException;

    public abstract void clearAllBook() throws LibException;

    public abstract boolean isConnected();

    public abstract void delete(BookId bookId) throws LibException;
}
