/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyBooks implements Serializable{
    
    private List<MyBook> books;

    public List<MyBook> getBooks() {
        return books;
    }

    public MyBooks() {
    }

    public MyBooks(List<MyBook> books) {
        this.books = books;
    }


    public void setBooks(List<MyBook> books) {
        this.books = books;
    }
    
}
