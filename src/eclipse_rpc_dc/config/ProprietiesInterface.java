package eclipse_rpc_dc.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * ProprietiesInterface.java
 * 
 * Essa classe gerencia o carregamento e a troca de configurações (config.properties) do Plugin.
 * Isso permite que o plugin armazene e recupere suas configurações de forma persistente
 * entre as execuções.
 */
public class ProprietiesInterface {
	
	/**
	 * Altera as propriedades do plugin.
	 * @param lang Idioma do plugin.
	 * @param ativo Se o plugin está ativo ou não.
	 * @return true se as propriedades foram alteradas com sucesso, false caso contrário.
	 */
	public boolean changeProperties(String lang, boolean ativo) {
	    Properties arquivo = new Properties();
	    Path pathConfig = Paths.get(System.getProperty("user.home"), ".eclipse-rpc", "config.properties");

	    try {
	        Files.createDirectories(pathConfig.getParent());

	        arquivo.setProperty("plugin.lang", lang);
	        arquivo.setProperty("rpc.enable", String.valueOf(ativo));

	        try (OutputStream saida = Files.newOutputStream(pathConfig)) {
	            arquivo.store(saida, "Plugin Config");
	            System.out.println("Arquivo salvo em: " + pathConfig.toAbsolutePath());
	            return true;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	/**
	 * Classe interna para armazenar as propriedades do arquivo de configuração.
	 * Contém o idioma e se o plugin está ativo ou não.
	 */
	public static class ArquivoProperties {
		public String lang;
		public boolean ativo;
		public ArquivoProperties(String lang, boolean ativo) {
			this.lang = lang;
			this.ativo = ativo;
		}
	}
	
	/**
	 * Carrega as propriedades do arquivo de configuração.
	 * Por padrão o idioma é "english" e o plugin está ativo.
	 * @return Um objeto ArquivoProperties contendo o idioma e se o plugin está ativo.
	 */
	public ArquivoProperties loadProperties() {
		String lang = "english";
		boolean ativo = true;
		
		Properties arquivo = new Properties();
		Path pathConfig = Paths.get(System.getProperty("user.home"), ".eclipse-rpc", "config.properties");
		try {
		Files.createDirectories(pathConfig.getParent());
			try (InputStream stream = Files.newInputStream(pathConfig)){
				arquivo.load(stream);
				lang = arquivo.getProperty("plugin.lang");
				ativo = Boolean.parseBoolean(arquivo.getProperty("rpc.enable"));
				} 
			} catch (IOException e) {
				e.printStackTrace();System.err.println(e.getMessage());
		}
		return new ArquivoProperties(lang, ativo);
	}
	
	/**
	 * Retorna as propriedades do arquivo de configuração.
	 * @return Um objeto Properties contendo as propriedades do arquivo de configuração.
	 */
	public Properties returnProperties() {
		Properties arquivo = new Properties();
		Path pathConfig = Paths.get(System.getProperty("user.home"), ".eclipse-rpc", "config.properties");
		try {
		Files.createDirectories(pathConfig.getParent());
			try (InputStream stream = Files.newInputStream(pathConfig)){
				arquivo.load(stream);
				} 
			} catch (IOException e) {
				e.printStackTrace();System.err.println(e.getMessage());
		}
		return arquivo;
	}
	
	/**
	 * Retorna o caminho do arquivo de configuração.
	 * @return Um objeto Path representando o caminho do arquivo de configuração.
	 */
	public Path returnConfigPath() {
		Path pathConfig = Paths.get(System.getProperty("user.home"), ".eclipse-rpc", "config.properties");
		return pathConfig;
	}
}
