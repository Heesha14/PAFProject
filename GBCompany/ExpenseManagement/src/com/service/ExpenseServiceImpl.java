package com.service;

import com.model.Expenses;
import com.repo.ExpenseRepo;
import com.repo.ExpenseRepoImpl;

/**
 * @author Jaanvi.S.C.H IT19801100
 *
 */
public class ExpenseServiceImpl implements ExpenseService {

	ExpenseRepo expenseRepo = new ExpenseRepoImpl();

	@Override
	public String insertExpense(Expenses expenseModel) {
		return expenseRepo.addExpense(expenseModel);
	}

	@Override
	public String updateExpenseStatus(int expenseId, String paymentStatus) {
		return expenseRepo.updateStatus(expenseId,paymentStatus);
	}


}
