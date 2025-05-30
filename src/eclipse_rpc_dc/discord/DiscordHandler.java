package eclipse_rpc_dc.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import eclipse_rpc_dc.config.ProprietiesInterface;
import eclipse_rpc_dc.eclipse.EclipseListener;
import eclipse_rpc_dc.lang.I18n;

import java.io.IOException;
import java.time.Instant;

public class DiscordHandler {

	public static void start() throws IOException {
	    ProprietiesInterface config = new ProprietiesInterface();
	    ProprietiesInterface.ArquivoProperties props = config.loadProperties();

	    if (!props.ativo) {
	        System.out.println("RPC Desligado!");
	        return;
	    }

	    // 🔥 Carrega o idioma com base nas preferências
	    eclipse_rpc_dc.lang.I18n.load(props.lang);

	    EclipseListener.Projeto projeto;

	    try (CreateParams params = new CreateParams()) {
	        params.setClientID(1377129209306288159L);
	        params.setFlags(CreateParams.getDefaultFlags());

	        try (Core core = new Core(params)) {
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

	                    core.activityManager().updateActivity(activity);
	                }

	                core.runCallbacks();

	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
}
