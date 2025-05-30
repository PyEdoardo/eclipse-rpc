package eclipse_rpc_dc.config;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclipse_rpc_dc.lang.I18n;

public class ConfigWindow extends PreferencePage implements IWorkbenchPreferencePage {

    private Combo languageCombo;
    private Button rpcToggleButton;

    private final ProprietiesInterface propsI = new ProprietiesInterface();

    public ConfigWindow() {
        super("Configurações do Plugin Eclipse RPC");
    }

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
        return true;
    }

    @Override
    protected void performDefaults() {
        languageCombo.select(1); // padrão: Inglês
        rpcToggleButton.setSelection(false);
        super.performDefaults();
    }

    @Override
    public void init(IWorkbench workbench) {
        // Nenhuma ação necessária
    }
}
