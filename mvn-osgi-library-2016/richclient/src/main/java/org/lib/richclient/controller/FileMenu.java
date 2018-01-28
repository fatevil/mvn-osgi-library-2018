/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.scene.control.Menu;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class FileMenu extends Menu {

    public FileMenu() {
        super(Messages.File.createMessage());
        this.getItems().addAll(
            ExitAction.inst.createItem(),
            CreateBookAction.inst.createItem(),
            DeleteAllBooksAction.inst.createItem());
    }

}
