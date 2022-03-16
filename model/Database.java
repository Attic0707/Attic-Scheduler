package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
	private List<Issue> issues;
	private List<User> users;
	private Map<String, char[]> userMap;
	private Map<String, String> issueMap;
	private String loggedInUser;
	private ArrayList<String> userDetails;

	public Database() {
		issues = new LinkedList<Issue>();
		users = new LinkedList<User>();
		userMap = new HashMap<String, char[]>();
		issueMap = new HashMap<String, String>();
	}

	public void putToUserMap(String userName, char[] password) {
		userMap.put(userName, password);
	}
	
	public void deleteAccount() {
	}
	
	public ArrayList<String> getUserDetails() {
		return userDetails;
	}
	
	public void setLoggedInUser(String currentUser) {
		this.loggedInUser = currentUser;
	}
	
	public void logOutUser() {
		this.loggedInUser = null;
	}
	
	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void putToIssueMap(String issueId, String issueName) {
		issueMap.put(issueId, issueName);
	}

	public void removeFromIssueMap(String issueId) {
		issueMap.remove(issueId);
	}

	public Map<String, String> getIssueMap() {
		return issueMap;
	}

	public Map<String, char[]> getUserMap() {
		return userMap;
//		return Collections.unmodifiableSortedMap((SortedMap<String, char[]>) userMap);
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public List<Issue> getIssues() {
		return Collections.unmodifiableList(issues);
	}
	public ArrayList<String> getCountries() {
		ArrayList<String> countryList = new ArrayList<String>();
		countryList = Country.getCountryList();
		return countryList;
	}
	
	public void setUserDetails(ArrayList<String> userDetails) {
		this.userDetails = userDetails;
		for(User u: users) {
			if(u.getUserName().equals(userDetails.get(2))) {
				u.setFirstName(userDetails.get(0));
				u.setLastName(userDetails.get(1));
				u.setStreet(userDetails.get(3));
				u.setPostalCode(userDetails.get(4));
				u.setCity(userDetails.get(5));
				u.setState(userDetails.get(6));
				u.setCountry(userDetails.get(7));
				u.setBio(userDetails.get(8));
				u.setEmail(userDetails.get(9));
				u.setCompany(userDetails.get(10));
				u.setWebsite(userDetails.get(11));
				u.setSocial(userDetails.get(12));
				u.setPhone(userDetails.get(13));
			}
		}
	}

	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}
	public ArrayList<String> getUserNameList() {
		ArrayList<String> currentUserNames = new ArrayList<String>();
		for(User s: users) {
			currentUserNames.add(s.getUserName());
		}
		return currentUserNames;
	}

	public void exportUsers(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		User[] userList = users.toArray(new User[users.size()]);
		oos.writeObject(userList);
		oos.close();
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Issue[] issueList = issues.toArray(new Issue[issues.size()]);
		oos.writeObject(issueList);
		oos.close();
	}

	public void loadCSVFile(File file) throws IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		try {
			Issue[] issueList = (Issue[]) ois.readObject();
			issues.clear();
			issues.addAll(Arrays.asList(issueList));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ois.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Issue[] issueList = (Issue[]) ois.readObject();
			issues.clear();
			issues.addAll(Arrays.asList(issueList));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ois.close();
	}

	public void removeIssue(int index) {
		issues.remove(index);
	}

	public void removeUser(int index) {
		users.remove(index);
	}

	public void editIssue(int index, Object editedValue) {
		issues.get(index).setDesc(editedValue.toString());
//		issues.edit(index);
	}
}