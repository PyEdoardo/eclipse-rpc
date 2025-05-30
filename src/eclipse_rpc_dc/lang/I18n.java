package eclipse_rpc_dc.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
	
	private static ResourceBundle bundle;
	
	@SuppressWarnings("deprecation")
	public static void load(String lang) {
		Locale locale;
		switch (lang.toLowerCase()) {
		case "ptbr":
			locale = new Locale("pt", "BR");
			break;
		case "en":
			locale = new Locale("en", "US");
			break;
		case "span":
			locale = new Locale("es", "ES");
			break;
		default:
			locale = new Locale("en", "US");
			break;
		}
		
		bundle = ResourceBundle.getBundle("eclipse_rpc_dc.lang.i18n", locale);
		
	}
	
	public static String get(String key) {
		if (bundle == null) {
			load("en"); //Padrão para inglês se o bundle não estiver carregado
		}
		return bundle.getString(key);
	}
}
