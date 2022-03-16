package model;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351729135012380019L;
	
	private static int count = 0;
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private char[] password;
	private String street, city, state, country, bio, email, company, website, social, memberSince;
	private int phone, postalCode;

	public User(String firstName, String lastName, String userName, char[] password, String street, 
			int postalCode, String city, String state, String country, String bio, int phone, String email, 
			String company, String website, String social,  String memberSince) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.bio = bio;
		this.email = email;
		this.company = company;
		this.website = website;
		this.social = social;
		this.phone = phone;
		this.memberSince = memberSince;
		this.id = count;
		count++;
	}
	
	public int getUserId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return userName;
	}
	public char[] getPassword() {
		return password;
	}
	public String getStreet() {
		return street;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getBio() {
		return bio;
	}
	public String getEmail() {
		return email;
	}
	public String getCompany() {
		return company;
	}
	public String getWebsite() {
		return website;
	}
	public String getSocial() {
		return social;
	}
	public int getPhone() {
		return phone;
	}
	public String getMemberSince() {
		return memberSince;
	}
	public int getUserCount() {
		return count;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password.toCharArray();
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = Integer.valueOf(postalCode);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public void setPhone(String phone) {
		this.phone = Integer.valueOf(phone);
	}
}