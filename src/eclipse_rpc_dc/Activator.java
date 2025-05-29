package eclipse_rpc_dc;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	@Override
    public void start(BundleContext context) {
        System.out.println("[DiscordPresence] Plugin iniciado.");
        new PresenceListener().start();
    }
	
	@Override
    public void stop(BundleContext context) {
        System.out.println("[DiscordPresence] Plugin finalizado.");
    }
}
