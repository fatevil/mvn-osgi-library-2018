/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;

public class AbstrId<T extends AbstrId> implements Comparable<T>, Serializable {

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public AbstrId() {
    }

    public AbstrId(int id) {
        this.id = id;
    }

    public int compareTo(T o) {
        return id - o.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return compareTo((T) obj) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}
