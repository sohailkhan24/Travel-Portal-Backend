package com.nagarro.training.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RegisterTicketDto {
	private String id;
	private String requestType;
	private String priority;
	private String travelCity;
	private String fromLocationCity;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date travelStartDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date travelEndDate;
	private String passportNumber;
	private String projectName;
	private String expenseBorneBy;
	private String travelApproverName;
	private String expectedDurationOfTravel;
	private String upperBound;
	private String anyAdditionalDetails;

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

}
