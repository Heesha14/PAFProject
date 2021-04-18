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

            sql = "INSERT INTO Expenses(expenseTitle,expenseDesc,expenseAmount,expenseStatus,expenseDate) " +
                    "VALUES (?,?,?,?,?)";


            System.out.println("Queryyyyyyyyyyyyyy  " + sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, expenseModel.getExpenseTitle());
            preparedStatement.setString(2, expenseModel.getExpenseDesc());
            preparedStatement.setDouble(3, expenseModel.getExpenseAmount());
            preparedStatement.setString(4, expenseModel.getExpenseStatus());
            preparedStatement.setString(5, expenseModel.getExpenseDate());

            preparedStatement.execute();

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
			msg.addProperty("expenseID", expenseModel.getExpenseId());
			msg.addProperty("expenseAmount", expenseModel.getExpenseAmount());
			msg.addProperty("expenseStatus", expenseModel.getExpenseStatus());

			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
			Client client = ClientBuilder.newBuilder().register(feature).build();
			WebTarget webTarget = client.target("http://localhost:8081/AuthService/AuthService").path("users");
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
			WebTarget webTarget = client.target("http://localhost:8081/AuthService/AuthService").path("users");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(msg.toString(), MediaType.APPLICATION_JSON));

		} catch (Exception e) {
			System.out.println("Error in making payments " + e);
			return "Not send to payments";
		}

		return "Payments status updated";
	}

}
