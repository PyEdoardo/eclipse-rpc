package eclipse_rpc_dc;

public class PresenceListener extends Thread {
	@Override
	public void run() {
		try {
			DiscordHandler.start();
			System.out.println("Plugin Iniciado!");
		}
		catch (Exception e) {
			log(e);
		}
	}
	
	public static void log(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append("Erro: ").append(e.getMessage()).append(" Stack Trace: ").append(e.getStackTrace());
		System.err.print(sb.toString());
	}
}
