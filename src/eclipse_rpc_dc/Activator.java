package eclipse_rpc_dc;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Classe que implementa o BundleActivator para o plugin de presença do Discord. 
 * @interface BundleActivator
 */
public class Activator implements BundleActivator {
	
	/**
	 * Função implementada que é chamada quando o plugin é iniciado
	 * @param context O contexto do bundle
	 */
	@Override
    public void start(BundleContext context) {
        System.out.println("[DiscordPresence] Plugin iniciado.");
        new PresenceListener().start();
    }
	
	/**
	 * Função implementada que é chamada quando o plugin é parado
	 * @param context O contexto do bundle
	 */
	@Override
    public void stop(BundleContext context) {
        System.out.println("[DiscordPresence] Plugin finalizado.");
    }
}
