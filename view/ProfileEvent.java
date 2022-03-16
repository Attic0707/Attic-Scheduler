package view;

import java.util.ArrayList;

public class ProfileEvent {
	private boolean loggedOut;
	private boolean delete;
	private char[] password;
	private ArrayList<String> userDetails;
	private String editCommand;
	
	public ProfileEvent(Object source, String clickedEdit, ArrayList<String> profileDetails) {
		this.editCommand = clickedEdit;
		this.userDetails = profileDetails;
	}

	public ProfileEvent(Object source, boolean loggedOut) {
		this.loggedOut = loggedOut;
	}

	public ProfileEvent(Object source, boolean deleteAccount, char[] password) {
		this.delete = deleteAccount;
		this.password = password;
	}

	public ProfileEvent(Object source, ArrayList<String> profileDetails) {
		this.userDetails = profileDetails;
	}
	
	public String getEditCommand() {
		return editCommand;
	}

	public boolean getLoggedOut() {
		return loggedOut;
	}

	public boolean getDeleteRequest() {
		return delete;
	}

	public char[] getConfirmPass() {
		return password;
	}

	public ArrayList<String> getProfileDetails() {
		return userDetails;
	}
}