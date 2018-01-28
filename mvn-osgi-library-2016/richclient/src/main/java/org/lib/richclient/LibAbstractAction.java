/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 *
 * @author danecek
 */
public abstract class LibAbstractAction implements Observer {

    String name;
    SimpleBooleanProperty disableProperty = new SimpleBooleanProperty(false);

    public LibAbstractAction(String name) {
        this.name = name;
        LibObservable.instance.addObserver(this);
    }

    public abstract void execute();

    public boolean checkDisable() {
        return false;
    }

    public MenuItem createItem() {
        MenuItem mi = new MenuItem(name);
        mi.disableProperty().bind(disableProperty);
        mi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                execute();
            }
        });
        return mi;
    }

    public Button createButton() {
        Button mi = new Button(name);
        mi.disableProperty().bind(disableProperty);
        mi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                execute();
            }
        });
        return mi;
    }

    @Override
    public void update(Observable o, Object arg) {
        disableProperty.set(checkDisable());
    }

}
