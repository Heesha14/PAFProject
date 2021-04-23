package com.repo;

import com.model.Expenses;

/**
 * @author Jaanvi.S.C.H IT19801100
 *
 */
public interface ExpenseRepo {


    /**
     * Inserts new expense to expense table
     * and sending inserting record to payements table
     * interservice communication implemented
     *
     * @param expenseModel
     * @return
     */
    public String addExpense(Expenses expenseModel);


    /**
     * Updates status of expense table
     * meanwhile upfdates payments table of that particukar record
     * interservice communication implemented
     *
     * @param expenseId
     * @param paymentStatus
     * @return
     */
	public String updateStatus(int expenseId, String paymentStatus);

}