/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.view;

import javafx.scene.control.ToolBar;
import org.lib.richclient.controller.DeleteAllBooksAction;
import org.lib.richclient.controller.CreateBookAction;
import org.lib.richclient.controller.DeleteSelectedAction;
import org.lib.richclient.controller.ExitAction;
import org.lib.richclient.controller.RefreshAction;

/**
 *
 * @author danecek
 */
public class LibToolBar extends ToolBar {

    public LibToolBar() {
        getItems().addAll(ExitAction.inst.createButton(),
                CreateBookAction.inst.createButton(),
                DeleteAllBooksAction.inst.createButton(),
                DeleteSelectedAction.inst.createButton(),
                RefreshAction.inst.createButton()
        );
    }

}
