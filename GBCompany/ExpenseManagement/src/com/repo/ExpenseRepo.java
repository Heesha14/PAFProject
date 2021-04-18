package com.repo;

import java.sql.SQLException;
import java.util.List;

import com.model.Expenses;

/**
 * @author HeeshaJ
 *
 */
public interface ExpenseRepo {


    public String addExpense(Expenses expenseModel);

}