package com.gadgetBadgetG14.payment;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PaymentRepository {
	public Connection getConnection() {
		
		Connection con = null;
		
		String url = "Jdbc:mysql://localhost:3307/paymentapi";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Successful");
		return con;
	}
	
	
	List<Payment> payments;
	public PaymentRepository() {
		
		Connection con = getConnection();
			
		payments = new ArrayList<>();
		
		Payment p1 = new Payment();
		p1.setPaymentId(123);
		p1.setAmount(5000);
		p1.setPayDate("Cash");
		p1.setCardHolder("Alwis");
		p1.setCardNo("4563 5678 2345 1234");
		p1.setCvv(145);
		p1.setExpDate("12-23");
		
		
		payments.add(p1);
	}
	
	public List<Payment> getAllPayment(){
		return payments;
	}
	
	public Payment createPayment(Payment p1) {
		
		String insertSql = "INSERT INTO `payment`(`paymentID`, `Amount`, `payDate`, `cardHolder`, `cardNo`, `cvv`, `expDate`) VALUES (?,?,?,?,?,?,?)";
		
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(insertSql);
			pst.setInt(1, p1.paymentId);
			pst.setInt(2, p1.amount);
			pst.setString(3, p1.payDate);
			pst.setString(4, p1.cardHolder);
			pst.setString(5, p1.cardNo);
			pst.setInt(6, p1.cvv);
			pst.setString(7, p1.expDate);
			
			pst.executeUpdate();
			
			//String output = "Inserted Successfully";
			
		}catch (Exception e) {
			System.out.println(e);
		}
		payments.add(p1);
		System.out.println(payments);
		return p1;
	}
	
	public Payment getpaymentId(int paymentId) {
		String getsql = "SELECT * FROM `payment` WHERE paymentID = '"+paymentId+"'";
		Payment pd = new Payment();
		Connection con = getConnection();
		
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(getsql);
			
			while(rs.next()) {
				
				pd.setPaymentId(rs.getInt(1));
				pd.setAmount(rs.getInt(2));
				pd.setPayDate(rs.getString(3));
				pd.setCardHolder(rs.getString(4));
				pd.setCardNo(rs.getString(5));
				pd.setCvv(rs.getInt(6));
				pd.setExpDate(rs.getString(7));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pd;
	}
	
	public String deletePayment(int paymentId) {
		
		String output = "";
		
		try {
			Connection con = getConnection();
			
			String deletePayment = "DELETE FROM `payment` WHERE paymentID = '"+paymentId+"' ";
			PreparedStatement pst = con.prepareStatement(deletePayment);
			pst.execute();
			
			output = "Successfully Deleted";
			con.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public String updatePayment(Payment payment) {
		
		String output = "";
		try {
			Connection con = getConnection();
			
			String updatePayment = "UPDATE `payment` SET `paymentID`='"+payment.getPaymentId()+"',`Amount`='"+payment.getAmount()+"',`payDate`='"+payment.getPayDate()+"',`cardHolder`='"+payment.getCardHolder()+"',`cardNo`='"+payment.getCardNo()+"',`cvv`='"+payment.getCvv()+"',`expDate`='"+payment.getExpDate()+"' WHERE paymentID = '"+payment.getPaymentId()+"'";
			PreparedStatement st = con.prepareStatement(updatePayment);
			
			st.executeUpdate();
			
			output = "Updated Successfully";
			
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public String readPayment() {
		
		String output = "";
		
		try {
			Connection con = getConnection();  //DB Connection
			
			if(con == null) 
			{return "Error while connecting to the database for reading.";}
			
				output = "<table border='1'><tr><th>Payment ID</th><th>Amount</th><th>PayDate</th><th>Card Holder</th><th>Card Number</th><th>CVV</th><th>Exp Date</th>" +
						"<th>Update</th><th>Remove</th></tr>";	
				String query = "SELECT * FROM `payment`";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				//move through the rows in the table
				while (rs.next()) {
					String paymentId = Integer.toString(rs.getInt("paymentID"));
					String amount = Integer.toString(rs.getInt("Amount"));
					String payDate = rs.getString("payDate");
					String cardHolder = rs.getString("cardHolder");
					String cardNo = rs.getString("cardNo");
					String cvv = Integer.toString(rs.getInt("cvv"));
					String expDate = rs.getString("expDate");
					
					
					// adding to table
					output += "<tr><td>" + paymentId + "</td>";
					output += "<td>" + amount + "</td>";
					output += "<td>" + payDate + "</td>";
					output += "<td>" + cardHolder + "</td>";
					output += "<td>" + cardNo + "</td>";
					output += "<td>" + cvv + "</td>";
					output += "<td>" + expDate + "</td>";
					
					//buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"	
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='itemID' type='hidden' value='" + paymentId + "'>" + "</form></td></tr>";
						
					
					
					}
				output += "</table>";
				
			
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	} 
}
