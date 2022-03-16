package view;

import java.awt.Color;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTextField taskName;
	private JComboBox<workflow> status;
	private JTextField description;
	private JComboBox<users> assignee;
	private JComboBox<difficulty> difficulty;
	private JButton submit;
	private JButton cancel;
	private JRadioButton bug;
	private JRadioButton feature;
	private JRadioButton story;
	private ButtonGroup buttonGroup;
	private JComboBox<priority> priorities;
	private JCheckBox subTask;
	private JLabel subLabel;
	private JTextField subField;
	private JSpinner dateSpinner;
	private SpinnerDateModel model;

	private FormListener formListener;

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
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
		if(!darkMode) {
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

	public FormPanel() {
		setFocusable(true);
		requestFocusInWindow(true);
		setBackground(new Color(0, 156, 99));
		setPreferredSize(new Dimension(300, 800));
		setMinimumSize(new Dimension(250, 600));

		Border outerBorder = BorderFactory.createTitledBorder("Create a Task");
		Border innerBorder = BorderFactory.createEmptyBorder();
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		taskName = new JTextField(10);
		status = new JComboBox<workflow>();
		description = new JTextField(50);

		model = new SpinnerDateModel();
		dateSpinner = new JSpinner(model);
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		assignee = new JComboBox<users>();
		priorities = new JComboBox<priority>();
		difficulty = new JComboBox<difficulty>();
		subTask = new JCheckBox("Is there a sub-task?");
		subTask.setBackground(null);
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");

		subLabel = new JLabel("Subtask : ");
		subField = new JTextField(10);
		subLabel.setVisible(false);
		subField.setVisible(false);
		subTask.setSelected(false);

		subTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = subTask.isSelected();
				subLabel.setVisible(isTicked);
				subField.setVisible(isTicked);
				subField.setText(null);
			}
		});
		comboParams();
		buttonParams();
		layoutParams();

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String task = taskName.getText();
				taskName.setText("");
				String stageName = status.getSelectedItem().toString();
				status.setSelectedIndex(1);
				String desc = description.getText();
				description.setText("");
				Date date = (Date) dateSpinner.getModel().getValue();
				String formattedDate = sdf.format(date);
				
				String assigneeName = assignee.getSelectedItem().toString();
				assignee.setSelectedIndex(0);
				String type = buttonGroup.getSelection().getActionCommand();
				String priorityName = priorities.getSelectedItem().toString();
				int difficultyValue = Integer.parseInt(difficulty.getSelectedItem().toString());
				String subId = subField.getText();

				FormEvent fe = new FormEvent(this, task, stageName, desc, formattedDate, assigneeName, type,
						priorityName, difficultyValue, subId);
				if (formListener != null) {
					formListener.formEventOccurred(fe);
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskName.setText(null);
				status.setSelectedItem(1);
				description.setText(null);
				assignee.setSelectedIndex(0);
				priorities.setSelectedIndex(0);
				difficulty.setSelectedIndex(0);
				subTask.setSelected(false);
				subField.setVisible(false);
				subLabel.setVisible(false);
				buttonGroup.clearSelection();
			}
		});
		
	}

	private void buttonParams() {
		bug = new JRadioButton("Bug");
		feature = new JRadioButton("Feature");
		story = new JRadioButton("Story");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(bug);
		buttonGroup.add(feature);
		buttonGroup.add(story);
		bug.setOpaque(false);
		bug.setActionCommand("Bug");
		feature.setActionCommand("Feature");
		feature.setOpaque(false);
		story.setActionCommand("Story");
		story.setOpaque(false);
		submit.setBackground(new Color(101, 152, 240));
		submit.setForeground(Color.WHITE);
		subTask.setOpaque(false);
		submit.setDefaultCapable(true);
		ImageIcon submitIcon = new ImageIcon(getClass().getResource("/images/submit.png"));
		Image scaledSub = submitIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		submitIcon = new ImageIcon(scaledSub);
		submit.setIcon(submitIcon);
		submit.setHorizontalAlignment(JButton.CENTER);
		submit.setVerticalAlignment(JButton.CENTER);
		submit.setPreferredSize(new Dimension(50, 25));

		cancel.setBackground(new Color(101, 152, 248));
		cancel.setForeground(Color.WHITE);
		ImageIcon cancelIcon = new ImageIcon(getClass().getResource("/images/cancel.png"));
		Image scaledCan = cancelIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		cancelIcon = new ImageIcon(scaledCan);
		cancel.setIcon(cancelIcon);
		cancel.setHorizontalAlignment(JButton.CENTER);
		cancel.setVerticalAlignment(JButton.CENTER);
		cancel.setPreferredSize(new Dimension(50, 25));

		InputMap im = submit.getInputMap();
		im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		im.put(KeyStroke.getKeyStroke("released ENTER"), "released");

		InputMap imp = cancel.getInputMap();
		imp.put(KeyStroke.getKeyStroke("SPACE"), "pressed");
		imp.put(KeyStroke.getKeyStroke("released SPACE"), "released");

	}

	private void comboParams() {
		DefaultComboBoxModel<workflow> work = new DefaultComboBoxModel<workflow>();
		work.addElement(new workflow(0, "Backlog"));
		work.addElement(new workflow(1, "Selected for Development"));
		work.addElement(new workflow(2, "In Progress"));
		work.addElement(new workflow(3, "Development Done"));
		work.addElement(new workflow(4, "Peer Review"));
		work.addElement(new workflow(5, "Finished"));
		status.setModel(work);
		status.setPreferredSize(new Dimension(300, 300));

		DefaultComboBoxModel<users> usrs = new DefaultComboBoxModel<users>();
		usrs.addElement(new users(0, "Admin"));
		usrs.addElement(new users(1, "User 1"));
		usrs.addElement(new users(2, "User 2"));
		usrs.addElement(new users(3, "User 3"));
		usrs.addElement(new users(4, "User 4"));
		usrs.addElement(new users(5, "User 5"));

		assignee.setModel(usrs);
		assignee.setPreferredSize(new Dimension(300, 300));

		DefaultComboBoxModel<priority> prrty = new DefaultComboBoxModel<priority>();
		prrty.addElement(new priority(0, "Low"));
		prrty.addElement(new priority(1, "Medium"));
		prrty.addElement(new priority(2, "High"));
		prrty.addElement(new priority(3, "Urgent"));

		priorities.setModel(prrty);
		priorities.setPreferredSize(new Dimension(300, 300));

		DefaultComboBoxModel<difficulty> diffty = new DefaultComboBoxModel<difficulty>();
		diffty.addElement(new difficulty(0, 1));
		diffty.addElement(new difficulty(1, 3));
		diffty.addElement(new difficulty(2, 5));
		diffty.addElement(new difficulty(3, 8));
		diffty.addElement(new difficulty(4, 13));
		diffty.addElement(new difficulty(5, 21));
		difficulty.setModel(diffty);
		difficulty.setPreferredSize(new Dimension(300, 300));
	}

	private void layoutParams() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 5, 0, 0);
		add(new JLabel("Task Name: "), gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(10, 0, 0, 10);
		add(taskName, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Status: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(status, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Description: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(description, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Due Date: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(dateSpinner, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Assignee: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(assignee, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Type: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bug, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 50, 0, 0);
		add(feature, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 120, 0, 0);
		add(story, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Priority: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(priorities, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Difficulty: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(difficulty, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(new JLabel("Any Subtask?: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(subTask, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(subLabel, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		add(subField, gc);

		gc.gridx = 1;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 20;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 100, 0, 5);
		add(submit, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 20;
		gc.insets = new Insets(0, 1, 0, 10);
		add(cancel, gc);
	}

	private class difficulty {
//		private int id;
		private int diff;

		public difficulty(int id, int fibonacci) {
//			this.id = id;
			this.diff = fibonacci;
		}
//		public int getId() {
//			return id;
//		}

		public String toString() {
			return String.valueOf(diff);
		}
	}

	private class workflow {
//		private int id;
		private String name;

		public workflow(int id, String name) {
//			this.id = id;
			this.name = name;
		}

//		public int getId() {
//			return id;
//		}

		public String toString() {
			return name;
		}

	}

	private class users {
//		private int id;
		private String name;

		public users(int id, String name) {
//			this.id = id;
			this.name = name;
		}

//		public int getId() {
//			return id;
//		}

		public String toString() {
			return name;
		}

	}

	private class priority {
//		private int id;
		private String pri;

		public priority(int id, String pri) {
//			this.id = id;
			this.pri = pri;
		}

//		public int getId() {
//			return id;
//		}

		public String toString() {
			return pri;
		}
	}
}