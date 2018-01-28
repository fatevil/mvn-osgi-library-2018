/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import org.lib.richclient.LibObservable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.lib.business.LibraryFacadeService;
import org.lib.model.MyBook;
import org.lib.richclient.AbstrDialog;
import org.lib.richclient.MyTextField;
import org.lib.richclient.MyValidator;
import org.lib.richclient.MyAlert;
import org.lib.utils.LibException;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public final class CreateBookDialog extends AbstrDialog implements MyValidator {

    MyTextField authorTf;
    MyTextField titleTf;

    private String author;
    private String title;

    @Override
    public Node content() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(5));
        gp.setHgap(5);
        gp.setVgap(5);
        gp.add(new Label(Messages.Author.createMessage()), 0, 0);
        gp.add(authorTf = new MyTextField(this), 1, 0);
        gp.add(new Label(Messages.Title.createMessage()), 0, 1);
        gp.add(titleTf = new MyTextField(this), 1, 1);
        return gp;
    }

    public CreateBookDialog() {
        setTitle(Messages.Create_book.createMessage());
        getDialogPane().setContent(new VBox(content(), errorMessage));
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        validate();
    }

    @Override
    public void ok() {
        try {
            validate();
            MyBook book = new MyBook(title, author);
            LibraryFacadeService.service().createBook(book);
            LibObservable.instance.stateChanged();
        } catch (LibException ex) {
            MyAlert.error(ex);
        }
    }

    @Override
    public boolean validate() {
        author = authorTf.getText();
        if (author.isEmpty()) {
            errorMessage.setText(Messages.Empty_author.createMessage());
            return false;
        }
        title = titleTf.getText();
        if (title.isEmpty()) {
            errorMessage.setText(Messages.Empty_title.createMessage());
            return false;
        }
        errorMessage.setText("");
        return true;
    }

}
