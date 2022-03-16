package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class KanbanPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea card;
	private String[] paneNames;
	private ArrayList<JLayeredPane> paneArray;
	private JPopupMenu popupMenu;
	private TaskCardListener listener;
	private JScrollPane scroll;
	private Dimension area, scrollArea;
	private JMenuItem editTask, removeTask;
	private ArrayList<JTextArea> cardArray;
	private boolean darkMode;

	public void setCardListener(TaskCardListener listen) {
		this.listener = listen;
	}

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

	public KanbanPanel() {
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setPreferredSize(new Dimension());

		popupMenu = new JPopupMenu();
		editTask = new JMenuItem("Edit Task");
		editTask.addActionListener(this);
		removeTask = new JMenuItem("Remove Task");
		removeTask.addActionListener(this);
		popupMenu.add(editTask);
		popupMenu.add(removeTask);

		area = new Dimension(0, 0);
		scrollArea = new Dimension(190, 450);
		paneArray = new ArrayList<>(5);
		cardArray = new ArrayList<>();
		paneNames = new String[] { "Selected for Development", "In Progress", "Development Done", "Peer Review",
				"Finished" };

		for (int i = 0; i < 5; i++) {
			Border outerBorder = BorderFactory.createTitledBorder(paneNames[i]);
			Border innerBorder = BorderFactory.createEmptyBorder();

			JLayeredPane stages = new JLayeredPane();
			stages.setPreferredSize(area);
			stages.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			stages.setLayout(new FlowLayout());
			stages.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scroll = new JScrollPane(stages);
			scroll.setPreferredSize(scrollArea);
			scroll.setWheelScrollingEnabled(true);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.getVerticalScrollBar().setUnitIncrement(10);

			paneArray.add(stages);
			add(scroll, 100, 0);
		}
	}

	public void addCard(String issueId, String cardText, String type, String status) {
		System.out.println("Issue ID: " + issueId + "\n" + "cardText: " + cardText + "\n" + "Type: " + type + "\n"
				+ "Status: " + status);
		Point origin = new Point(10, 20);
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		card = new JTextArea();
		card.setPreferredSize(new Dimension(135, 125));
		card.setForeground(Color.WHITE);
		card.setEditable(false);
		card.setLineWrap(true);
		card.setBounds(origin.x, origin.y, 100, 100);
		card.setComponentPopupMenu(popupMenu);
		card.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		card.setText(cardText);
		cardArray.add(card);

		// Organize cards
		for (JTextArea card : cardArray) {
			if (status.equals("Selected For Development")) {
				if (type.equals("Bug")) {
					card.setBackground(new Color(242, 24, 86));
					paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight()
							* paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Feature")) {
					card.setBackground(new Color(6, 50, 140));
					paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight()
							* paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Story")) {
					card.setBackground(new Color(14, 153, 51));
					paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight()
							* paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
				}
			} else if (status.equals("In Progress")) {
				if (type.equals("Bug")) {
					card.setBackground(new Color(242, 24, 86));
					paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Feature")) {
					card.setBackground(new Color(6, 50, 140));
					paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Story")) {
					card.setBackground(new Color(14, 153, 51));
					paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
				}
			} else if (status.equals("Development Done")) {
				if (type.equals("Bug")) {
					card.setBackground(new Color(242, 24, 86));
					paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Feature")) {
					card.setBackground(new Color(6, 50, 140));
					paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Story")) {
					card.setBackground(new Color(14, 153, 51));
					paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
				}
			} else if (status.equals("Peer Review")) {
				if (type.equals("Bug")) {
					card.setBackground(new Color(242, 24, 86));
					paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Feature")) {
					card.setBackground(new Color(6, 50, 140));
					paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Story")) {
					card.setBackground(new Color(14, 153, 51));
					paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
				}
			} else if (status.equals("Finished")) {
				if (type.equals("Bug")) {
					card.setBackground(new Color(242, 24, 86));
					paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Feature")) {
					card.setBackground(new Color(6, 50, 140));
					paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
				} else if (type.equals("Story")) {
					card.setBackground(new Color(14, 153, 51));
					paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
					area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
							+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
					paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
				}
			}
		}
	}

	public void addCard(String issueId, String cardText) {
		Point origin = new Point(10, 20);
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		card = new JTextArea();
		card.setPreferredSize(new Dimension(135, 125));
		card.setForeground(Color.WHITE);
		// card.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		card.setEditable(false);
		card.setLineWrap(true);
		card.setBounds(origin.x, origin.y, 100, 100);
		card.setComponentPopupMenu(popupMenu);
		card.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		card.setText(cardText);

		if (stage == Status.SelectedForDevelopment) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));

			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.InProgress) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.DevelopmentDone) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.PeerReview) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.Finished) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			}
		}
	}

	public void tasksLoaded() {
//		I want to create cards for each task that is uploaded
		System.out.println("Success");
	}

	Status stage = null;

	public void receiveStatus(Status state) {
		this.stage = state;
	}

	Tip type = null;

	public void receiveType(Tip type) {
		this.type = type;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem clicked = (JMenuItem) e.getSource();
		if (clicked == editTask) {
			if (listener != null) {
				GridBagConstraints gc = new GridBagConstraints();
				JPanel editPanel = new JPanel();
				int editPanelResult;

				editPanel.setLayout(new GridBagLayout());
				editPanel.setPreferredSize(new Dimension(350, 550));
				ArrayList<JTextField> fieldArray = new ArrayList<JTextField>();
				String[] fieldNames = new String[] { "Task Name", "Status", "Description", "Due Date", "Assignee",
						"Type", "Priority", "Difficulty" };
				JCheckBox agree = new JCheckBox("Are you sure?");
				for (String s : fieldNames) {
					JTextField field = new JTextField(10);
					field.setName(s);
					fieldArray.add(field);
				}

				for (JTextField f : fieldArray) {
					f.setText("Editable");
				}

				gc.gridx = 0;
				gc.gridy = 0;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[0]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(0), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[1]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(1), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[2]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(2), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[3]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(3), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[4]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(4), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[5]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(5), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[6]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(6), gc);

				gc.gridx = 0;
				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				gc.insets = new Insets(10, 5, 0, 0);
				editPanel.add(new JLabel(fieldNames[7]), gc);

				gc.gridx++;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.insets = new Insets(10, 0, 0, 10);
				editPanel.add(fieldArray.get(7), gc);

				gc.gridy++;
				gc.weightx = 1;
				gc.weighty = 20;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				editPanel.add(agree, gc);

				do {
					editPanelResult = JOptionPane.showConfirmDialog(null, editPanel, "Edit Task",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (editPanelResult == -1 || editPanelResult == 2) {
						break;
					} else if (!agree.isSelected()) {
						JOptionPane.showMessageDialog(null, "Are you sure you want to make these changes?", "Warning",
								JOptionPane.WARNING_MESSAGE);
						agree.setForeground(Color.RED);
					} else if (agree.isSelected()) {
						agree.setForeground(Color.black);
					}
				} while (!agree.isSelected());

				for (JTextField f : fieldArray) {
					if (!f.getText().isBlank()) {
						System.out.println("This is changed: " + f.getText());
					}
				}
			}
		}
	}
}