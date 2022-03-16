package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 370481838840273932L;
	private JButton displayUsers;
	private JButton displayTasks;
	
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

	// public ControlPanel() {
	// 	tableModel = new UserTableModel();
	// 	userTable = new JTable(tableModel);
	// 	setLayout(new BorderLayout());
	// 	add(new JScrollPane(userTable), BorderLayout.CENTER);
	// 	setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	// 	popup = new JPopupMenu();
	// 	JMenuItem editUser = new JMenuItem("Edit User");
	// 	JMenuItem removeUser = new JMenuItem("Remove User");
	// 	popup.add(editUser);
	// 	popup.add(removeUser);
	// }
	public ControlPanel() {
		setEnabled(false);
		setLayout(new GridBagLayout());
		
		displayUsers = new JButton("Display Users");
		displayTasks = new JButton("Export Tasks");
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(new JLabel("User List: "), gc);
		
		gc.gridx++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(displayUsers, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(new JLabel("Task List: "), gc);
		
		gc.gridx++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(displayTasks, gc);
		
		displayUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(displayUsers == (JButton) e.getSource()) {
					System.out.println("Display users button clicked");
				}
			}
		});
		
		displayTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(displayTasks == (JButton) e.getSource()) {
					System.out.println("Display tasks button clicked");
				}
			}
		});
		
	}
}