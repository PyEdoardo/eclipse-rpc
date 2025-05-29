package eclipse_rpc_dc;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import java.io.IOException;

//import java.time.Instant;

public class DiscordHandler {
	
	public static void start() throws IOException
	{
		EclipseListener.Projeto projeto;
		try(CreateParams params = new CreateParams())
		{
			params.setClientID(1377129209306288159L);
			params.setFlags(CreateParams.getDefaultFlags());
			try(Core core = new Core(params))
			{
				while (true)
				{
					projeto = EclipseListener.extract();

					try (Activity activity = new Activity())
					{
						activity.setDetails("Editing: " + projeto.fileName + " | " + projeto.currentLine + "/" + projeto.totalLines);
						activity.setState("Workspace: " + projeto.nomeProjeto);
						
						activity.assets().setLargeImage(EclipseListener.infoArquivo(projeto).iconName);
						activity.assets().setLargeText(EclipseListener.infoArquivo(projeto).descricao);
						
						core.activityManager().updateActivity(activity);
					}

					core.runCallbacks();

					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}
