package eclipse_rpc_dc.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ProprietiesInterface {
	
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
	
	public static class ArquivoProperties {
		public String lang;
		public boolean ativo;
		public ArquivoProperties(String lang, boolean ativo) {
			this.lang = lang;
			this.ativo = ativo;
		}
	}
	
	public ArquivoProperties loadProperties() {
		String lang = "english"; //Caso dê alguma excessão no arquivo ele vem setado inglês e ativo.
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
	
	public Path returnConfigPath() {
		Path pathConfig = Paths.get(System.getProperty("user.home"), ".eclipse-rpc", "config.properties");
		return pathConfig;
	}
}
