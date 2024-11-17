package io.games.swings.base;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import io.games.swings.builder.ButtonBuilder;
import io.games.swings.builder.FormBuilder;

public class UtilityBasePanel extends UtilityBasePanelActionHandler {
	static Logger log = Logger.getLogger(UtilityBasePanel.class.getName());

	private JPanel _infoPanel = new JPanel();
	private JPanel _centerPanel = new JPanel();
	private JPanel _buttonPanel = new JPanel();
	private JPanel _treePanel = new JPanel();

	public void buildPanel(String panelName) {
		this.setName(panelName);
		this.setLayout(new BorderLayout());
		log.debug("Building Panel: " + panelName);
		populateInfoPanel();
		populateCenterPanel();
		populateButtonPanel();
		populateTreePanel();
	}

	protected void populateInfoPanel() {
		_infoPanel.setToolTipText("InfoPanel...");
		setBorder(_infoPanel, null);
		JLabel infoLabel = new JLabel();
		infoLabel.setText("This is default InfoLabel, override your label by "
				+ "Overriding this method...");

		_infoPanel.add(infoLabel);
		this.add(_infoPanel, BorderLayout.NORTH);
	}

	protected void populateCenterPanel() {
		_centerPanel.setToolTipText("CenterPanel...");
		setBorder(_centerPanel, null);
		if (FormBuilder.getFormComponentsForPanel(getName()) == null
				|| FormBuilder.getFormComponentsForPanel(getName()).size() == 0) {
			log.debug("Populating Default FormComponants for Panel: "
					+ getName());
			JTextField sampleTextField = new JTextField();
			sampleTextField.setText("Sample TextField");
			sampleTextField.addMouseListener(this);
			_centerPanel.add(sampleTextField);
			_formComponents.put(sampleTextField.getName(), sampleTextField);
		} else {
			log.debug("Populating FormComponents for Panel: " + getName());
			addFormComponentsToPanel(_centerPanel,
					FormBuilder.getFormComponentsForPanel(getName()));
		}
		this.add(_centerPanel, BorderLayout.CENTER);
	}

	protected void populateButtonPanel() {
		_buttonPanel.setToolTipText("ButtonPanel...");
		setBorder(_buttonPanel, null);
		if (ButtonBuilder.getButtonsForPanel(getName()) == null
				|| ButtonBuilder.getButtonsForPanel(getName()).size() == 0) {
			log.debug("Populating Default Buttons for Panel: " + getName());
			JButton okButton = new JButton(OK);
			okButton.setName(okButton.getText());
			okButton.setActionCommand(okButton.getName());
			okButton.addActionListener(this);
			_buttonPanel.add(okButton);
			_buttons.put(okButton.getName(), okButton);
		} else {
			log.debug("Populating Buttons for Panel: " + getName());
			addButtonsToPanel(_buttonPanel,
					ButtonBuilder.getButtonsForPanel(getName()));
		}
		this.add(_buttonPanel, BorderLayout.SOUTH);
	}

	protected void populateTreePanel() {
		_treePanel.setToolTipText("TreePanel...");
		setBorder(_treePanel, null);
		this.add(_treePanel, BorderLayout.WEST);
	}

	/**
	 * @return the _infoPanel
	 */
	public JPanel getInfoPanel() {
		return _infoPanel;
	}

	/**
	 * @return the _centerPanel
	 */
	public JPanel getCenterPanel() {
		return _centerPanel;
	}

	/**
	 * @return the _buttonPanel
	 */
	public JPanel getButtonPanel() {
		return _buttonPanel;
	}

	/**
	 * @return the _treePanel
	 */
	public JPanel getTreePanel() {
		return _treePanel;
	}

}
