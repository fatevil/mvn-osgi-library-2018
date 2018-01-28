/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookId extends AbstrId<BookId> {

    public BookId() {
    }
    
    public BookId(Integer id) {
        super(id);
    }
    
}
