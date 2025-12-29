package kirjanpito.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Holvi tuonnin ikkuna.
 * 
 * @author Eetu Kallio
 */
public class HolviImportDialog extends JDialog {
    
    private JTextField urlTextField;

    private int result;

    private static final long serialVersionUID = 1L;

    public HolviImportDialog(Frame owner) {
		super(owner, "Procountor -tuonti", true);
	}

    /**
	 * Luo ikkunan komponentit.
	 */
	public void create() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(460, 200));
		createContentPanel();
		createButtonPanel();
		pack();
		setLocationRelativeTo(getOwner());
	}

    private void createContentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.CENTER);

        urlTextField = new JTextField();

        addComponent(panel, 0, "Tiedosto URL:", urlTextField);
    }

    /**
	 * Palauttaa ikkunan tuloksen.
	 * 
	 * @return JOptionPane.OK_OPTION, jos käyttäjä on klikannut
	 * OK-painiketta; JOptionPane.CANCEL_OPTION, jos käyttäjä
	 * on klikannut Peruuta-painiketta
	 */
	public int getResult() {
		return result;
	}

    /**
	 * Näyttää virheilmoituksen.
	 * 
	 * @param message teksti
	 */
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this,
				message, Kirjanpito.APP_NAME,
				JOptionPane.ERROR_MESSAGE);
	}

    /**
	 * Palauttaa tiedoston URL:n.
	 * 
	 * @return Tiedoston URL
	 */
	public String getURL() {
		return urlTextField.getText();
	}

	/**
	 * Asettaa tiedoston URL:in.
	 * 
	 * @param url Tiedoston URL
	 */
	public void setURL(String url) {
		urlTextField.setText(url);
	}

    /**
	 * Lisää Swing-komponentin <code>comp</code> säiliöön <code>container</code>
	 * riville <code>rowIndex</code> ja lisää komponentin eteen tekstin
	 * <code>labelText</code>.
	 * 
	 * @param container säiliö, johon komponentti lisätään
	 * @param rowIndex rivinumero
	 * @param labelText teksti
	 * @param comp lisättävä komponentti
	 */
	private void addComponent(JPanel container,
    int rowIndex, String labelText, JComponent comp)
    {
        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridy = rowIndex;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(4, 5, 4, 5);
        container.add(new JLabel(labelText), c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 1;
        c.insets = new Insets(4, 5, 4, 5);
        container.add(comp, c);
    }

	/**
	 * Luo ikkunan painikkeet. (OK/Peruuta)
	 */
    private void createButtonPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 6;
		c.ipadx = 30;
		c.insets = new Insets(5, 10, 10, 2);
		
		JPanel panel = new JPanel(new GridLayout(1, 2, 4, 0));
		JPanel container = new JPanel(new GridBagLayout());
		
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 2, 10, 5);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 1.0;
		c.insets = new Insets(5, 5, 10, 10);
		container.add(panel, c);
		add(container, BorderLayout.SOUTH);
		
		JButton cancelButton = new JButton("Peruuta");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = JOptionPane.CANCEL_OPTION;
				setVisible(false);
			}
		});
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = JOptionPane.OK_OPTION;
				setVisible(false);
			}
		});
		
		panel.add(cancelButton);
		panel.add(okButton);
	}
}
