package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Assignee;
import model.Database;
import model.Issue;
import model.Priority;
import model.Status;
import model.Type;
import model.User;
import view.FormEvent;
import view.SignUpEvent;

public class Controller {
	Database db = new Database();

	public List<Issue> getIssueList() {
		return db.getIssues();
	}

	public List<User> getUserList() {
		return db.getUsers();
	}

	public ArrayList<String> getUserNameList() {
		return db.getUserNameList();
	}

	public void setUserDetails(ArrayList<String> userDetails) {
		db.setUserDetails(userDetails);
	}

	public ArrayList<String> getUserDetails() {
		return db.getUserDetails();
	}

	public int getUserCount() {
		return db.getUsers().size();
	}

	public int getIssueCount() {
		return db.getIssues().size();
	}

	public void putToUserMap(String userName, char[] password) {
		db.putToUserMap(userName, password);
	}

	public void putToIssueMap(String issueId, String issueName) {
		db.putToIssueMap(issueId, issueName);
	}

	public void removeFromIssueMap(String issueId) {
		db.removeFromIssueMap(issueId);
	}

	public String getCurrentUser() {
		return db.getLoggedInUser();
	}

	public void setCurrentUser(String userName) {
		db.setLoggedInUser(userName);
	}

	public void logOutCurrentUser() {
		db.logOutUser();
	}

	public void deleteRequest(boolean confirm, char[] confirmPass) {
//		if(confirm == true && Arrays.equals(confirmPass, )) {
		db.deleteAccount();
//		}
	}

	public Boolean issueValidation(String issueId, String issueName) {
		if (!(db.getIssueMap().isEmpty()) && (db.getIssueMap().containsKey(issueId))
				&& (db.getIssueMap().get(issueId)).equals(issueName)) {
			return true;
		}
		return false;
	}

	public Boolean checkForValid(String userName, char[] pass) {
		if (!(db.getUserMap().isEmpty()) && (db.getUserMap().containsKey(userName))
				&& Arrays.equals(db.getUserMap().get(userName), pass)) {
			return true;
		} else {
			return false;
		}
	};

	public ArrayList<String> getCountryList() {
		return db.getCountries();
	};

	public ArrayList<String> getUserDetails(String userName) {
		ArrayList<String> userDetails = new ArrayList<String>();
		for (User u : getUserList()) {
			if (u.getUserName().equals(userName)) {
				userDetails.add(u.getFirstName());
				userDetails.add(u.getLastName());
				userDetails.add(u.getUserName());
				userDetails.add(String.valueOf(u.getPassword()));
				userDetails.add(u.getStreet());
				userDetails.add(String.valueOf(u.getPostalCode()));
				userDetails.add(u.getCity());
				userDetails.add(u.getState());
				userDetails.add(u.getCountry());
				userDetails.add(u.getBio());
				userDetails.add(String.valueOf(u.getPhone()));
				userDetails.add(u.getEmail());
				userDetails.add(u.getCompany());
				userDetails.add(u.getWebsite());
				userDetails.add(u.getSocial());
				userDetails.add(u.getMemberSince());
			}

		}
		return userDetails;
	}

	public void addUser(SignUpEvent sue) {
		String firstName = sue.getFirstName();
		String lastName = sue.getLastName();
		String userName = sue.getUserName();
		char[] password = sue.getPassword();
		String street = sue.getStreet();
		int postalCode = sue.getPostalCode();
		String city = sue.getCity();
		String state = sue.getState();
		String country = sue.getCountry();
		String bio = sue.getBio();
		String email = sue.getEmail();
		String company = sue.getCompany();
		String website = sue.getWebsite();
		String social = sue.getSocial();
		int phone = sue.getPhone();
		String memberSince = sue.getMemberSince();
		User user = new User(firstName, lastName, userName, password, street, postalCode, city, state, country, bio,
				phone, email, company, website, social, memberSince);
//		Check if the user name already exists in the DB
		if (db.getUserNameList().size() != 0 && db.getUserNameList().contains(userName)) {
			System.out.println("Username in DB: ");
			
//			for (String s : db.getUserNameList()) {
//				System.out.println("Check if true : " + !s.equals(userName));
//				if (!s.equals(userName)) {
//					db.addUser(user);
//					System.out.println("DB size: " + db.getUsers().size());
//				} else { 
//					System.out.println("DB size: " + db.getUsers().size());
//					System.out.println("user already in DB");
//				}
//			}
		} else {
			System.out.println("UserDB is empty, creating a new user...");
			db.addUser(user);
			System.out.println("DB size: " + db.getUsers().size());
		}
	} 

	public void addIssue(FormEvent fv) {
		String issueName = fv.getIssueName();
		String issueStatus = fv.getStatus();
		String issueDesc = fv.getDesc();
		String dueDate = fv.getDate();
		String issueAssignee = fv.getAssignee();
		String issueType = fv.getType();
		String issuePriority = fv.getPriority();
		int issueDifficulty = fv.getDifficulty();
		String subTaskId = fv.getSubTaskId();

		Status status = null;
		if (issueStatus.equals("Backlog")) {
			status = Status.Backlog;
		} else if (issueStatus.equals("Selected for Development")) {
			status = Status.SelectedForDevelopment;
		} else if (issueStatus.equals("In Progress")) {
			status = Status.InProgress;
		} else if (issueStatus.equals("Development Done")) {
			status = Status.DevelopmentDone;
		} else if (issueStatus.equals("Peer Review")) {
			status = Status.PeerReview;
		} else if (issueStatus.equals("Finished")) {
			status = Status.Finished;
		}

		Assignee assignee = null;
		if (issueAssignee.equals("Admin")) {
			assignee = Assignee.Admin;
		} else if (issueAssignee.equals("User 1")) {
			assignee = Assignee.User1;
		} else if (issueAssignee.equals("User 2")) {
			assignee = Assignee.User2;
		} else if (issueAssignee.equals("User 3")) {
			assignee = Assignee.User3;
		} else if (issueAssignee.equals("User 4")) {
			assignee = Assignee.User4;
		} else if (issueAssignee.equals("User 5")) {
			assignee = Assignee.User5;
		}

		Type type = null;
		if (issueType.equals("Bug")) {
			type = Type.Bug;
		} else if (issueType.equals("Feature")) {
			type = Type.Feature;
		} else if (issueType.equals("Story")) {
			type = Type.Story;
		}

		Priority priority = null;
		if (issuePriority.equals("Low")) {
			priority = Priority.Low;
		} else if (issuePriority.equals("Medium")) {
			priority = Priority.Medium;
		} else if (issuePriority.equals("High")) {
			priority = Priority.High;
		} else if (issuePriority.equals("Urgent")) {
			priority = Priority.Urgent;
		}

		Issue issue = new Issue(issueName, status, issueDesc, dueDate, assignee, type, priority, issueDifficulty,
				subTaskId);
		db.addIssue(issue);
	}

	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}

	public void exportUsers(File file) throws IOException {
		db.exportUsers(file);
	}

	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void loadCSVFile(File file) throws IOException {
		db.loadCSVFile(file);
	}

	public void removeIssue(int index) {
		db.removeIssue(index);
	}

	public void editIssue(int index, Object editedValue) {
		db.editIssue(index, editedValue);
	}

}