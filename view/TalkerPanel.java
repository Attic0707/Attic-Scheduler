package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TalkerPanel extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = -8866559799698143799L;
	private boolean darkMode;
	private TalkerListener talkerListener;
	private JTextArea talkFlow;
	private JTextField body;
	private JButton send, back;
	private JPanel talkerScreen, lobbyScreen;
	private ArrayList<JButton> rooms;
	private JScrollPane scroll;
	private JPopupMenu popup;
	private JMenuItem edit, remove;

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

	public void setTalkerListener(TalkerListener listener) {
		this.talkerListener = listener;
	}

	public TalkerPanel() {
		popup = new JPopupMenu();
		edit = new JMenuItem("Edit Room");
		edit.addActionListener(this);
		edit.setActionCommand("Edit Clicked");
		edit.setBorderPainted(true);
		remove = new JMenuItem("Remove Room");
		remove.addActionListener(this);
		remove.setActionCommand("Remove Clicked");
		popup.add(edit);
		popup.add(remove);

		JPanel talkerScreen = initTalkerScreen();
		talkerScreen.setPreferredSize(new Dimension(850, 450));

		JPanel lobbyScreen = initLobbyScreen();
		lobbyScreen.setPreferredSize(new Dimension(400, 1500));
		lobbyScreen.setBackground(null);
		lobbyScreen.setOpaque(false);

		scroll = new JScrollPane(lobbyScreen);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		scroll.setBackground(null);
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scroll.setPreferredSize(new Dimension(400, 1000));
		scroll.setOpaque(false);

		setLayout(new BorderLayout());
		setBackground(null);
		setOpaque(false);
		add(scroll);
	}

	public JPanel initLobbyScreen() {
		lobbyScreen = new JPanel();
		lobbyScreen.setLayout(new FlowLayout());
		lobbyScreen.setBackground(null);
		lobbyScreen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lobbyScreen.setOpaque(false);

		rooms = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			JButton room = new JButton();
			room.setPreferredSize(new Dimension(230, 230));
			room.setBackground(Color.BLUE);
			Border outerBorder = BorderFactory.createTitledBorder("Room " + i);
			Border innerBorder = BorderFactory.createEmptyBorder();
			room.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			room.setCursor(new Cursor(Cursor.HAND_CURSOR));
			room.addMouseListener(this);
			room.setComponentPopupMenu(popup);
			room.setText("Room " + i);

			rooms.add(room);
			lobbyScreen.add(room);
		}
		return lobbyScreen;
	}

	public JPanel initTalkerScreen() {
		talkerScreen = new JPanel();
		talkerScreen.setLayout(new BorderLayout(10, 10));
		talkerScreen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		talkerScreen.setBackground(null);
		talkerScreen.setOpaque(false);

		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder();

		talkFlow = new JTextArea();
		body = new JTextField(25);
		send = new JButton("Send");
		back = new JButton("Back");

		send.setActionCommand("Send Clicked");
		back.setActionCommand("Back Clicked");

		talkFlow.setEditable(false);
		talkFlow.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		talkFlow.setLineWrap(true);

		send.setBackground(Color.WHITE);
		back.setBackground(Color.WHITE);
		send.setPreferredSize(new Dimension(80, 60));
		back.setPreferredSize(new Dimension(80, 60));
		body.addKeyListener(this);
		send.addActionListener(this);
		back.addActionListener(this);

		scroll = new JScrollPane(talkFlow);
		scroll.setPreferredSize(new Dimension(200, 420));
		scroll.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setWheelScrollingEnabled(true);

		body.setPreferredSize(new Dimension(500, 50));

		talkerScreen.add(scroll, BorderLayout.NORTH);
		talkerScreen.add(body, BorderLayout.CENTER);
		talkerScreen.add(send, BorderLayout.EAST);
		talkerScreen.add(back, BorderLayout.WEST);

		return talkerScreen;
	}

	public void mouseClicked(MouseEvent e) {
		JButton clicked = (JButton) e.getSource();

		for (JButton b : rooms) {
			if (clicked == b) {
				add(talkerScreen, BorderLayout.CENTER);
				remove(scroll);
				repaint();
				revalidate();
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		JButton entered = (JButton) e.getSource();
		for (JButton b : rooms) {
			if (b == entered) {
				b.setBackground(Color.RED);
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		JButton exited = (JButton) e.getSource();
		for (JButton b : rooms) {
			if (b == exited) {
				b.setBackground(Color.BLUE);
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			if(body.getText().length() != 0) {
				send.doClick();
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void setTalkFlowText(String currentUser, String message) {
		if (talkFlow.getText().length() == 0) {
			talkFlow.setText(currentUser + " >>> " + message);
		} else {
			StringBuilder sb = new StringBuilder(talkFlow.getText()).append("\n").append(currentUser)
					.append(" ---->>> ").append(message).append("\n");
			talkFlow.setText(sb.toString());
		}
	}

	public void actionPerformed(ActionEvent e) {
		String commands = e.getActionCommand();
		if (commands == send.getActionCommand()) {
			if (talkerListener != null) {
				TalkerEvent te = new TalkerEvent(this, body.getText());
				talkerListener.talkerEventOccured(te);
				body.setText(null);
			}
		} else if (commands == back.getActionCommand()) {
			remove(talkerScreen);
			add(scroll, BorderLayout.CENTER);
			for (JButton r : rooms) {
				r.setBackground(Color.BLUE);
			}
			repaint();
			revalidate();
		} 
		else if (commands == edit.getActionCommand()) {
			String edited = JOptionPane.showInputDialog(null, "Edit Name", "Edit", JOptionPane.PLAIN_MESSAGE);
			System.out.println("Edited thing: " + edited);
			
			for(JButton b: rooms) {
				if(b == edit.getComponent()) {
					b.setText(edited);
				}
			}
		} else if (commands == remove.getActionCommand()) {
			System.out.println("Remove Clicked");

		}
	}
}