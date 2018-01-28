package org.lib.richclient.controller;

import org.lib.business.LibraryFacadeService;
import org.lib.richclient.LibAbstractAction;
import org.lib.utils.Messages;

public class CreateBookAction extends LibAbstractAction {

    public static CreateBookAction inst = new CreateBookAction();

    private CreateBookAction() {
        super(Messages.Create_book.createMessage());
    }

    @Override
    public boolean checkDisable() {
        return !LibraryFacadeService.service().isConnected();
    }

    @Override
    public void execute() {
        new CreateBookDialog().execute();
    }

}
