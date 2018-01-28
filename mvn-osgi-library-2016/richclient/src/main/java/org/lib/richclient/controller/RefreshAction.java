/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import org.lib.richclient.LibObservable;
import org.lib.business.LibraryFacadeService;
import org.lib.richclient.LibAbstractAction;
import org.lib.richclient.MyAlert;
import org.lib.utils.LibException;
import org.lib.utils.Messages;

public class RefreshAction extends LibAbstractAction {

    public static RefreshAction inst = new RefreshAction();

    private RefreshAction() {
        super(Messages.Refresh.createMessage());
    }

    @Override
    public boolean checkDisable() {
        return !LibraryFacadeService.service().isConnected();
    }

    @Override
    public void execute() {
        LibObservable.instance.stateChanged();
    }

}
