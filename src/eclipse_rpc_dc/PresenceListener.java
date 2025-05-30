package eclipse_rpc_dc;

import java.io.IOException;

import eclipse_rpc_dc.discord.DiscordHandler;

public class PresenceListener extends Thread {
	@Override
	public void run() {
		try {
			DiscordHandler.start();
			System.out.println("Plugin Iniciado!");
		}
		catch (IOException e) {
			log(e);
		}
	}
	
	public static void log(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append("Erro: ").append(e.getMessage()).append(" Stack Trace: ").append(e.getStackTrace());
		System.err.print(sb.toString());
	}
}
