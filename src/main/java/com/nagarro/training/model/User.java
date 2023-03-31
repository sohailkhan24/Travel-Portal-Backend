package com.nagarro.training.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@Size(min = 1)
	private String firstName;

	@NotNull
	@Size(min = 1)
	private String lastName;

	@NotNull
	@Size(min = 1)
	private String buisnessUnit;

	@NotNull
	@Size(min = 1)
	private String title;

	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^(?!.*[A-Z])(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@nagarro.com$")
	private String email;

	@NotNull
	@Size(min = 10, max = 15)
	@Pattern(regexp = "^\\d{10,15}$")
	private String telephone;

	@NotNull
	@Size(min = 1)
	private String address1;

	private String address2;

	@NotNull
	@Size(min = 1)
	private String city;

	@NotNull
	@Size(min = 1)
	private String state;

	@NotNull
	@Size(min = 1)
	private String zip;

	@NotNull
	@Size(min = 1)
	private String country;

	@NotNull
	@Size(min = 1)
	@Column(unique = true)
	private String userName;

	@NotNull
	@Size(min = 8)
	private String password;

	@NotNull
	private String role;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Ticket> tickets;

	public User() {
		this.role = "user";
	}

	public User(String firstName, String lastName, String buisnessUnit, String title, String email, String telephone,
			String address1, String address2, String city, String state, String zip, String country) {
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
		this.role = "user";
	}

	public User(String id, String firstName, String lastName, String buisnessUnit, String title, String email,
			String telephone, String address1, String address2, String city, String state, String zip, String country) {
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
		this.role = "user";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", buisnessUnit="
				+ buisnessUnit + ", title=" + title + ", email=" + email + ", telephone=" + telephone + ", address1="
				+ address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", country=" + country + ", userName=" + userName + ", role=" + role + "]";
	}

}