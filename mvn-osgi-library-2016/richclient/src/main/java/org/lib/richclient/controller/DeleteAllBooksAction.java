package org.lib.richclient.controller;

import org.lib.richclient.LibObservable;
import org.lib.business.LibraryFacadeService;
import org.lib.richclient.LibAbstractAction;
import org.lib.richclient.MainWindow;
import org.lib.richclient.MyAlert;
import org.lib.utils.LibException;
import org.lib.utils.Messages;

public class DeleteAllBooksAction extends LibAbstractAction {

    public static DeleteAllBooksAction inst = new DeleteAllBooksAction();

    private DeleteAllBooksAction() {
        super(Messages.Delete_all_books.createMessage());
    }

    @Override
    public boolean checkDisable() {
        return MainWindow.instance.getBooks().isEmpty()
                || !LibraryFacadeService.service().isConnected();
    }

    @Override
    public void execute() {
        try {
            LibraryFacadeService.service().clearAllBook();
            LibObservable.instance.stateChanged();
        } catch (LibException ex) {
            MyAlert.error(ex);
        }
    }

}
