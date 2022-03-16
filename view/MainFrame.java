package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KanbanPanel kanbanPanel;
	private HomePanel homePanel;
	private FormPanel formPanel;
	private ProfilePanel profilePanel;
	private ReportsPanel reportsPanel;
	private ControlPanel controlPanel;
	private TalkerPanel talkerPanel;
	private Toolbar toolbar;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private JTabbedPane tabbedPane;

	public MainFrame() {
		super("Attic Scheduler");
		setVisible(true);
		pack();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(1600, 600));
		setMinimumSize(new Dimension(800, 300));
		setResizable(false);
		setLocationRelativeTo(null);

		controller = new Controller();
		kanbanPanel = new KanbanPanel();
		homePanel = new HomePanel();
		profilePanel = new ProfilePanel();
		reportsPanel = new ReportsPanel();
		controlPanel = new ControlPanel();
		talkerPanel = new TalkerPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		tablePanel = new TablePanel();
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(50, 168, 168));

		setJMenuBar(createMenuBar());

		add(formPanel, BorderLayout.EAST);
		add(toolbar, BorderLayout.WEST);
		add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Home", homePanel);
		tabbedPane.addTab("Backlog", tablePanel);
		tabbedPane.addTab("Kanban Panel", kanbanPanel);
		tabbedPane.addTab("Profile", profilePanel);
		tabbedPane.addTab("Reports", reportsPanel);
		tabbedPane.addTab("Settings", controlPanel);
		tabbedPane.addTab("Talker", talkerPanel);

		fileChooser.addChoosableFileFilter(new IssueFileFilter());
		tablePanel.setData(controller.getIssueList());

		talkerPanel.setTalkerListener(new TalkerListener() {
			public void talkerEventOccured(TalkerEvent te) {
				talkerPanel.setTalkFlowText(controller.getCurrentUser(), te.getMessage());
			}
		});

		kanbanPanel.setCardListener(new TaskCardListener() {
			public void cardRemoved(int paneNo, int cardNo, Component cardComp) {
				controller.removeIssue(cardNo);
				tablePanel.refresh();
			}

			public void editCard(int paneId, int cardNo) {
			}
		});

		profilePanel.setProfileListener(new ProfileListener() {
			public void profileEventHappened(ProfileEvent pe) {
				if (pe.getLoggedOut()) {
					controller.logOutCurrentUser();
					toolbar.logOut();
					profilePanel.logOutUser();
				} else if(pe.getEditCommand() == "Save") {
//					profilePanel.getCountryList(controller.getCountryList());
				} else {
					controller.deleteRequest(pe.getDeleteRequest(), pe.getConfirmPass());
					controller.setUserDetails(pe.getProfileDetails());
				}
			}
		});

		tablePanel.setIssueTableListener(new IssueTableListener() {
			public void rowDeleted(int row) {
				controller.removeIssue(row);
//				taskCards.remove(row);
			}

			public void cellEdited(int row, int col, Object editedValue) {
				controller.editIssue(row, editedValue);
			}
		});

		toolbar.setSignUpListener(new SignUpListener() {
			public void signUpEventOccured(SignUpEvent sue) {
				if (sue.retrieveCountries()) {
					toolbar.getCountryList(controller.getCountryList());

				} else {
					controller.putToUserMap(sue.getUserName(), sue.getPassword());
					controller.addUser(sue);
//					formPanel.getUserNameList();
				}
			}
		});

		toolbar.setLogInListener(new LogInListener() {
			public void logInEventOccured(LogInEvent lie) {
				if (controller.checkForValid(lie.getUserName(), lie.getPassword()) == true) {
					lie.setGranted();
					controller.setCurrentUser(lie.getUserName());

					ArrayList<String> userDetails = controller.getUserDetails(lie.getUserName());
					
					profilePanel.getCountryList(controller.getCountryList());
					profilePanel.userInfoDetails(true, userDetails.get(0), userDetails.get(1), userDetails.get(2),
							userDetails.get(3).toCharArray(), userDetails.get(4), Integer.valueOf(userDetails.get(5)),
							userDetails.get(6), userDetails.get(7), userDetails.get(8), userDetails.get(9),
							Integer.valueOf(userDetails.get(10)), userDetails.get(11), userDetails.get(12),
							userDetails.get(13), userDetails.get(14), userDetails.get(15));

					profilePanel.revalidate();

				} else {

					System.out.println("Access is not granted");
				}
			}
		});

		toolbar.setToolListener(new ToolListener() {
			public void toolEventOccured(ActionEvent te) {
				if (te.getActionCommand() == "Calendar Clicked") {
					Calendar calendar = new Calendar();

					if (!tabbedPane.getSelectedComponent().equals(calendar))
						;
					{
						tabbedPane.addTab("Calendarinatior", calendar);
						tabbedPane.setSelectedComponent(calendar);
						calendar.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent me) {
								if (tabbedPane.findComponentAt(getMousePosition()) == me.getSource()) {
									System.out
											.println("change this so that it is closed when the new panel is closed.");
									tabbedPane.remove(calendar);
								}
							}
						});
						calendar.initialize();
					}

				} else if (te.getActionCommand() == "Calculator Clicked") {
					CalcUI calculator = new CalcUI();
					if (!tabbedPane.getSelectedComponent().equals(calculator)) {
						tabbedPane.addTab("Calculatinator", calculator);
						tabbedPane.setSelectedComponent(calculator);
						calculator.init();
					}

				} else if (te.getActionCommand() == "Profile Clicked") {
					if (!tabbedPane.getSelectedComponent().equals(profilePanel)) {
						tabbedPane.setSelectedComponent(profilePanel);
					}
				} else if (te.getActionCommand() == "Reports Clicked") {
					if (!tabbedPane.getSelectedComponent().equals(reportsPanel)) {
						tabbedPane.setSelectedComponent(reportsPanel);
					}
				} else if (te.getActionCommand() == "Settings Clicked") {
					if (!tabbedPane.getSelectedComponent().equals(controlPanel)) {
						tabbedPane.setSelectedComponent(controlPanel);
					}
				} else if (te.getActionCommand() == "Talker Clicked") {
					if (!tabbedPane.getSelectedComponent().equals(talkerPanel)) {
						tabbedPane.setSelectedComponent(talkerPanel);
					}
				}
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent fv) {
				String stat = fv.getStatus();
				String type = fv.getType();
				String subTaskId = fv.getSubTaskId();

				Tip tip = null;
				if (type.equals("Bug")) {
					tip = Tip.Bug;
				} else if (type.equals("Feature")) {
					tip = Tip.Feature;
				} else if (type.equals("Story")) {
					tip = Tip.Story;
				}

				Status state = null;
				if (stat.equals("Backlog")) {
					state = Status.Backlog;
				} else if (stat.equals("Selected for Development")) {
					state = Status.SelectedForDevelopment;
				} else if (stat.equals("In Progress")) {
					state = Status.InProgress;
				} else if (stat.equals("Development Done")) {
					state = Status.DevelopmentDone;
				} else if (stat.equals("Peer Review")) {
					state = Status.PeerReview;
				} else if (stat.equals("Finished")) {
					state = Status.Finished;
				}

				if (subTaskId.isEmpty()) {
					controller.addIssue(fv);
					controller.putToIssueMap(fv.getIssueId(), fv.getIssueName());
					tablePanel.refresh();

					kanbanPanel.receiveStatus(state);
					kanbanPanel.receiveType(tip);
					kanbanPanel.addCard(fv.getIssueId(), fv.getIssueName(), fv.getType(), fv.getStatus());
//					taskCards.addCard(fv.getIssueId(), fv.getIssueName(), fv.getStatus(), fv.getType());
				}

				else {
					controller.addIssue(fv);
					controller.putToIssueMap(fv.getIssueId(), fv.getIssueName());
					tablePanel.refresh();
					kanbanPanel.receiveStatus(state);
					kanbanPanel.receiveType(tip);
					kanbanPanel.addCard(fv.getIssueId(), fv.getIssueName());
//					kanbanPanel.addCard(fv.getIssueId(), fv.getIssueName(), fv.getStatus(), fv.getType());
				}
				if (state == Status.Backlog) {
					tabbedPane.setSelectedComponent(tablePanel);
				} else {
					tabbedPane.setSelectedComponent(kanbanPanel);
				}
			}
		});
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 156, 99));
		menuBar.setBorder(BorderFactory.createEtchedBorder());
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu windowMenu = new JMenu("Window");
		JMenu helpMenu = new JMenu("Help");
		JMenu exportOperations = new JMenu("Export Operations");

		fileMenu.setForeground(Color.WHITE);
		editMenu.setForeground(Color.WHITE);
		windowMenu.setForeground(Color.WHITE);
		helpMenu.setForeground(Color.WHITE);

		JMenuItem newFile = new JMenuItem("New File");
		JMenuItem openFile = new JMenuItem("Open File");
		JMenuItem recentFiles = new JMenuItem("Recent Files");
		JMenuItem exportUsers = new JMenuItem("Export Users");
		JMenuItem exportTasks = new JMenuItem("Export Tasks");
		JMenuItem exportSettings = new JMenuItem("Export Settings");
		JMenuItem saveFile = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As...");
		JMenuItem logIn = new JMenuItem("Log in");
		JMenuItem logOut = new JMenuItem("Log Out");
		JMenuItem exit = new JMenuItem("Exit");

		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		JMenuItem find = new JMenuItem("Find");
		JMenuItem findAndReplace = new JMenuItem("Find and Replace");
		JMenuItem addBookmark = new JMenuItem("Add Bookmark");

		JCheckBoxMenuItem showForm = new JCheckBoxMenuItem("Form Panel");
		JCheckBoxMenuItem showToolbar = new JCheckBoxMenuItem("Toolbar");
		JCheckBoxMenuItem showTablePanel = new JCheckBoxMenuItem("Backlog Table");
		JCheckBoxMenuItem darkMode = new JCheckBoxMenuItem("Dark Mode");

		showForm.setSelected(true);
		showToolbar.setSelected(true);
		showTablePanel.setSelected(true);
		darkMode.setSelected(false);

		/*
		 * if(controller.getCurrentUser() == null) { logOut.setVisible(false);
		 * logIn.setVisible(true); } else { logOut.setVisible(true);
		 * logOut.setVisible(false); }
		 */

		showForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		showToolbar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		showTablePanel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		darkMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		recentFiles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK));
		logIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		findAndReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		addBookmark.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.SHIFT_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

		showForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isClicked = showForm.isSelected();
				formPanel.setVisible(isClicked);
			}
		});

		showToolbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isClicked = showToolbar.isSelected();
				toolbar.setVisible(isClicked);
			}
		});

		showTablePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isClicked = showTablePanel.isSelected();
				tabbedPane.setVisible(isClicked);
			}
		});

		exportUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exportUsers == (JMenuItem) e.getSource()) {
					if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
						try {
							controller.exportUsers(fileChooser.getSelectedFile());
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(MainFrame.this, "Unable to save file", "Fatal Error",
									JOptionPane.ERROR_MESSAGE);
						}
						System.out.println("working");
					}
				}
			}
		});

		darkMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (darkMode.isSelected() == true) {
					JOptionPane.showConfirmDialog(MainFrame.this, "Turning Dark Mode On", "Please Wait",
							JOptionPane.OK_OPTION);
					profilePanel.setDarkMode(true);
					kanbanPanel.setDarkMode(true);
					toolbar.setDarkMode(true);
					controlPanel.setDarkMode(true);
					formPanel.setDarkMode(true);
					homePanel.setDarkMode(true);
					reportsPanel.setDarkMode(true);
					tablePanel.setDarkMode(true);
					talkerPanel.setDarkMode(true);
				} else {
					JOptionPane.showConfirmDialog(MainFrame.this, "Turning Dark Mode Off", "Please Wait",
							JOptionPane.OK_OPTION);
					profilePanel.setDarkMode(false);
					kanbanPanel.setDarkMode(false);
					toolbar.setDarkMode(false);
					controlPanel.setDarkMode(false);
					formPanel.setDarkMode(false);
					homePanel.setDarkMode(false);
					reportsPanel.setDarkMode(false);
					tablePanel.setDarkMode(false);
					talkerPanel.setDarkMode(false);

				}
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "All that is not saved will be lost",
						"Are you sure", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to save file", "Fatal Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to save file", "Fatal Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuItem clicked = (JMenuItem) e.getSource();
				if (clicked == logOut) {
					int result = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to log out?",
							"Sign Out", JOptionPane.YES_NO_OPTION);
					if (result == 0) {
						controller.logOutCurrentUser();
						toolbar.logOut();
						System.out.println("user logged out");
						menuBar.revalidate();
					}
				}
			}
		});

		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuItem clicked = (JMenuItem) e.getSource();
				if (clicked == logIn) {
					System.out.println("LogIn attempt via MenuBar");
					toolbar.clickLogin();
					menuBar.revalidate();
				}
			}
		});
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						controller.loadCSVFile(fileChooser.getSelectedFile());
						kanbanPanel.tasksLoaded();
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to load file", "Fatal Error",
								JOptionPane.ERROR_MESSAGE);
					}
				;
			}
		});

		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
					;
			}
		});

		JMenuItem about = new JMenuItem("About Attic Scheduler");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuItem clicked = (JMenuItem) e.getSource();
				if (clicked == about) {
					JOptionPane.showMessageDialog(MainFrame.this,
							"Attic Scheduler Tool\n|||Designed and developed by Attic Inc.|||\n|||2020|||\n", "About",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		menuBar.add(fileMenu);
		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.add(recentFiles);
		fileMenu.addSeparator();
		fileMenu.add(exportOperations);
		fileMenu.addSeparator();
		fileMenu.add(saveFile);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(logIn);
		fileMenu.add(logOut);
		fileMenu.add(exit);

		exportOperations.add(exportUsers);
		exportOperations.add(exportSettings);
		exportOperations.add(exportTasks);

		menuBar.add(editMenu);
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.addSeparator();
		editMenu.add(find);
		editMenu.add(findAndReplace);
		editMenu.add(addBookmark);

		menuBar.add(windowMenu);
		windowMenu.add(showForm);
		windowMenu.add(showToolbar);
		windowMenu.add(showTablePanel);
		windowMenu.addSeparator();
		windowMenu.add(darkMode);

		menuBar.add(helpMenu);
		helpMenu.add(about);

		return menuBar;
	}
}