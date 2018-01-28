package org.lib.richclient.controller;

import org.lib.richclient.LibObservable;
import org.lib.business.LibraryFacadeService;
import org.lib.model.MyBook;
import org.lib.richclient.LibAbstractAction;
import org.lib.richclient.MainWindow;
import org.lib.richclient.MyAlert;
import org.lib.utils.LibException;
import org.lib.utils.Messages;

public class DeleteSelectedAction extends LibAbstractAction {

    public static DeleteSelectedAction inst = new DeleteSelectedAction();

    private DeleteSelectedAction() {
        super(Messages.Delete_selected.createMessage());
    }

    @Override
    public boolean checkDisable() {
        return !LibraryFacadeService.service().isConnected()
                || MainWindow.instance.getSelectedBooks().isEmpty();
    }

    @Override
    public void execute() {
        try {
            for (MyBook mb : MainWindow.instance.getSelectedBooks()) {
                LibraryFacadeService.service().delete(mb.getBookId());
            }
            LibObservable.instance.stateChanged();
        } catch (LibException ex) {
            MyAlert.error(ex);
        }
    }

}
