package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Expenses;

/**
 * @author HeeshaJ
 *
 */
public interface ExpenseService {

	public String insertExpense(Expenses expenseModel);

}
