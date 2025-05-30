package eclipse_rpc_dc;

import java.io.IOException;

import eclipse_rpc_dc.discord.DiscordHandler;

/**
 * Essa clase roda em uma thread separada e inicia o DiscordHandler.
 * Dessa forma, o plugin pode ser iniciado sem bloquear a thread principal do Eclipse.
 */
public class PresenceListener extends Thread {
	
	/**
	 * Faz o run do thread, que inicia o DiscordHandler.
	 * @Ovveride run método da classe Thread.
	 * @exception IOException se houver um erro ao iniciar o DiscordHandler.
	 */
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
	
	/**
	 * Função para logar erros, só isso.
	 * @param e Exceção que será logada.
	 */
	public static void log(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append("Erro: ").append(e.getMessage()).append(" Stack Trace: ").append(e.getStackTrace());
		System.err.print(sb.toString());
	}
}
