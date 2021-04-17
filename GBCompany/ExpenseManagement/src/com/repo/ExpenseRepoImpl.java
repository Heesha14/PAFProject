package com.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.DBConn;
import com.model.Expenses;

public class ExpenseRepoImpl implements ExpenseRepo{


	private static Connection conn;

	@Override
	public String addExpense(Expenses expenseModel) {
		String sql = null;
		try {
			conn = DBConn.getConnection();

				sql = "INSERT INTO Expenses(expenseTitle,expenseDesc,expenseAmount,expenseStatus,expenseDate) " +
						"VALUES (?,?,?,?,?)";


			System.out.println("Queryyyyyyyyyyyyyy  "+ sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, expenseModel.getExpenseTitle());
			preparedStatement.setString(2, expenseModel.getExpenseDesc());
			preparedStatement.setDouble(3, expenseModel.getExpenseAmount());
			preparedStatement.setString(4, expenseModel.getExpenseStatus());
			preparedStatement.setString(5, expenseModel.getExpenseDate());

			preparedStatement.execute();

			return "Expense successfully inserted";

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return "Error occured when adding expense";

		} finally {
			/*
			 * database connectivity closed at the end of transaction
			 */
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}


}
