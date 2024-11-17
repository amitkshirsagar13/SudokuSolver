package io.games.swings.base;

/**
 * ProjectName: SwingsStatusPanel
 * @author amit_kshirsagar
 * @date Jan 17, 2014
 */

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;

import io.games.swings.base.model.InfoAccessModel;

public class UtilityStatusPanel extends UtilityBasePanelActionHandler implements
		MouseListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger _log = Logger.getLogger(UtilityStatusPanel.class.getName());

	private static UtilityStatusPanel _statusPanel = null;

	private UtilityStatusPanel() {
	}

	public static UtilityStatusPanel loadPanel(JFrame mainFrame, String layOut) {
		if (_statusPanel == null) {
			_statusPanel = new UtilityStatusPanel();
			// _log.debug("Loaded statusPanel to mainFrame...");
			_statusPanel.loadViewComponants();
			((JPanel) mainFrame.getContentPane()).add(_statusPanel, layOut);
		}
		return _statusPanel;
	}

	private static JLabel _statusBar = new JLabel();
	private JProgressBar _progressBar = new JProgressBar();

	private JPanel _infoPanel = new JPanel();
	private JLabel _customerIndex = new JLabel();
	private JLabel _readWriteLabel = new JLabel();
	private JLabel _userIDLabel = new JLabel();
	private JLabel _privilegedUserLabel = new JLabel();

	private void loadViewComponants() {
		_customerIndex.setName("Customer Index");
		_readWriteLabel.setName("Access Level");
		_userIDLabel.setName("User Name");
		_privilegedUserLabel.setName("Privilaged");
		_statusBar.setName("Status Bar");

		// _statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_customerIndex.setBorder(BorderFactory.createLoweredBevelBorder());
		_readWriteLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_userIDLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_privilegedUserLabel
				.setBorder(BorderFactory.createLoweredBevelBorder());

		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.X_AXIS));
		_infoPanel.add(_customerIndex, null);
		_infoPanel.add(_readWriteLabel, null);
		_infoPanel.add(_userIDLabel, null);
		_infoPanel.add(_privilegedUserLabel, null);

		_statusPanel.setLayout(new BorderLayout());
		_statusPanel.add(_progressBar, BorderLayout.CENTER);

		_progressBar.setMaximum(100);
		_progressBar.setLayout(new BorderLayout());
		_progressBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_progressBar.add(_statusBar);

		_statusPanel.add(_infoPanel, BorderLayout.EAST);
		_customerIndex.setText("CustomerIndex");
		_readWriteLabel.setText(" R ");
		_userIDLabel.setText("        ");
		_privilegedUserLabel.setText(" N ");
		_statusBar.setOpaque(false);
		_customerIndex.setOpaque(true);
		_readWriteLabel.setOpaque(true);
		_userIDLabel.setOpaque(true);
		_privilegedUserLabel.setOpaque(true);
		_customerIndex.setToolTipText("Business Safty with Customer..");
		_readWriteLabel.setToolTipText("Access For Read Write");
		_userIDLabel.setToolTipText("UserID");
		_privilegedUserLabel.setToolTipText("Privileged User");
		_customerIndex.addMouseListener(this);
		_readWriteLabel.addMouseListener(this);
		_userIDLabel.addMouseListener(this);
		_privilegedUserLabel.addMouseListener(this);
		_statusBar.addMouseListener(this);
		_statusBar.setToolTipText("Status Bar Message...");
		statusBarMsg("Status bar working...");

	}

	String EMPTYSTRING = " ";
	String statusMessage = null;

	/**
	 * @param string
	 */
	public void statusBarMsg(String statusMessage) {
		// _statusBar.setText(EMPTYSTRING + statusMessage + EMPTYSTRING);
		this.statusMessage = statusMessage;
		Thread statusThread = new Thread(this);
		statusThread.start();
	}

	public void updateStatusBarMessage() {
		log.debug("Updated StatusBar:" + this.statusMessage);
		_statusBar.setText(EMPTYSTRING + this.statusMessage + EMPTYSTRING);
	}

	@Override
	public void setProgressStatus(int progressStatus, String statusMessage) {
		statusBarMsg(statusMessage);
		_progressBar.setValue(progressStatus);
	}

	public void loadInfoAccessModel(InfoAccessModel infoAccessModel) {
		_customerIndex.setText(EMPTYSTRING + infoAccessModel.getIndex()
				+ EMPTYSTRING);
		_readWriteLabel.setText(EMPTYSTRING + infoAccessModel.getAccess()
				+ EMPTYSTRING);
		_userIDLabel.setText(EMPTYSTRING + infoAccessModel.getName()
				+ EMPTYSTRING);
		_privilegedUserLabel.setText(EMPTYSTRING + infoAccessModel.getRole()
				+ EMPTYSTRING);
		statusBarMsg("InfoAccessModel updated...");
	}

	private static boolean continueMessageThread = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		updateStatusBarMessage();
	}

}
