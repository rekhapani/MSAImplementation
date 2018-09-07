package com.reference.commitment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderAssociation")
public class OrderAssociationEntity {

	@Id
	@Column(name = "OrderAssocaitonId")
	private Integer orderAssocaitonId;
	
	@Column(name = "CommitmentId")
	private Integer commitmentId;
	
	@Column(name = "OrderId")
	private Integer orderId;
	
	@Column(name = "PickupDate")
	private Date pickupDate;

	public Integer getOrderAssocaitonId() {
		return orderAssocaitonId;
	}

	public void setOrderAssocaitonId(Integer orderAssocaitonId) {
		this.orderAssocaitonId = orderAssocaitonId;
	}

	public Integer getCommitmentId() {
		return commitmentId;
	}

	public void setCommitmentId(Integer commitmentId) {
		this.commitmentId = commitmentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

}
