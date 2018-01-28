/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.lib.integration.BookDAO;
import org.lib.model.MyBook;
import org.lib.model.BookId;

public final class BookDAODefault implements BookDAO {

    private final Map<BookId, MyBook> books = new HashMap<>();
    private static int counter;

    public BookDAODefault() {
        create(new MyBook("RUR", "Capek"));
    }

    @Override
    public void create(MyBook book) {
        BookId bookId = new BookId(++counter);
        books.put(bookId, new MyBook(bookId, book.getTitle(), book.getAuthor()));
    }

    @Override
    public Collection<MyBook> all() {
        return new ArrayList<>(books.values());
    }

    @Override
    public void clear() {
        books.clear();
    }

    @Override
    public void delete(BookId bookId) {
        books.remove(bookId);
    }

}
