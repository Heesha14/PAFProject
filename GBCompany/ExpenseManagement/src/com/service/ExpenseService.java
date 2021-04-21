package com.service;

import com.model.Expenses;

/**
 * @author HeeshaJ
 *
 */
public interface ExpenseService {

	public String insertExpense(Expenses expenseModel);

	public String updateExpenseStatus(int expenseId, String paymentStatus);

}
