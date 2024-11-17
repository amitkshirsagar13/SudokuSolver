package io.games.swings.base;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import io.games.swings.base.constants.UtilityButtonCommands;
import io.games.swings.componants.Button;
import io.games.swings.componants.FormComponent;

public class UtilityBasePanelActionHandler extends JPanel implements
		ActionListener, FocusListener, MouseListener, UtilityButtonCommands,
		ItemListener {
	static Logger log = Logger.getLogger(UtilityBasePanelActionHandler.class
			.getName());

	protected UtilityBaseFrame mainFrame = null;

	/**
	 * @return the _mainFrame
	 */
	public UtilityBaseFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param _mainFrame
	 *            the _mainFrame to set
	 */
	public void setMainFrame(UtilityBaseFrame _mainFrame) {
		mainFrame = _mainFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource().getClass().getName().contains("Label")) {
			if (event.getClickCount() == 2) {
				JOptionPane.showMessageDialog(this,
						((JLabel) event.getSource()).getName() + " : "
								+ ((JLabel) event.getSource()).getText(),
						((JLabel) event.getSource()).getName(),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (event.getSource().getClass().getName().contains("TextField")) {
			if (event.getClickCount() == 3) {
				((JTextField) event.getSource()).setText("");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		final String command = event.getActionCommand();
		Runnable actionRunner = new Runnable() {

			@Override
			public void run() {
				if (command.equalsIgnoreCase(OK)) {
					executeOK();
				} else if (command.equalsIgnoreCase(SUBMIT)) {
					executeSubmit();
				} else if (command.equalsIgnoreCase(CANCEL)) {
					executeCancel();
				} else if (command.equalsIgnoreCase(RESET)) {
					executeReset();
				} else {
					executeNoActionMessage(event);
				}
			}
		};

		Thread actionThread = new Thread(actionRunner, event.getSource()
				.toString());
		actionThread.start();
	}

	/**
	 * 
	 */
	protected void executeNoActionMessage(ActionEvent event) {
		log.debug("Executing NoAction");
		JOptionPane.showMessageDialog(this, event.getActionCommand() + " : "
				+ event.getActionCommand(), event.getSource().toString(),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 */
	protected void executeReset() {
		log.debug("Executing Reset");
	}

	/**
	 * 
	 */
	protected void executeCancel() {
		log.debug("Executing Cancel");
	}

	/**
	 * 
	 */
	protected void executeSubmit() {
		log.debug("Executing Submit");
	}

	/**
	 * 
	 */
	protected void executeOK() {
		log.debug("Executing OK");
	}

	public void setBorder(JPanel panel, Border borderType) {
		if (borderType == null) {
			borderType = BorderFactory.createLoweredBevelBorder();
		}
		panel.setBorder(borderType);
	}

	protected UtilityStatusPanel statusPanel = null;

	/**
	 * 
	 */
	public void setStatusPanel(UtilityStatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	public void setProgressStatus(int progressStatus, String statusMessage) {
		statusPanel.setProgressStatus(progressStatus, statusMessage);
	}

	/**
	 * @param controlPanel
	 * @param buttonList
	 */
	public void addButtonsToPanel(JPanel controlPanel, List<Button> buttonList) {
		for (int i = 0; i < buttonList.size(); i++) {
			Button button = buttonList.get(i);
			if (!_buttons.containsKey(button.getButtonAction())) {
				JButton jButton = new JButton(button.getButtonName());
				jButton.setName(button.getButtonName());
				jButton.setActionCommand(button.getButtonAction());
				jButton.addActionListener(this);
				jButton.setToolTipText(button.getButtonToolTip().toString());
				if (button.getButtonDisabled() != null
						&& button.getButtonDisabled().equalsIgnoreCase(
								"Disabled")) {
					jButton.setEnabled(false);
				}

				if (button.getButtonListeners() != null) {
					if (button.getButtonListeners().contains("FocusListener")) {
						jButton.addFocusListener(this);
					}
					if (button.getButtonListeners().contains("MouseListener")) {
						jButton.addMouseListener(this);
					}
				}
				_buttons.put(jButton.getActionCommand(), jButton);
				controlPanel.add(jButton);
			}
		}

	}

	public void addFormComponentsToPanel(JPanel controlPanel,
			List<FormComponent> formComponentList) {
		controlPanel.setLayout(null);
		for (int i = 0; i < formComponentList.size(); i++) {
			FormComponent formComponent = formComponentList.get(i);
			if (!_formComponents.containsKey(formComponent.getComponentName())) {
				JComponent jComponent = prepareFormComponent(formComponent);
				_formComponents.put(jComponent.getName(), jComponent);
				controlPanel.add(jComponent);
			}
		}

	}

	public JComponent prepareFormComponent(FormComponent formComponent) {
		JComponent jComponent = null;
		if (formComponent.getComponentType().equalsIgnoreCase("JTextField")) {
			jComponent = new JTextField(formComponent.getComponentName());
			if (formComponent.getComponentDisabled() != null
					&& formComponent.getComponentDisabled().equalsIgnoreCase(
							"Disabled")) {
				jComponent.setEnabled(false);
			}
			if (formComponent.getComponentListeners() != null) {
				if (formComponent.getComponentListeners().contains(
						"FocusListener")) {
					jComponent.addFocusListener(this);
				}
				if (formComponent.getComponentListeners().contains(
						"MouseListener")) {
					jComponent.addMouseListener(this);
				}
			}
		} else if (formComponent.getComponentType().equalsIgnoreCase("JLabel")) {
			jComponent = new JLabel(formComponent.getComponentToolTip()
					.toString());

		} else if (formComponent.getComponentType().equalsIgnoreCase(
				"JComboBox")) {
			JComboBox jComponent2 = new JComboBox();
			jComponent2.addItemListener(this);
			jComponent = jComponent2;
		}
		jComponent.setName(formComponent.getComponentName());
		String tooltip = formComponent.getComponentToolTip()
		.toString().equals("") ? formComponent.getComponentName() : formComponent.getComponentToolTip()
		.toString();
		jComponent.setToolTipText(tooltip);
		jComponent.setBounds(
				Integer.parseInt(formComponent.getComponentXPos()),
				Integer.parseInt(formComponent.getComponentYPos()), 30, 30);
		return jComponent;
	}

	protected HashMap<String, JComponent> _formComponents = new HashMap<String, JComponent>();
	protected HashMap<String, JButton> _buttons = new HashMap<String, JButton>();

	/**
	 * @return the _formComponents
	 */
	public HashMap<String, JComponent> getFormComponents() {
		return _formComponents;
	}

	/**
	 * @return the _buttons
	 */
	public HashMap<String, JButton> getButtons() {
		return _buttons;
	}

	public JComponent getFormComponent(String key) {
		return _formComponents.get(key);
	}

	public JButton getButton(String key) {
		return _buttons.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
