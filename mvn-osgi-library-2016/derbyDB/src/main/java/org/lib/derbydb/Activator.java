package org.lib.derbydb;

import java.util.logging.Logger;
import org.lib.integration.DAOFactoryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(Activator.class.getName());
    ServiceReference<DAOFactoryService> sr;

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        DerbyDAOFactoryService ds = new DerbyDAOFactoryService();
        context.registerService(DAOFactoryService.class, ds, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
