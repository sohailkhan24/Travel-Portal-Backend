package com.nagarro.training.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	private String requestType;

	@NotNull
	private String priority;

	@NotNull
	@Size(min = 1)
	private String travelCity;

	@NotNull
	@Size(min = 1)
	private String fromLocationCity;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date travelStartDate;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date travelEndDate;

	@NotNull
	@Size(min = 1, max = 25)
	private String passportNumber;

	@NotNull
	@Size(min = 1, max = 100)
	private String projectName;

	@NotNull
	private String expenseBorneBy;

	@Size(max = 100)
	private String travelApproverName;

	@Size(max = 100)
	private String expectedDurationOfTravel;

	@Size(max = 500)
	private String upperBound;

	@NotNull
	@Size(min = 1, max = 1000)
	private String anyAdditionalDetails;

	@NotNull
	private String status;

	@Size(max = 1000)
	private String comments;

	@OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<File> files;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	private int statusCode;
	public Date createdAt;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void initializeCreatedAt() {
		this.createdAt = new Date();
	}

	public Ticket() {

	}

	public Ticket(String id, String requestType, String priority, String travelCity, String fromLocationCity,
			Date travelStartDate, Date travelEndDate, String passportNumber, String projectName, String expenseBorneBy,
			String travelApproverName, String expectedDurationOfTravel, String upperBound, String anyAdditionalDetails,
			User user) {
		this.id = id;
		this.requestType = requestType;
		this.priority = priority;
		this.travelCity = travelCity;
		this.fromLocationCity = fromLocationCity;
		this.travelStartDate = travelStartDate;
		this.travelEndDate = travelEndDate;
		this.passportNumber = passportNumber;
		this.projectName = projectName;
		this.expenseBorneBy = expenseBorneBy;
		this.travelApproverName = travelApproverName;
		this.expectedDurationOfTravel = expectedDurationOfTravel;
		this.upperBound = upperBound;
		this.anyAdditionalDetails = anyAdditionalDetails;
		this.user = user;
		this.status = "submit";
		this.statusCode = 0;
	}

	public Ticket(String requestType, String priority, String travelCity, String fromLocationCity, Date travelStartDate,
			Date travelEndDate, String passportNumber, String projectName, String expenseBorneBy,
			String travelApproverName, String expectedDurationOfTravel, String upperBound, String anyAdditionalDetails,
			User user) {
		this.requestType = requestType;
		this.priority = priority;
		this.travelCity = travelCity;
		this.fromLocationCity = fromLocationCity;
		this.travelStartDate = travelStartDate;
		this.travelEndDate = travelEndDate;
		this.passportNumber = passportNumber;
		this.projectName = projectName;
		this.expenseBorneBy = expenseBorneBy;
		this.travelApproverName = travelApproverName;
		this.expectedDurationOfTravel = expectedDurationOfTravel;
		this.upperBound = upperBound;
		this.anyAdditionalDetails = anyAdditionalDetails;
		this.user = user;
		this.status = "submit";
		this.statusCode = 0;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTravelCity() {
		return travelCity;
	}

	public void setTravelCity(String travelCity) {
		this.travelCity = travelCity;
	}

	public String getFromLocationCity() {
		return fromLocationCity;
	}

	public void setFromLocationCity(String fromLocationCity) {
		this.fromLocationCity = fromLocationCity;
	}

	public Date getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public Date getTravelEndDate() {
		return travelEndDate;
	}

	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getExpenseBorneBy() {
		return expenseBorneBy;
	}

	public void setExpenseBorneBy(String expenseBorneBy) {
		this.expenseBorneBy = expenseBorneBy;
	}

	public String getTravelApproverName() {
		return travelApproverName;
	}

	public void setTravelApproverName(String travelApproverName) {
		this.travelApproverName = travelApproverName;
	}

	public String getExpectedDurationOfTravel() {
		return expectedDurationOfTravel;
	}

	public void setExpectedDurationOfTravel(String expectedDurationOfTravel) {
		this.expectedDurationOfTravel = expectedDurationOfTravel;
	}

	public String getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	public String getAnyAdditionalDetails() {
		return anyAdditionalDetails;
	}

	public void setAnyAdditionalDetails(String anyAdditionalDetails) {
		this.anyAdditionalDetails = anyAdditionalDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
