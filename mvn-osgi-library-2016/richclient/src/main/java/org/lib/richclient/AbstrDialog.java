/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.text.Text;


public abstract class AbstrDialog extends Dialog<ButtonType> implements MyValidator {

    protected Text errorMessage = new Text();

    protected abstract Node content();

    public void execute() {
        Optional<ButtonType> result = showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ok();
        }
    }

    protected abstract void ok();

    @Override
    public abstract boolean validate();

}
