/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import java.util.logging.Logger;
import org.lib.integration.impl.DAOFactoryServiceImpl;
import org.osgi.util.tracker.ServiceTracker;

public abstract class DAOFactoryService {

    private static DAOFactoryService service;
    private static ServiceTracker<DAOFactoryService, DAOFactoryService> st;

    public static DAOFactoryService service() {
        if (service == null) {
            service = st.getService();
            if (service == null) {
                LOG.info("default DAOFactoryServiceImpl");
                service = new DAOFactoryServiceImpl();
            }
        }
        return service;
    }
    private static final Logger LOG = Logger.getLogger(DAOFactoryService.class.getName());

    public abstract BookDAO getBookDAO();

    public static void setSt(ServiceTracker<DAOFactoryService, DAOFactoryService> aSt) {
        st = aSt;
    }

}
