/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import org.lib.utils.LibException;

public abstract class Command implements Serializable {
    
    public static final Ok OK =new Ok();

    public abstract <T> T execute() throws LibException;
}
