/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyBook implements Serializable {

    public MyBook() {
    }

    private BookId bookId;
    private String title;
    private String author;

    public MyBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public MyBook(BookId bookId, String title, String author) {
        this(title, author);
        this.bookId = bookId;
    }

    /**
     * @return the bookId
     */
    public BookId getBookId() {
        return bookId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
