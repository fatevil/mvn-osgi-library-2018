package org.lib.integration;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        ServiceTracker<DAOFactoryService, DAOFactoryService> st
                = new ServiceTracker<>(context, DAOFactoryService.class, null);
        st.open(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        DAOFactoryService.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
