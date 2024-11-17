package io.games.swings.base;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import io.games.swings.base.constants.UtilityButtonCommands;

public class UtilityBaseFrame extends JFrame implements ActionListener,
		UtilityButtonCommands {
	static Logger log = Logger.getLogger(UtilityBaseFrame.class.getName());

	private JPanel toolPanel = new JPanel();
	private JTabbedPane tabbedPanel = null;
	private HashMap<String, JPanel> tabbedPanelHash = new HashMap<String, JPanel>();
	private UtilityStatusPanel statusPanel = null;

	// private JPanel _applicationPanel;

	public void buildFrame() {
		setDefaultApplicationSettings();
		populateToolPanel();
		populateTabbedPanel();
		populateStatusPanel();
	}

	/**
	 * 
	 */
	private void setDefaultApplicationSettings() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("nimbusOrange", new Color(40, 225, 40));
					UIManager.put("Table.background", Color.WHITE);
					UIManager.put("Table.alternateRowColor", Color.BLUE);
					UIManager.put("nimbusSelectionBackground", new Color(220,
							220, 220));
					UIManager.put("List[Selected].textBackground", new Color(
							220, 220, 220));
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		this.setSize(new Dimension(1000, 750));

		// _applicationPanel = (JPanel) this.getContentPane();
		this.setLayout(new BorderLayout());
	}

	public void populateToolPanel() {
		log.debug("Populating Tool Panel");
		toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton addToolButton = new JButton("Add");
		addToolButton.setName(addToolButton.getText());
		addToolButton.setActionCommand(addToolButton.getName());
		addToolButton.addActionListener(this);

		JButton removeToolButton = new JButton("Remove");
		removeToolButton.setName(removeToolButton.getText());
		removeToolButton.setActionCommand(removeToolButton.getName());
		removeToolButton.addActionListener(this);

		toolPanel.add(addToolButton);
		toolPanel.add(removeToolButton);
		setBorder(toolPanel, null);
		this.add(toolPanel, BorderLayout.NORTH);
	}

	public void populateTabbedPanel() {
		tabbedPanel = new JTabbedPane();
		this.add(tabbedPanel, BorderLayout.CENTER);
	}

	public void addTabbedPanel(UtilityBasePanel tab) {
		if (!tabbedPanelHash.containsKey(tab.getName())) {
			tabbedPanel.addTab(tab.getName(), tab);
			tab.setStatusPanel(statusPanel);
			tabbedPanelHash.put(tab.getName(), tab);
		}
	}

	public void setSelected(String name) {
		tabbedPanel.setSelectedComponent(tabbedPanelHash.get(name));
	}

	public UtilityBasePanel getTabbedPanel(String tabName) {
		if (tabbedPanelHash.containsKey(tabName)) {
			return (UtilityBasePanel) tabbedPanelHash.get(tabName);
		} else {
			return null;
		}
	}

	public void removeTabbedPanel(String tabName) {
		if (tabbedPanelHash.containsKey(tabName)) {
			tabbedPanel.remove(tabbedPanelHash.get(tabName));
			tabbedPanelHash.remove(tabName);
		}
	}

	public void populateStatusPanel() {
		statusPanel = UtilityStatusPanel.loadPanel(this, BorderLayout.SOUTH);
	}

	public void setProgressStatus(int progressStatus, String statusMessage) {
		statusPanel.setProgressStatus(progressStatus, statusMessage);
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
				} else if (command.equalsIgnoreCase(ADD)
						|| command.equalsIgnoreCase(REMOVE)) {
					executeAddRemove(command);
				} else {
					executeNoActionMessage(event);
				}
			}

		};

		Thread actionThread = new Thread(actionRunner, event.getSource()
				.toString());
		actionThread.start();
	}

	protected void executeAddRemove(String command) {
		if (command.equalsIgnoreCase(ADD)) {
			UtilityBasePanel otherBasePanel = new UtilityBasePanel();
			otherBasePanel.buildPanel("AddedOtherPanel");
			addTabbedPanel(otherBasePanel);
		} else {
			removeTabbedPanel("AddedOtherPanel");
		}
	}

	/**
	 * 
	 */
	protected void executeNoActionMessage(ActionEvent event) {
		log.debug("Executing NoAction");
		JOptionPane.showMessageDialog(this, event.getActionCommand() + " : "
				+ ((JButton) event.getSource()).getName(),
				((JButton) event.getSource()).getName(),
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

}
