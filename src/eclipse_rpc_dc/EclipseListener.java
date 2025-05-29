package eclipse_rpc_dc;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

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

public class EclipseListener {
	
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
        public Projeto() {
        	tabela.put("java", new Arquivo("iconjava", "Java Class"));
        	tabela.put("xml", new Arquivo("iconxml", "XML"));
        	tabela.put("txt", new Arquivo("icontxt", "Text File"));
        	tabela.put("json", new Arquivo("iconjson", "JSON"));
        	tabela.put("yml", new Arquivo("iconyml", "YML"));
        	tabela.put("proprieties", new Arquivo("iconproprieties", "Proprieties"));
        	tabela.put("md", new Arquivo("iconmd", "Markdown"));
        	tabela.put("kt", new Arquivo("iconkt", "Kotlin Class"));
        	tabela.put("default", new Arquivo("iconjava", "Sleeping.."));
        	tabela.put("enum", new Arquivo("iconenum", "Enumeration"));
        	tabela.put("abstract", new Arquivo("iconabstract", "Abstract Class"));
        	tabela.put("interface", new Arquivo("iconinterface", "Interface"));
        	tabela.put("record", new Arquivo("iconrecord", "Record Class"));
        	tabela.put("annotation", new Arquivo("iconannotation", "Annotation Interface"));
        }
	}
	
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
	            	}
	                else if (!file.getFileExtension().equalsIgnoreCase("java")) {
	                	info.extension = file.getFileExtension();
	                }
	            }
	        }
	    });

	    return info;
	}
	
	public static eclipse_rpc_dc.EclipseListener.Projeto.Arquivo infoArquivo(Projeto proj) {
		if (proj.extension == "interface") {
			return proj.tabela.get("interface");
		}
		else if (proj.extension == "enum") {
			return proj.tabela.get("enum");
		}
		else if (proj.extension == "record") {
			return proj.tabela.get("record");
		}
		else if (proj.extension == "abstract class") {
			return proj.tabela.get("abstract");
		}
		else if (proj.extension == "class") {
			return proj.tabela.get("java");
		}
		else if (proj.extension == "txt") {
			return proj.tabela.get("txt");
		}
		
		else {
			return proj.tabela.get("default");
		}
	}
}
