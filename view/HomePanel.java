package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HomePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7685689743747881331L;
	private JTextArea textArea;
	private JCheckBox hide;

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

	public HomePanel() {
		setFocusable(true);
		requestFocusInWindow();
//		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		textArea = new JTextArea(50, 50);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		textArea.setText("Welcome to Attic Scheduler\nDesigned and developed by Attic Software Inc.\n2020 Istanbul");
		textArea.setOpaque(false);
		textArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		textArea.setAlignmentY(JTextArea.CENTER_ALIGNMENT);

		hide = new JCheckBox("Hide Welcome Text");
		hide.setBackground(null);
		hide.setOpaque(false);
		hide.setSelected(false);

		hide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isClicked = hide.isSelected();
				textArea.setVisible(!isClicked);
			}
		});

		add(textArea, BorderLayout.CENTER);
		add(hide, BorderLayout.AFTER_LAST_LINE);
	}
}
