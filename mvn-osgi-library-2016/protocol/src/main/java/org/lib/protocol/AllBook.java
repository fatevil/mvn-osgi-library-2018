/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeService;
import org.lib.utils.LibException;

@XmlRootElement
public class AllBook extends Command {

    @Override
    public <T> T execute() throws LibException {
        return (T) LibraryFacadeService.service().allBook();
    }
    
}
