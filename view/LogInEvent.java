package view;

public class LogInEvent {
	private String userName;
	private char[] password;
	private boolean granted;

	public LogInEvent(Object source, String userName, char[] password) {
		this.userName = userName;
		this.password = password;
		this.granted = false;
	}

	public LogInEvent(Object source, boolean granted) {
		this.granted = granted;
	}

	public String getUserName() {
		return userName;
	}
	
	public char[] getPassword() {
		return password;
	}

	public void setGranted() {
		granted = true;
	}

	public boolean getGranted() {
		return granted;
	}

	public void loggedOut() {
		granted = false;
	}

}