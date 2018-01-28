package org.lib.connection;

import java.util.logging.Logger;
import javafx.application.Platform;
import org.lib.richclient.LibObservable;
import org.lib.richclient.MainWindow;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                MainWindow.instance.addTollButton(ConnectAction.instance.createButton());
                MainWindow.instance.addTollButton(DisconnectAction.instance.createButton());
                LibObservable.instance.stateChanged();
            }
        });
        ServiceTracker<LibConnection, LibConnection> st
                = new ServiceTracker<LibConnection, LibConnection>(context,
                        LibConnection.class, null);
        st.open();
        LibConnection.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
