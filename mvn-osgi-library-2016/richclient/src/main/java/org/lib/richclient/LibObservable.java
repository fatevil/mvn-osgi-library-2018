/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.Observable;


public class LibObservable extends  Observable {
    
    public static LibObservable instance = new LibObservable();

    private LibObservable() {
        
    }
    
    public void stateChanged() {
       setChanged();
       notifyObservers();
    }
    
}
