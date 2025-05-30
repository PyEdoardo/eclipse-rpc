package eclipse_rpc_dc.config;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclipse_rpc_dc.lang.I18n;
import eclipse_rpc_dc.log.Logger;

/**
 * Classe que representa a janela de configurações do plugin Eclipse RPC.
 * Contém opções para selecionar o idioma e ativar/desativar o RPC.
 * Esta classe estende PreferencePage e implementa IWorkbenchPreferencePage
 * para integrar com o sistema de preferências do Eclipse.
 * 
 *  @Implements IWorkbenchPreferencePage
 *  @Extends PreferencePage
 */
public class ConfigWindow extends PreferencePage implements IWorkbenchPreferencePage {

    private Combo languageCombo;
    private Button rpcToggleButton;

    private final ProprietiesInterface propsI = new ProprietiesInterface();

    public ConfigWindow() {
        super("Configurações do Plugin Eclipse RPC");
        Logger.info(I18n.get("log.running"));
    }
    
    /**
	 * Cria o conteúdo da página de preferências.
	 * Aqui, criamos os componentes da interface, como Combo para seleção de idioma
	 * e Button para ativar/desativar o RPC.
	 * 
	 * @param parent O composite pai onde os componentes serão adicionados.
	 * @return O composite contendo os componentes da página de preferências.
	 */
    @Override
    protected Control createContents(Composite parent) {
        ProprietiesInterface.ArquivoProperties props = propsI.loadProperties();
        I18n.load(props.lang); // 🔥 carrega a linguagem aqui

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        Label langLabel = new Label(container, SWT.NONE);
        langLabel.setText(I18n.get("label.language")); // tradução dinâmica
        langLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

        languageCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        languageCombo.setItems(new String[]{
        	    I18n.get("option.portuguese"),
        	    I18n.get("option.english"),
        	    I18n.get("option.spanish"),
        	    I18n.get("option.german"),
        	    I18n.get("option.italian")
        	});
        languageCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Seleciona a linguagem
        switch(props.lang.toLowerCase()) {
        case "ptbr":
            languageCombo.select(0);
            break;
        case "span":
            languageCombo.select(2);
            break;
        case "de":   // alemão
            languageCombo.select(3);
            break;
        case "it":   // italiano
            languageCombo.select(4);
            break;
        default:     // inglês padrão
            languageCombo.select(1);
            break;
    }

        rpcToggleButton = new Button(container, SWT.CHECK);
        rpcToggleButton.setText(I18n.get("checkbox.rpc")); // tradução dinâmica
        rpcToggleButton.setSelection(props.ativo);

        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        gd.horizontalSpan = 2;
        rpcToggleButton.setLayoutData(gd);

        setDescription(I18n.get("page.description")); // título da página

        return container;
    }

    /**
	 * Método chamado quando o usuário clica no botão "OK" na janela de preferências.
	 * Aqui, obtemos o idioma selecionado e se o RPC está ativado ou não,
	 * e então atualizamos as propriedades do plugin.
	 * 
	 * @return true se as alterações foram aplicadas com sucesso, false caso contrário.
	 */
    @Override
    public boolean performOk() {
    	String selected = languageCombo.getText();
    	String selectedLang;

    	if (selected.equals(I18n.get("option.portuguese"))) {
    	    selectedLang = "ptbr";
    	} else if (selected.equals(I18n.get("option.spanish"))) {
    	    selectedLang = "span";
    	} else if (selected.equals(I18n.get("option.german"))) {
    	    selectedLang = "de";
    	} else if (selected.equals(I18n.get("option.italian"))) {
    	    selectedLang = "it";
    	} else {
    	    selectedLang = "en";  // inglês
    	}
    	
        boolean rpcEnabled = rpcToggleButton.getSelection();
        propsI.changeProperties(selectedLang, rpcEnabled);
        Logger.info(I18n.get("log.lang.changed") + selectedLang);
        return true;
    }
    
    /**
	 * Método chamado quando o usuário clica no botão "Restaurar Padrões" na janela de preferências.
	 * Aqui, restauramos os valores padrão para o idioma e o estado do RPC.
	 */
    @Override
    protected void performDefaults() {
        languageCombo.select(1); // padrão: Inglês
        rpcToggleButton.setSelection(false);
        super.performDefaults();
    }

    /**
     * Não faz nada neste método, pois não precisamos de inicialização específica, mas precisa implementar.
     */
    @Override
    public void init(IWorkbench workbench) {
        
    }
}
