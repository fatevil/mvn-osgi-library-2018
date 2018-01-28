package org.lib.business;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        ServiceTracker<LibraryFacadeService, LibraryFacadeService> st = 
                new ServiceTracker<LibraryFacadeService, LibraryFacadeService>(
                        context, 
                        LibraryFacadeService.class, null);
        st.open();
        LibraryFacadeService.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
