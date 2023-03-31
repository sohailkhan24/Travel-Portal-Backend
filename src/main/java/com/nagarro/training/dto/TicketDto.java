package com.nagarro.training.dto;

import java.util.Date;
import java.util.List;

import com.nagarro.training.utility.ResponseFile;

public class TicketDto {
	private String id;
	private String requestType;
	private String priority;
	private String travelCity;
	private String fromLocationCity;
	private Date travelStartDate;
	private Date travelEndDate;
	private String passportNumber;
	private String projectName;
	private String expenseBorneBy;
	private String travelApproverName;
	private String expectedDurationOfTravel;
	private String upperBound;
	private String anyAdditionalDetails;
	private String comments;
	private String status;
	private List<ResponseFile> responseFiles;
	private String userName;

	public TicketDto(String id, String requestType, String priority, String travelCity, String fromLocationCity,
			Date travelStartDate, Date travelEndDate, String passportNumber, String projectName, String expenseBorneBy,
			String travelApproverName, String expectedDurationOfTravel, String upperBound,
			String anyAdditionalDetails) {
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

	}

	public TicketDto(String id, String requestType, String priority, String travelCity, String fromLocationCity,
			Date travelStartDate, Date travelEndDate, String passportNumber, String projectName, String expenseBorneBy,
			String travelApproverName, String expectedDurationOfTravel, String upperBound, String anyAdditionalDetails,
			String comments, String status, String userName) {
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
		this.comments = comments;
		this.status = status;
		this.userName = userName;
	}

	public TicketDto() {
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

	public List<ResponseFile> getResponseFiles() {
		return responseFiles;
	}

	public void setResponseFiles(List<ResponseFile> responseFiles) {
		this.responseFiles = responseFiles;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
