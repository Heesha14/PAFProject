package com.service;

import com.model.Expenses;

/**
 * @author HeeshaJ
 *
 */
public interface ExpenseService {

	/**
	 * Inserts new expense to expense table
	 * and sending inserting record to payements table
	 * interservice communication implemented
	 *
	 * @param expenseModel
	 * @return
	 */
	public String insertExpense(Expenses expenseModel);

	 /**
     * Updates status of expense table
     * meanwhile upfdates payments table of that particukar record
     * interservice communication implemented
     *
     * @param expenseId
     * @param paymentStatus
     * @return
     */
	public String updateExpenseStatus(int expenseId, String paymentStatus);

}
