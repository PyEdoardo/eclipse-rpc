package eclipse_rpc_dc.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.LogLevel;
import de.jcm.discordgamesdk.activity.Activity;
import eclipse_rpc_dc.config.ProprietiesInterface;
import eclipse_rpc_dc.eclipse.EclipseListener;
import eclipse_rpc_dc.lang.I18n;
import eclipse_rpc_dc.log.Logger;

import java.io.IOException;
import java.time.Instant;

/**
 * Classe responsável por gerenciar a conexão com o Discord e atualizar o status do usuário.
 * Utiliza a biblioteca Discord Game SDK para criar uma instância do Core e enviar atualizações de atividade.
 */
public class DiscordHandler {
	
	/**
	 * Método principal que inicia o handler do Discord.
	 * Carrega as propriedades do arquivo de configuração, verifica se o RPC está ativo,
	 * e inicia a conexão com o Discord, atualizando o status do usuário em um loop infinito.
	 *
	 * @throws IOException Se ocorrer um erro ao carregar as propriedades.
	 */
	public static void start() throws IOException {
	    ProprietiesInterface config = new ProprietiesInterface();
	    ProprietiesInterface.ArquivoProperties props = config.loadProperties();
	    eclipse_rpc_dc.lang.I18n.load(props.lang);

	    EclipseListener.Projeto projeto;
	    
	    /**
	     * Verifica se o arquivo de propriedades foi carregado corretamente
	     */
	    if (!props.ativo) {
	        Logger.warn(I18n.get("log.rpc.disabled"));
	        return;
	    }
	    try (CreateParams params = new CreateParams()) {
	        params.setClientID(1377129209306288159L);
	        params.setFlags(CreateParams.getDefaultFlags());
	        Logger.info(I18n.get("log.running"));
	        
	        /**
	         * Cria uma instância do Core do Discord SDK, que é responsável por gerenciar a conexão com o Discord
	         * e enviar atualizações de atividade.
	         * O logHook é configurado para exibir apenas mensagens importantes no log.
	         */
	        try (Core core = new Core(params)) {
	        	core.setLogHook(LogLevel.WARN, (lvl, msg) -> {System.out.println("[Discord SDK] [" + lvl + "] " + msg);});
	            Instant startTime = Instant.now();

	            while (true) {
	                projeto = EclipseListener.extract();
	                try (Activity activity = new Activity()) {
	                    activity.timestamps().setStart(startTime);

	                    String editLabel = I18n.get("rpc.edit");
	                    String workspaceLabel = I18n.get("rpc.workspace");

	                    activity.setDetails(editLabel + " " + projeto.fileName + " | " + projeto.currentLine + "/" + projeto.totalLines);
	                    activity.setState(workspaceLabel + " " + projeto.nomeProjeto);
	                    
	                    activity.assets().setLargeImage(EclipseListener.infoArquivo(projeto).iconName);
	                    activity.assets().setLargeText(EclipseListener.infoArquivo(projeto).descricao);
	                    
	                    activity.assets().setSmallImage("iconeclipse");
	                    activity.assets().setSmallText("Eclipse IDE");            

	                    core.activityManager().updateActivity(activity);
	                }
	                /**
	                 * Atualiza o status do usuário no Discord com as informações do projeto atual.
	                 * O status inclui detalhes sobre o arquivo sendo editado, o nome do projeto e a linha atual.
	                 */
	                core.runCallbacks();

	                try {
	                	/** * Aguarda 1 segundo antes de atualizar novamente o status do usuário. */
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
}
