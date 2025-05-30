package eclipse_rpc_dc.lang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe responsável por carregar e fornecer traduções de strings do plugin.
 * Utiliza ResourceBundle para gerenciar as traduções em diferentes idiomas.
 * 
 * @author EclipseRPC
 */
public class I18n {
	
	/**
	 * ResourceBundle que contém as traduções.
	 * Inicialmente nulo, será carregado com base no idioma selecionado.
	 */
	private static ResourceBundle bundle;
	
	/**
	 * Carrega o ResourceBundle com base no idioma fornecido.
	 * Se o idioma não for reconhecido, carrega o inglês por padrão.
	 * 
	 * @param lang O código do idioma a ser carregado (ex: "ptbr", "span", "de", "it").
	 * @annotation Deprecated pois o método de carregamento de idiomas foi alterado para aceitar códigos de idioma mais comuns.
	 */
	@SuppressWarnings("deprecation")
	public static void load(String lang) {
		Locale locale;
		switch (lang.toLowerCase()) {
		case "ptbr":
			locale = new Locale("pt", "BR");
			break;
		case "span":
			locale = new Locale("es", "ES");
			break;
		case "de":
			locale = new Locale("de", "DE");
			break;
		case "it":
			locale = new Locale("it", "IT");
			break;
		default:
			locale = new Locale("en", "US");
			break;
		}
		
		/* * Carrega o ResourceBundle com base no idioma selecionado.
		 * O arquivo de propriedades deve estar localizado em eclipse_rpc_dc/lang/i18n.properties
		 * ou em eclipse_rpc_dc/lang/i18n_<lang>.properties, onde <lang> é o código do idioma.
		 */
		bundle = ResourceBundle.getBundle("eclipse_rpc_dc.lang.i18n", locale);
		
	}
	
	/**
	 * Retorna a string traduzida para a chave fornecida.
	 * Se o ResourceBundle não estiver carregado, carrega o idioma padrão (inglês).
	 * 
	 * @param key A chave da string a ser traduzida.
	 * @return A string traduzida correspondente à chave.
	 */
	public static String get(String key) {
		if (bundle == null) {
			load("en");
		}
		return bundle.getString(key);
	}
}
