/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author danecek
 */
public class MyTextField extends TextField {

    public MyTextField(MyValidator validator, String value) {
        super(value);
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validator.validate();
            }
        });
    }

    public MyTextField(MyValidator validator) {
        this(validator, "");
    }

}
