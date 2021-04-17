package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expenses {

    private int expenseId;
    private String expenseTitle;
    private String expenseDesc;
    private Double expenseAmount;
    private String expenseStatus;
    private String expenseDate;
    private String aId;
	/**
	 * @return the expenseId
	 */
	public int getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the expenseTitle
	 */
	public String getExpenseTitle() {
		return expenseTitle;
	}
	/**
	 * @param expenseTitle the expenseTitle to set
	 */
	public void setExpenseTitle(String expenseTitle) {
		this.expenseTitle = expenseTitle;
	}
	/**
	 * @return the expenseDesc
	 */
	public String getExpenseDesc() {
		return expenseDesc;
	}
	/**
	 * @param expenseDesc the expenseDesc to set
	 */
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	/**
	 * @return the expenseAmount
	 */
	public Double getExpenseAmount() {
		return expenseAmount;
	}
	/**
	 * @param expenseAmount the expenseAmount to set
	 */
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	/**
	 * @return the expenseStatus
	 */
	public String getExpenseStatus() {
		return expenseStatus;
	}
	/**
	 * @param expenseStatus the expenseStatus to set
	 */
	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
	}
	/**
	 * @return the expenseDate
	 */
	public String getExpenseDate() {
		return expenseDate;
	}
	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	/**
	 * @return the aId
	 */
	public String getaId() {
		return aId;
	}
	/**
	 * @param aId the aId to set
	 */
	public void setaId(String aId) {
		this.aId = aId;
	}
    
    



}
