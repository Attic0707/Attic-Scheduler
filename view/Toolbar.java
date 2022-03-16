package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Toolbar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton login, profile, calc, calendar, reports, talker, feedback, settings, signUp;
	private ToolListener listener;
	private SignUpListener signUpListener;
	private LogInListener logInListener;
	private JTextField timeStamp;
	private ArrayList<String> countriesFromDB;

	public void setSignUpListener(SignUpListener listens) {
		this.signUpListener = listens;
	}

	public void setLogInListener(LogInListener listener) {
		this.logInListener = listener;
	}

	public void setToolListener(ToolListener listen) {
		this.listener = listen;
	}

	public void getCountryList(ArrayList<String> countriesInDB) {
		this.countriesFromDB = countriesInDB;
	} 
	public JComboBox<String> generateCountryComboBox() {
		JComboBox<String> countryList = new JComboBox<String>();
		DefaultComboBoxModel<String> countriesModel = new DefaultComboBoxModel<String>();
		for (String s : countriesFromDB) {
			countriesModel.addElement(s);
		}
		countriesModel.setSelectedItem("Turkey");
		countryList.setModel(countriesModel);
		countryList.setPreferredSize(new Dimension(0, 20));
		return countryList;
	}

	private boolean darkMode;

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		Color darkGray = Color.DARK_GRAY;
		Color gray = Color.GRAY;
		GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
		GradientPaint dm = new GradientPaint(0, 0, gray, 100, height, darkGray);
		if (!darkMode) {
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
		} else {
			g2d.setPaint(dm);
			g2d.fillRect(0, 0, width, height);
		}
	}

	public void setDarkMode(boolean turnDark) {
		this.darkMode = turnDark;
		repaint();
		revalidate();
	}

	public Toolbar() {
		buttonParams();
		actionsForButtons();
		layoutParams();
		setPreferredSize(new Dimension(150, 500));
		setBorder(BorderFactory.createTitledBorder("Toolbar"));
	}

	GridBagConstraints gc;

	public void layoutParams() {
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 15, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(login, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 15, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(profile, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 15, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(calc, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 15, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(calendar, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 15, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(reports, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 50;
		gc.insets = new Insets(0, 15, 0, 0);
		gc.anchor = GridBagConstraints.NORTHWEST;
		add(talker, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(feedback, gc);

		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 60, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(settings, gc);

		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 100, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(signUp, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(timeStamp, gc);
	}

	public void buttonParams() {
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(113, 25));
		login.setBackground(Color.WHITE);
		profile = new JButton("Profile");
		profile.setPreferredSize(new Dimension(113, 25));
		profile.setBackground(Color.WHITE);
		profile.setActionCommand("Profile Clicked");
		calc = new JButton("Calculator");
		calc.setPreferredSize(new Dimension(113, 25));
		calc.setBackground(Color.WHITE);
		calendar = new JButton("Calendar");
		calendar.setPreferredSize(new Dimension(113, 25));
		calendar.setBackground(Color.WHITE);
		reports = new JButton("Reports");
		reports.setPreferredSize(new Dimension(113, 25));
		reports.setBackground(Color.WHITE);
		reports.setActionCommand("Reports Clicked");
		talker = new JButton("Talker");
		talker.setPreferredSize(new Dimension(113, 25));
		talker.setBackground(Color.WHITE);
		talker.setActionCommand("Talker Clicked");

		feedback = new JButton();
		feedback.setPreferredSize(new Dimension(30, 30));
		feedback.setBackground(Color.WHITE);
		feedback.setToolTipText("Feedback");
		settings = new JButton();
		settings.setPreferredSize(new Dimension(30, 30));
		settings.setBackground(Color.WHITE);
		settings.setToolTipText("Settings");
		signUp = new JButton();
		signUp.setPreferredSize(new Dimension(30, 30));
		signUp.setBackground(Color.WHITE);
		signUp.setToolTipText("Create Account");

		ImageIcon loginIcon = new ImageIcon(getClass().getResource("/images/login.png"));
		Image scaledLog = loginIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		loginIcon = new ImageIcon(scaledLog);
		login.setIcon(loginIcon);
		login.setHorizontalAlignment(SwingConstants.LEFT);
		login.setVerticalAlignment(JButton.CENTER);
		login.setName("Login");

		ImageIcon profIcon = new ImageIcon(getClass().getResource("/images/profile.png"));
		Image scaledProf = profIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		profIcon = new ImageIcon(scaledProf);
		profile.setIcon(profIcon);
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setVerticalAlignment(JButton.CENTER);

		ImageIcon calcIcon = new ImageIcon(getClass().getResource("/images/calc.png"));
		Image scaledCalc = calcIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		calcIcon = new ImageIcon(scaledCalc);
		calc.setIcon(calcIcon);
		calc.setHorizontalAlignment(SwingConstants.LEFT);
		calc.setVerticalAlignment(JButton.CENTER);
		calc.setActionCommand("Calculator Clicked");

		ImageIcon caleIcon = new ImageIcon(getClass().getResource("/images/calendar.png"));
		Image scaledCale = caleIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		caleIcon = new ImageIcon(scaledCale);
		calendar.setIcon(caleIcon);
		calendar.setHorizontalAlignment(SwingConstants.LEFT);
		calendar.setVerticalAlignment(JButton.CENTER);
		calendar.setActionCommand("Calendar Clicked");

		ImageIcon reportIcon = new ImageIcon(getClass().getResource("/images/graph.png"));
		Image scaledImg = reportIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		reportIcon = new ImageIcon(scaledImg);
		reports.setIcon(reportIcon);
		reports.setHorizontalAlignment(SwingConstants.LEFT);
		reports.setVerticalAlignment(JButton.CENTER);

		ImageIcon talkerIcon = new ImageIcon(getClass().getResource("/images/talker.png"));
		Image scaledTalker = talkerIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		talkerIcon = new ImageIcon(scaledTalker);
		talker.setIcon(talkerIcon);
		talker.setHorizontalAlignment(SwingConstants.LEFT);
		talker.setVerticalAlignment(SwingConstants.CENTER);

		ImageIcon feedIcon = new ImageIcon(getClass().getResource("/images/feed.png"));
		Image scaledFeed = feedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		feedIcon = new ImageIcon(scaledFeed);
		feedback.setIcon(feedIcon);
		feedback.setHorizontalAlignment(JButton.CENTER);
		feedback.setVerticalAlignment(JButton.CENTER);
		feedback.setActionCommand("Feedback Clicked");

		ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/images/settings.png"));
		Image scaledSet = settingsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		settingsIcon = new ImageIcon(scaledSet);
		settings.setIcon(settingsIcon);
		settings.setHorizontalAlignment(JButton.CENTER);
		settings.setVerticalAlignment(JButton.CENTER);
		settings.setActionCommand("Settings Clicked");

		ImageIcon signUpIcon = new ImageIcon(getClass().getResource("/images/signup.png"));
		Image scaledSignUp = signUpIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		signUpIcon = new ImageIcon(scaledSignUp);
		signUp.setIcon(signUpIcon);
		signUp.setHorizontalAlignment(JButton.CENTER);
		signUp.setVerticalAlignment(JButton.CENTER);
		signUp.setActionCommand("Sign Up Clicked");

		timeStamp = new JTextField();
		timeStamp.setPreferredSize(new Dimension(113, 25));
		timeStamp.setEditable(false);
		timeStamp.setBackground(Color.GRAY);
		timeStamp.setForeground(Color.WHITE);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		timeStamp.setText(dtf.format(now));
		timeStamp.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void logOut() {
		ImageIcon loginIcon = new ImageIcon(getClass().getResource("/images/login.png"));
		Image scaledLog = loginIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		loginIcon = new ImageIcon(scaledLog);
		login.setIcon(loginIcon);
		login.setHorizontalAlignment(SwingConstants.LEFT);
		login.setVerticalAlignment(JButton.CENTER);
		login.setText("Login");
		login.setEnabled(true);
		login.setName("Login");
		LogInEvent le = new LogInEvent(this, false);
		if (logInListener != null) {
			logInListener.logInEventOccured(le);
		}
	}

	public void clickLogin() {
		login.doClick();
	}

	public void welcomeButton() {
		login.setIcon(null);
		login.setText("Welcome");
		login.setEnabled(false);
		login.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void actionsForButtons() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == login) {
					
					JPanel panel = new JPanel(new GridBagLayout());
					GridBagLayout grid = new GridBagLayout();
					GridBagConstraints gc = new GridBagConstraints();
					panel.setLayout(grid);

					JTextField userName = new JTextField(10);

					JPasswordField pass = new JPasswordField(10);
					JLabel create = new JLabel("<html><u>Don't have an account?</u></html>");
					create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					create.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent me) {
							signUp.doClick();
						}
					});
					JLabel forgot = new JLabel("<html><u>Forgot Password?</u></html>");
					forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

					panel.setPreferredSize(new Dimension(250, 200));
					int paneResult;

					gc.gridx = 0;
					gc.gridy = 0;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.NONE;
					gc.anchor = GridBagConstraints.FIRST_LINE_START;
					gc.insets = new Insets(10, 5, 0, 0);
					panel.add(new JLabel("Username: "), gc);

					gc.gridx++;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.HORIZONTAL;
					gc.insets = new Insets(10, 0, 0, 10);
					panel.add(userName, gc);

					gc.gridx = 0;
					gc.gridy++;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.NONE;
					gc.anchor = GridBagConstraints.FIRST_LINE_START;
					gc.insets = new Insets(10, 5, 0, 0);
					panel.add(new JLabel("Password: "), gc);

					gc.gridx++;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.HORIZONTAL;
					gc.insets = new Insets(10, 0, 0, 10);
					panel.add(pass, gc);

					gc.gridy++;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.HORIZONTAL;
					gc.insets = new Insets(10, 0, 0, 10);
					panel.add(new JCheckBox("Remember me"), gc);

					gc.gridy++;
					gc.weightx = 1;
					gc.weighty = 1;
					gc.fill = GridBagConstraints.HORIZONTAL;
					gc.insets = new Insets(10, 5, 0, 0);
					panel.add(create, gc);

					gc.gridy++;
					gc.weightx = 1;
					gc.weighty = 10;
					gc.fill = GridBagConstraints.HORIZONTAL;
					gc.insets = new Insets(10, 5, 0, 0);
					panel.add(forgot, gc);

					do {
						paneResult = JOptionPane.showInternalConfirmDialog(null, panel, "Login",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (paneResult == -1 || paneResult == 2) {
							break;
						} else {
							LogInEvent lie = new LogInEvent(this, userName.getText(), pass.getPassword());
							logInListener.logInEventOccured(lie);
							if (lie.getGranted() == true) {
								welcomeButton();
							} else {
								int NotFoundMsg = JOptionPane.showInternalConfirmDialog(null,
										"Account Not Found" + "\n" + "Do you want to create a new account?",
										"Account Not Found", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
								if (NotFoundMsg == 2 || NotFoundMsg == -1) {
									break;
								} else {
									signUp.doClick();
								}
							}
						}
					} while (false);
				}
			}
		});

		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == profile) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == calc) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == calendar) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == reports) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		feedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == feedback) {
					String feedback = JOptionPane.showInputDialog(Toolbar.this, "Want to share your feedback?",
							"Feedback", JOptionPane.YES_NO_CANCEL_OPTION);
					System.out.println(feedback);
					if (feedback != null) {
						File feedbacks = new File("User_Feedback.txt");
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter(feedbacks));
							bw.write(feedback);
							bw.close();
						} catch (IOException e1) {
							System.out.println("Unable to create file");
						}
						JOptionPane.showMessageDialog(Toolbar.this, "Thank you for your feedback", "Feedback",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showConfirmDialog(Toolbar.this, "No worries", "Feedback",
								JOptionPane.CLOSED_OPTION);
					}
				}
			}

		});

		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == settings) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		talker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == talker) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == signUp) {
					if (signUpListener != null) { // I KEEP FORGETTING TO DO NULL CHECK
						GridBagConstraints gc = new GridBagConstraints();

						JPanel firstPage = new JPanel();
						JPanel secondPage = new JPanel();
						int firstPaneResult;

						firstPage.setLayout(new GridBagLayout());
						firstPage.setPreferredSize(new Dimension(350, 550));
						secondPage.setLayout(new GridBagLayout());
						secondPage.setPreferredSize(new Dimension(350, 500));

						ArrayList<JTextField> fieldArray = new ArrayList<>();

						String[] fieldNames = new String[] { "First Name", "Last Name", "User Name", "Street",
								"Postal Code", "City", "State", "Country", "Bio", "Phone", "Email", "Company",
								"Website", "Social", "Member Since" };

						for (String t : fieldNames) {
							JTextField field = new JTextField(20);
							field.setName(t);
							fieldArray.add(field);
						}
						// This signUpEvent is to get the Country list from the DB
						SignUpEvent se = new SignUpEvent(this, "", "", "", null, "", 0, "", "", "", "", 0,
								"", "", "", "", "", true);
						signUpListener.signUpEventOccured(se);
						JComboBox<String> countryList = generateCountryComboBox();
						
						JLabel more = new JLabel("<html><u> More Details </u></html>");
						more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

						JPasswordField pass = new JPasswordField(20);
						JPasswordField confirmPass = new JPasswordField(20);

						more.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent me) {
								if (more == me.getSource()) {
									int secondPaneResult = JOptionPane.showConfirmDialog(null, secondPage,
											"Enter Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
									if (secondPaneResult == -1 || secondPaneResult == 2) {
										for (Integer i = 3; i < fieldArray.size(); i++) {
											fieldArray.get(i).setText(null);
										}
									} else {
										for (Integer i = 3; i < fieldArray.size(); i++) {
											if (fieldArray.get(i).getText() != null) {
												continue;
											}
										}
									}
								}
							}
						});

						JCheckBox agree = new JCheckBox("I agree Terms & Conditions");

						gc.gridx = 0;
						gc.gridy = 0;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						firstPage.add(new JLabel(fieldNames[0] + " (*) "), gc);

						gc.gridx++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						firstPage.add(fieldArray.get(0), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						firstPage.add(new JLabel(fieldNames[1] + " (*) "), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						firstPage.add(fieldArray.get(1), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						firstPage.add(new JLabel(fieldNames[2] + " (*) "), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						firstPage.add(fieldArray.get(2), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						firstPage.add(new JLabel("Password" + " (*) "), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						firstPage.add(pass, gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						firstPage.add(new JLabel("Confirm Password" + " (*) "), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						firstPage.add(confirmPass, gc);

						gc.gridy++;
						gc.insets = new Insets(10, 0, 0, 10);
						gc.anchor = GridBagConstraints.LINE_END;
						firstPage.add(more, gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[3]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(3), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[4]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(4), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[5]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(5), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[6]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(6), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 0.1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[7]), gc);

						gc.gridx = 1;
						gc.weightx = 200;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(countryList, gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[8]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(8), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[9]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(9), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[10]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(10), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[11]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(11), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[12]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(12), gc);

						gc.gridx = 0;
						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						gc.insets = new Insets(10, 5, 0, 0);
						secondPage.add(new JLabel(fieldNames[13]), gc);

						gc.gridx = 1;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.fill = GridBagConstraints.HORIZONTAL;
						gc.insets = new Insets(10, 0, 0, 10);
						secondPage.add(fieldArray.get(13), gc);

						gc.gridy++;
						gc.weightx = 1;
						gc.weighty = 20;
						gc.fill = GridBagConstraints.NONE;
						gc.anchor = GridBagConstraints.FIRST_LINE_START;
						firstPage.add(agree, gc);

						do {
							firstPaneResult = JOptionPane.showConfirmDialog(null, firstPage, "Create new user",
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
							if (firstPaneResult == -1 || firstPaneResult == 2) {
								break;
							}

							else if (!agree.isSelected()) {
								JOptionPane.showMessageDialog(null,
										"Make sure you agreed the Terms & Conditions to create a new profile",
										"Warning", JOptionPane.WARNING_MESSAGE);
								agree.setForeground(Color.red);
							} else if (agree.isSelected()) {
								agree.setForeground(Color.black);
							}
							if (!Arrays.equals(pass.getPassword(), confirmPass.getPassword())) {
								JOptionPane.showMessageDialog(null, "Passwords do not match", "Warning",
										JOptionPane.WARNING_MESSAGE);
								pass.setBorder(BorderFactory.createLineBorder(Color.red));
								confirmPass.setBorder(BorderFactory.createLineBorder(Color.red));
							} else if (Arrays.equals(pass.getPassword(), confirmPass.getPassword())) {
								pass.setBorder(BorderFactory.createLineBorder(Color.black));
								confirmPass.setBorder(BorderFactory.createLineBorder(Color.black));
							}
							for (JTextField f : fieldArray) {
								if (f.getName() == "First Name" || f.getName() == "Last Name"
										|| f.getName() == "User Name") {
									if (f.getText().length() == 0) {
										f.setBorder(BorderFactory.createLineBorder(Color.red));
									} else if (f.getText().length() > 0) {
										f.setBorder(BorderFactory.createLineBorder(Color.black));
									}
								}
							}

						} while (!agree.isSelected() || !Arrays.equals(pass.getPassword(), confirmPass.getPassword())
								|| fieldArray.get(0).getText().length() == 0
								|| fieldArray.get(1).getText().length() == 0
								|| fieldArray.get(2).getText().length() == 0 || pass.getPassword().length == 0
								|| confirmPass.getPassword().length == 0);

						if (Arrays.equals(pass.getPassword(), confirmPass.getPassword()) && firstPaneResult == 0) {
							JOptionPane.showMessageDialog(null, "New profile created successfully!");
							StringBuilder sb = new StringBuilder("First Name: ").append(fieldArray.get(0).getText())
									.append("\n" + "Last Name: ").append(fieldArray.get(1).getText())
									.append("\n" + "Username: ").append(fieldArray.get(2).getText())
									.append("\n" + "Password: ").append(pass.getPassword());
							File file = new File("UserInfo.txt");
							try {
								if (!file.exists()) {
									file.createNewFile();
								} else {
									BufferedWriter bw = new BufferedWriter(new FileWriter(file));
									bw.write(sb.toString());
									bw.close();

									if (fieldArray.get(4).getText() != null) {
										try {
											Integer.parseInt(fieldArray.get(4).getText());
										} catch (NumberFormatException nfe) {
											System.out.println(
													"User input was not Int or Int was incorrect, converting to 0 : "
															+ nfe.getMessage());
											fieldArray.get(4).setText("0");
										}
									}
									if (fieldArray.get(9).getText() != null) {
										try {
											Integer.parseInt(fieldArray.get(9).getText());
										} catch (NumberFormatException nfe) {
											System.out.println(
													"User input was not Int or Int was incorrect, converting to 0 : "
															+ nfe.getMessage());
											fieldArray.get(9).setText("0");
										}
									}

									SignUpEvent sue = new SignUpEvent(this, fieldArray.get(0).getText(),
											fieldArray.get(1).getText(), fieldArray.get(2).getText(),
											pass.getPassword(), fieldArray.get(3).getText(),
											fieldArray.get(4).getText().isEmpty() ? 0
													: Integer.parseInt(fieldArray.get(4).getText()),
											fieldArray.get(5).getText(), fieldArray.get(6).getText(),
											(String) countryList.getSelectedItem(), fieldArray.get(8).getText(),
											fieldArray.get(9).getText().isEmpty() ? 0
													: Integer.valueOf(fieldArray.get(9).getText()),
											fieldArray.get(10).getText(), fieldArray.get(11).getText(),
											fieldArray.get(12).getText(), fieldArray.get(13).getText(),
											timeStamp.getText(), false);
									signUpListener.signUpEventOccured(sue);
									System.out.println(sb);
								}
							} catch (IOException e1) {
								System.out.println("Unable to open file");
							}
						}
					}
				}
			}
		});
	}

}