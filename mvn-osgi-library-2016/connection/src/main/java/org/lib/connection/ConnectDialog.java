/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.lib.richclient.AbstrDialog;
import org.lib.richclient.LibObservable;
import org.lib.richclient.MyTextField;
import org.lib.richclient.MyAlert;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class ConnectDialog extends AbstrDialog {

    MyTextField hostTf;
    MyTextField portTf;
    private String host;
    private int port;

    public ConnectDialog() {
        portTf = new MyTextField(this, "3333");
        hostTf = new MyTextField(this, "localhost");
        setTitle(Messages.Connect.createMessage());
        getDialogPane().setContent(new VBox(content(), errorMessage));
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        validate();
    }

    @Override
    protected Node content() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(5));
        gp.setHgap(5);
        gp.setVgap(5);
        gp.add(new Label(Messages.Host.createMessage()), 0, 0);
        gp.add(hostTf, 1, 0);
        gp.add(new Label(Messages.Port.createMessage()), 0, 1);
        gp.add(portTf, 1, 1);
        return gp;
    }

    @Override
    protected void ok() {
        if (validate()) {
            try {
                LibConnection.getService().connect(host, port);
                LibObservable.instance.stateChanged();
            } catch (IOException ex) {
                MyAlert.error(ex);
            }
        }
    }

    @Override
    public boolean validate() {
        host = hostTf.getText();
        if (host.isEmpty()) {
            errorMessage.setText(Messages.Empty_host.createMessage());
            return false;
        }
        try {
            port = Integer.parseInt(portTf.getText());
        } catch (NumberFormatException ex) {
            errorMessage.setText(Messages.Invalid_port.createMessage());
            return false;
        }
        errorMessage.setText("");
        return true;
    }

}
