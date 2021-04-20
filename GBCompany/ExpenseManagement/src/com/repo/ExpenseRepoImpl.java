package com.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.DBConn;
import com.google.gson.JsonObject;
import com.model.Expenses;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExpenseRepoImpl implements ExpenseRepo {


	private static Connection conn;

	@Override
	public String addExpense(Expenses expenseModel) {
		String sql = null, output;
		try {
			conn = DBConn.getConnection();

			sql = "INSERT INTO Expenses(expenseId,expenseTitle,expenseDesc,expenseAmount,expenseStatus,expenseDate) " +
					"VALUES (?,?,?,?,?,?)";


			System.out.println("Queryyyyyyyyyyyyyy  " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, countExpenseList());
			preparedStatement.setString(2, expenseModel.getExpenseTitle());
			preparedStatement.setString(3, expenseModel.getExpenseDesc());
			preparedStatement.setDouble(4, expenseModel.getExpenseAmount());
			preparedStatement.setString(5, expenseModel.getExpenseStatus());
			preparedStatement.setString(6, expenseModel.getExpenseDate());
			
			System.out.println(expenseModel.getExpenseDate());
			System.out.println(expenseModel.getExpenseDate().substring(0, 10));

			preparedStatement.execute();

			output = "Expense successfully inserted";

			output += InsertExpenseToPayement(expenseModel);

			return output;

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

	private String InsertExpenseToPayement(Expenses expenseModel) {
		try {
			JsonObject msg = new JsonObject();
			msg.addProperty("expenseId", countExpenseList()-1);
			msg.addProperty("amount", expenseModel.getExpenseAmount());
			msg.addProperty("payment_status", expenseModel.getExpenseStatus());
			msg.addProperty("paid_date",expenseModel.getExpenseDate().substring(0, 10));

			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
			Client client = ClientBuilder.newBuilder().register(feature).build();
			WebTarget webTarget = client.target("http://localhost:8443/PaymentManagement/PaymentService").path("Payments/addexpense");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(msg.toString(), MediaType.APPLICATION_JSON));

		} catch (Exception e) {
			System.out.println("Error in InsertExpenseToPayement " + e);
			return "Not send to payments";
		}

		return "And Send to Payments";
	}

	public String makePayment(String expenseID){
		try {
			JsonObject msg = new JsonObject();
			msg.addProperty("expenseID", expenseID);
			msg.addProperty("paymentDate", String.valueOf(java.time.LocalDate.now()));
			msg.addProperty("paymentStatus", "Paid");

			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
			Client client = ClientBuilder.newBuilder().register(feature).build();
			WebTarget webTarget = client.target("http://localhost:8443/PaymentManagement/PaymentService").path("Payments/addexpense");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(msg.toString(), MediaType.APPLICATION_JSON));

		} catch (Exception e) {
			System.out.println("Error in making payments " + e);
			return "Not send to payments";
		}

		return "Payments status updated";
	}

	public int generateId() {
		int count = 1000;
		count++;
		return count;
	}

	public int countExpenseList() {
		String sql = null, output;
		int dataCount = 0;
		try {
			conn = DBConn.getConnection();
			sql = "SELECT COUNT(*) cnt FROM expenses";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.execute();

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()){

				dataCount = rs.getInt("cnt");

			}


		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;

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
		return dataCount + 1000;
		

	}

}
