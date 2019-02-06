package com.revature.craleigh318.ers.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReimbursementRequest {
	
	private int userId;
	private BigDecimal amount;
	private Date requestDate;
	private String description;
	private Boolean approved;

	public ReimbursementRequest(int userId, BigDecimal amount, Date requestDate, String description, Boolean approved) {
		this.setUserId(userId);
		this.setAmount(amount);
		this.setRequestDate(requestDate);
		this.setDescription(description);
		this.setApproved(approved);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
}
