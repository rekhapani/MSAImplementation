package com.reference.commitment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="commitments")
public class CommitmentEntity {

	@Id
	@Column(name = "CommitmentId", nullable = true)
	private Integer commitmentId;
	
	@Column(name = "CorporateAccountId")
	private Integer corporateAccountId;
	
	@Column(name = "LineofBusineeId")
	private Integer lineofBusineeId;
	
	@Column(name = "BusinessUnit")
	private String businessUnit;
	
	@Column(name = "ServiceOffering")
	private String serviceOffering;

	@Column(name = "OriginArea")
	private String originArea;
	
	@Column(name = "DestinationArea")
	private String destinationArea;
	
	@Column(name = "ScheduleFrom")
	private Date scheduleFrom;
	
	@Column(name = "ScheduleTo")
	private Date scheduleTo;

	public Integer getCommitmentId() {
		return commitmentId;
	}

	public void setCommitmentId(Integer commitmentId) {
		this.commitmentId = commitmentId;
	}

	public Integer getCorporateAccountId() {
		return corporateAccountId;
	}

	public void setCorporateAccountId(Integer corporateAccountId) {
		this.corporateAccountId = corporateAccountId;
	}

	public Integer getLineofBusineeId() {
		return lineofBusineeId;
	}

	public void setLineofBusineeId(Integer lineofBusineeId) {
		this.lineofBusineeId = lineofBusineeId;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getServiceOffering() {
		return serviceOffering;
	}

	public void setServiceOffering(String serviceOffering) {
		this.serviceOffering = serviceOffering;
	}

	public String getOriginArea() {
		return originArea;
	}

	public void setOriginArea(String originArea) {
		this.originArea = originArea;
	}

	public String getDestinationArea() {
		return destinationArea;
	}

	public void setDestinationArea(String destinationArea) {
		this.destinationArea = destinationArea;
	}

	public Date getScheduleFrom() {
		return scheduleFrom;
	}

	public void setScheduleFrom(Date scheduleFrom) {
		this.scheduleFrom = scheduleFrom;
	}

	public Date getScheduleTo() {
		return scheduleTo;
	}

	public void setScheduleTo(Date scheduleTo) {
		this.scheduleTo = scheduleTo;
	}
}
