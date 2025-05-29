package eclipse_rpc_dc;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.io.IOException;
//import java.time.Instant;

public class DiscordHandler {
	public static void start() throws IOException
	{
		try(CreateParams params = new CreateParams())
		{
			params.setClientID(1377129209306288159L);
			params.setFlags(CreateParams.getDefaultFlags());
			try(Core core = new Core(params))
			{
				try(Activity activity = new Activity())
				{
					activity.setDetails("Teste");
					activity.setState("Teste");

					//activity.timestamps().setStart(Instant.now());

					//activity.assets().setLargeImage("test");


					core.activityManager().updateActivity(activity);
				}

				while(true)
				{
					core.runCallbacks();
					try
					{
						Thread.sleep(16);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}
