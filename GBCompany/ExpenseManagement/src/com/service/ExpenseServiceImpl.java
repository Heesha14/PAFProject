package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Expenses;
import com.repo.ExpenseRepo;
import com.repo.ExpenseRepoImpl;

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
