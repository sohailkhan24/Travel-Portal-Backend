package com.nagarro.training.dto;

public class RegisterUserDto {
	private String id;
	private String firstName;
	private String lastName;
	private String buisnessUnit;
	private String title;
	private String email;
	private String telephone;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;

	public RegisterUserDto() {

	}

	public RegisterUserDto(String id, String firstName, String lastName, String buisnessUnit, String title,
			String email, String telephone, String address1, String address2, String city, String state, String zip,
			String country) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.buisnessUnit = buisnessUnit;
		this.title = title;
		this.email = email;
		this.telephone = telephone;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBuisnessUnit() {
		return buisnessUnit;
	}

	public void setBuisnessUnit(String buisnessUnit) {
		this.buisnessUnit = buisnessUnit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "RegisterUserDto [firstName=" + firstName + ", lastName=" + lastName + ", buisnessUnit=" + buisnessUnit
				+ ", title=" + title + ", email=" + email + ", telephone=" + telephone + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + "]";
	}

}
