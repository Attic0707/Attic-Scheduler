package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalcUI extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9221665701997370955L;
	private final JFrame frame;
	private final JPanel panel;
	private final JPanel panelSub1;
	private final JPanel panelSub2;
	private final JPanel panelSub3;
	private final JPanel panelSub4;
	private final JPanel panelSub5;

	private final JTextArea text;
	private final JButton but[], butAdd, butMinus, butMultiply, butDivide, butEqual, butCancel, butComma;
	private final Calculatinator calculator;

	private String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private final Font font;
	private final Font textFont;

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

	public CalcUI() {
		frame = new JFrame("Attic Calculatinator");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panelSub1 = new JPanel(new FlowLayout());
		panelSub2 = new JPanel(new FlowLayout());
		panelSub3 = new JPanel(new FlowLayout());
		panelSub4 = new JPanel(new FlowLayout());
		panelSub5 = new JPanel(new FlowLayout());

		font = new Font("Consolas", Font.PLAIN, 18);
		text = new JTextArea(1, 20);
		textFont = new Font("Consolas", Font.BOLD, 24);

		but = new JButton[10];
		for (int i = 0; i < 10; i++) {
			but[i] = new JButton(String.valueOf(i));
			but[i].setBackground(new Color(50, 168, 168));
			but[i].setForeground(Color.WHITE);
			but[i].setPreferredSize(new Dimension(55, 70));
		}
		butAdd = new JButton("+");
		butMinus = new JButton("-");
		butMultiply = new JButton("*");
		butDivide = new JButton("/");
		butEqual = new JButton("=");
		butCancel = new JButton("C");
		butComma = new JButton(",");

		butAdd.setPreferredSize(new Dimension(45, 70));
		butAdd.setBackground(new Color(101, 152, 240));
		butAdd.setForeground(Color.WHITE);

		butMinus.setPreferredSize(new Dimension(45, 70));
		butMinus.setBackground(new Color(101, 152, 240));
		butMinus.setForeground(Color.WHITE);

		butMultiply.setPreferredSize(new Dimension(45, 70));
		butMultiply.setBackground(new Color(101, 152, 240));
		butMultiply.setForeground(Color.WHITE);

		butDivide.setPreferredSize(new Dimension(45, 70));
		butDivide.setBackground(new Color(101, 152, 240));
		butDivide.setForeground(Color.WHITE);

		butEqual.setPreferredSize(new Dimension(100, 70));
		butEqual.setBackground(new Color(101, 152, 240));
		butEqual.setForeground(Color.WHITE);

		butCancel.setPreferredSize(new Dimension(45, 70));
		butCancel.setBackground(new Color(101, 152, 240));
		butCancel.setForeground(Color.WHITE);

		butComma.setPreferredSize(new Dimension(45, 70));
		butComma.setForeground(Color.WHITE);
		butComma.setBackground(new Color(101, 152, 240));

		but[0].setPreferredSize(new Dimension(100, 70));
		but[0].setForeground(Color.WHITE);
		but[0].setBackground(new Color(50, 168, 168));

		calculator = new Calculatinator();
	}
	
	public void init() {
		frame.setSize(350, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		text.setFont(textFont);
		text.setEditable(false);

		for (int i = 0; i < 10; i++) {
			but[i].setFont(font);
		}
		butAdd.setFont(font);
		butMinus.setFont(font);
		butMultiply.setFont(font);
		butDivide.setFont(font);
		butEqual.setFont(font);
		butCancel.setFont(font);

		panel.add(Box.createHorizontalStrut(100));
		panelSub1.add(text);
		panel.add(panelSub1);

		panelSub2.add(but[1]);
		panelSub2.add(but[2]);
		panelSub2.add(but[3]);
		panelSub2.add(Box.createHorizontalStrut(15));
		panelSub2.add(butAdd);
		panelSub2.add(butMinus);
		panel.add(panelSub2);

		panelSub3.add(but[4]);
		panelSub3.add(but[5]);
		panelSub3.add(but[6]);
		panelSub3.add(Box.createHorizontalStrut(15));
		panelSub3.add(butMultiply);
		panelSub3.add(butDivide);
		panel.add(panelSub3);

		panelSub4.add(but[7]);
		panelSub4.add(but[8]);
		panelSub4.add(but[9]);
		panelSub4.add(Box.createHorizontalStrut(15));
		panelSub4.add(butComma);
		panelSub4.add(butCancel);
		panel.add(panelSub4);

		panelSub5.add(Box.createHorizontalStrut(75));
		panelSub5.add(but[0]);
		panelSub5.add(Box.createHorizontalStrut(15));
		panelSub5.add(butEqual);
		panel.add(panelSub5);

		for (int i = 0; i < 10; i++) {
			but[i].addActionListener(this);
		}
		butAdd.addActionListener(this);
		butMinus.addActionListener(this);
		butMultiply.addActionListener(this);
		butDivide.addActionListener(this);
		butEqual.addActionListener(this);
		butCancel.addActionListener(this);
		butComma.addActionListener(this);

		frame.add(panel);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();

		for (int i = 0; i < 10; i++) {
			if (source == but[i]) {
				text.replaceSelection(buttonValue[i]);
				return;
			}
		}

		if (source == butAdd) {
			writer(calculator.calculateBi(Calculatinator.BiOperatorModes.add, reader()));
		}

		if (source == butMinus) {
			writer(calculator.calculateBi(Calculatinator.BiOperatorModes.minus, reader()));
		}

		if (source == butMultiply) {
			writer(calculator.calculateBi(Calculatinator.BiOperatorModes.multiply, reader()));
		}

		if (source == butDivide) {
			writer(calculator.calculateBi(Calculatinator.BiOperatorModes.divide, reader()));
		}

		if (source == butEqual) {
			writer(calculator.calculateEqual(reader()));
		}

		if (source == butCancel) {
			writer(calculator.reset());
		}
		text.selectAll();
	}

	public Double reader() {
		Double num;
		String str;
		str = text.getText();
		num = Double.valueOf(str);

		return num;
	}

	public void writer(final Double num) {
		if (Double.isNaN(num)) {
			text.setText("");
		} else {
			text.setText(Double.toString(num));
		}
	}

}
