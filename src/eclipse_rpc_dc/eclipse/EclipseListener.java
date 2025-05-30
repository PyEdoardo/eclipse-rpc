package eclipse_rpc_dc.eclipse;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Display;

/**
 * EclipseListener.java
 * 
 * Essa classe é responsável por extrair informações sobre o projeto atual,
 * arquivo sendo editado e o tipo de arquivo no Eclipse.
 */
public class EclipseListener {
	
	/**
	 * Classe que representa as informações do projeto e do arquivo atual no Eclipse.
	 * Contém o nome do projeto, nome do arquivo, extensão do arquivo, tipo de arquivo,
	 * linha atual e total de linhas.
	 */
	public static class Projeto{
		public String nomeProjeto;
		public String fileName;
        public String extension;
        public Map<String, Arquivo> tabela = new HashMap<>();
        public Arquivo tipo;
        public int currentLine;
        public int totalLines;
        public static class Arquivo {
    		public String iconName, descricao;
    		
    		public Arquivo(String icone, String desc) {
    			this.iconName = icone;
    			this.descricao = desc;
    		}
    	}
        /**
		 * Construtor da classe Projeto.
		 * Inicializa a tabela de tipos de arquivos com ícones e descrições.
		 * É usado apenas como um cache para associar a extensão do arquivo com um ícone e descrição.
		 */
        public Projeto() {
        	tabela.put("java", new Arquivo("iconjava", "Java Class")); //Tem img
        	tabela.put("xml", new Arquivo("iconxml", "XML")); //Tem img
        	tabela.put("txt", new Arquivo("icontxt", "Text File")); //Tem img
        	tabela.put("json", new Arquivo("iconjson", "JSON")); //Tem img
        	tabela.put("yml", new Arquivo("iconyaml", "YML")); //Tem img
        	tabela.put("proprieties", new Arquivo("iconproprieties", "Proprieties"));
        	tabela.put("md", new Arquivo("iconmd", "Markdown"));
        	tabela.put("kt", new Arquivo("iconkt", "Kotlin Class"));
        	tabela.put("default", new Arquivo("iconjava", "Sleeping.."));
        	tabela.put("enum", new Arquivo("iconenum", "Enumeration")); //Tem img
        	tabela.put("abstract", new Arquivo("iconabstract", "Abstract Class")); //Tem img
        	tabela.put("interface", new Arquivo("iconinterface", "Interface")); //Tem img
        	tabela.put("record", new Arquivo("iconrecord", "Record Class")); //Tem img
        	tabela.put("annotation", new Arquivo("iconannotation", "Annotation Interface"));
        }
	}
	
	/**
	 * Método estático que extrai informações do projeto atual e do arquivo sendo editado no Eclipse.
	 * Ele coleta o nome do projeto, nome do arquivo, extensão do arquivo, tipo de arquivo,
	 * linha atual e total de linhas.
	 * 
	 * @return Um objeto Projeto contendo as informações extraídas.
	 * @throws Exception Se ocorrer algum erro durante a extração das informações.
	 */
	public static Projeto extract() {
	    Projeto info = new Projeto();

	    Display.getDefault().syncExec(() -> {
	        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	        if (window == null) return;

	        IWorkbenchPage page = window.getActivePage();
	        if (page == null) return;

	        IEditorPart editor = page.getActiveEditor();
	        if (editor == null) return;

	        IEditorInput input = editor.getEditorInput();
	        IFile file = input.getAdapter(IFile.class);

	        if (file != null) {
	            info.nomeProjeto = file.getProject().getName();
	            info.fileName = file.getName();

	            if (editor instanceof ITextEditor) {
	                ITextEditor textEditor = (ITextEditor) editor;
	                ITextSelection selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
	                info.currentLine = selection.getStartLine() + 1;

	                IDocument doc = textEditor.getDocumentProvider().getDocument(input);
	                info.totalLines = doc.getNumberOfLines();
	                System.out.println(file.getFileExtension());
	                 //Detectar o tipo do arquivo
	                if (file.getFileExtension().equalsIgnoreCase("java")) {
	                System.out.println("entrou no if");
	                String content = doc.get();
	                if (content.contains("interface ")) {
	                    info.extension = content.contains("@interface") ? "annotation" : "interface";
	                    System.out.println("é interface");
	                } else if (content.contains("enum ")) {
	                    info.extension = "enum";
	                    System.out.println("é enum");
	                } else if (content.contains("record ")) {
	                    info.extension = "record";
	                    System.out.println("é record");
	                } else if (content.contains("abstract class ")) {
	                    info.extension = "abstract class";
	                    System.out.println("é classe abstrata");
	                } else if (content.contains("class ")) {
	                    info.extension = "class";
	                    System.out.println("é classe");
	                	}
	                else {
	                	info.extension = "java";
	                	}
	            	}
//	                else if (!file.getFileExtension().equalsIgnoreCase("java")) {
//	                	info.extension = file.getFileExtension();
//	                }
	                else {
	                	info.extension = file.getFileExtension();
	                }
	            }
	        }
	    });

	    return info;
	}
	
	/**
	 * Método estático que retorna as informações do arquivo atual baseado na extensão do arquivo.
	 * Ele utiliza a tabela de tipos de arquivos definida na classe Projeto para retornar o ícone e descrição apropriados.
	 * 
	 * @param proj O objeto Projeto contendo as informações do arquivo atual.
	 * @return Um objeto Arquivo contendo o ícone e descrição do tipo de arquivo.
	 */
	public static eclipse_rpc_dc.eclipse.EclipseListener.Projeto.Arquivo infoArquivo(Projeto proj) {
		if (proj.extension.equalsIgnoreCase("interface")) {
			return proj.tabela.get("interface");
		}
		else if (proj.extension.equalsIgnoreCase("enum")) {
			return proj.tabela.get("enum");
		}
		else if (proj.extension.equalsIgnoreCase("record")) {
			return proj.tabela.get("record");
		}
		else if (proj.extension.equalsIgnoreCase("abstract class")) {
			return proj.tabela.get("abstract");
		}
		else if (proj.extension.equalsIgnoreCase("class")) {
			return proj.tabela.get("java");
		}
		else if (proj.extension.equalsIgnoreCase("txt")) {
			return proj.tabela.get("txt");
		}
		else if (proj.extension.equalsIgnoreCase("json")) {
			return proj.tabela.get("json");
		}
		else if (proj.extension.equalsIgnoreCase("annotation")) {
			return proj.tabela.get("annotation");
		}
		else if (proj.extension.equalsIgnoreCase("xml")) {
			return proj.tabela.get("xml");
		}
		else if (proj.extension.equalsIgnoreCase("yml")) {
			return proj.tabela.get("yml");
		}
		else if (proj.extension.equalsIgnoreCase("yaml")) {
			return proj.tabela.get("yml");
		}
		else {
			return proj.tabela.get("default");
		}
	}
}
