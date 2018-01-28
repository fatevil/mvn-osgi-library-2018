/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import org.lib.richclient.LibAbstractAction;
import org.lib.richclient.MainWindow;
import org.lib.utils.Messages;

public class ExitAction extends LibAbstractAction {

    public static ExitAction inst = new ExitAction();

    private ExitAction() {
        super(Messages.Exit.createMessage());
    }

    @Override
    public void execute() {
        MainWindow.instance.stop();
    }


}
