package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

public class Calendar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1814142941291706135L;
	private final JFrame frame;
	private final JPanel mainPanel;
	private final JTextField currentMonth;
	private final JTextField currentYear;
	private JSpinner dateSpinner;
	private SpinnerDateModel model;
	private int gridRows;
	private int gridCols;
	private JPanel calendarPanel;
	private JPanel upperPanel;
	private JPanel lowerPanel;
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}

	public Calendar() {
		frame = new JFrame("Attic Calendarinator");
		mainPanel = new JPanel();
		upperPanel = new JPanel();
		lowerPanel = new JPanel();

		model = new SpinnerDateModel();
		dateSpinner = new JSpinner(model);
		dateSpinner.setPreferredSize(new Dimension(150, 25));
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM.yyyy"));

		currentMonth = new JTextField(10);
		currentMonth.setText("June");
		currentMonth.setEditable(false);
		currentYear = new JTextField(10);
		currentYear.setText("2020");
		currentYear.setEditable(false);
		mainPanel.setLayout(new BorderLayout());
		upperPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		upperPanel.add(currentMonth);
		upperPanel.add(currentYear);
		upperPanel.add(dateSpinner);

		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 30));
		lowerPanel.add(new JLabel("Today's Tasks"));

		gridRows = 7;
		gridCols = 5;
		calendarPanel = new JPanel(new GridLayout(gridRows, gridCols));
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
//				String labelText = String.format("[%d, %d]", j + 1, i + 1);
				String labelText = String.valueOf(i);
				JLabel label = new JLabel(labelText);

				Border outsideBorder = BorderFactory.createLineBorder(Color.BLUE);
				Border insideBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
				label.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
				calendarPanel.add(label);
			}
		}
		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		calendarPanel.setSize(calendarPanel.getPreferredSize());
	}

	public void initialize() {
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(calendarPanel,BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
	}
}
