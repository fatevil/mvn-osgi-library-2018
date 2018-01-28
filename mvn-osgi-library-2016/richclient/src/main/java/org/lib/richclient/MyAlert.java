/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.scene.control.Alert;

/**
 *
 * @author danecek
 */
public class MyAlert {
    
    public static void error(Exception ex) {
        error(ex.toString());
    }
    
    public static void error(String mess) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(mess);
        a.showAndWait();
    }
}
