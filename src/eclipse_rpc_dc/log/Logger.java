package eclipse_rpc_dc.log;

import java.time.Instant;

public class Logger {
	
	public static void error(String message) {
		if (message == null ||  message.isEmpty()) {
			return;
		}
		
		System.err.println("[Eclipse-RPC]"+"["+Instant.now()+"] " + message);
	}
	
	public static void warn(String message) {
		if (message == null ||  message.isEmpty()) {
			return;
		}
		
		System.out.println("[Eclipse-RPC]"+"["+Instant.now()+"] " + message);
	}
	
	public static void info(String message) {
		if (message == null ||  message.isEmpty()) {
			return;
		}
		
		System.out.println("[Eclipse-RPC]"+"["+Instant.now()+"] " + message);
	}
	
}
