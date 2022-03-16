package view;
import javax.swing.SwingUtilities;

public class AtticScheduler {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
}