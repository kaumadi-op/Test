package com.pafprojects.fundingbodies;

import java.util.ArrayList;
import java.util.List;


import java.sql.*;

public class FunderRepository {
	
	public Connection getconnection() {
		   Connection con = null;
			String url ="Jdbc:mysql://localhost:3306/fundingbodies";
			String username = "root";
			String password = "";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,username,password);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("success");
			return con;
		}
	
	Connection con = null;
	List<Funders> funders;

	public FunderRepository() {
		
		String url = "Jdbc:mysql://localhost:3306/fundingbodies";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
		}
		catch (Exception e) {
			System.out.println(e);
			
		}
		
	funders = new ArrayList<>();
	
	Funders f1 = new Funders();
	f1.setName("Kithmi");
	f1.setEmail("123@gmail.com");
	f1.setId(1);
	
	Funders f2 = new Funders();
	f2.setName("Kaumadi");
	f2.setEmail("1234@gmail.com");
	f2.setId(2);
	
	Funders f3 = new Funders();
	f3.setName("Isuri");
	f3.setEmail("12345@gmail.com");
	f3.setId(3);
	
	 funders.add(f1);
	 funders.add(f2);
	 funders.add(f3);
	
	}
	public List<Funders> getAllFunders(){
		return funders;
	}
	public Funders createFunders(Funders f1) {
		String insertSql = "INSERT INTO `funders`(`id`, `name`, `email`) VALUES (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(insertSql);
			st.setInt(1, f1.id);
			st.setString(2, f1.name);
			st.setString(3, f1.email);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
			
			
		}	
		
		funders.add(f1);
		System.out.println(funders);
		return f1;
	}
	public Funders getfundersid(int id)
	{
		
		String getsql = "select * from `funders` where `id` = '"+id+"'";
		Funders cd = new Funders();
		Connection con = getconnection();
		
		try {
			Statement st = con.createStatement();
			ResultSet f1 = st.executeQuery(getsql);
			
			while(f1.next()) {
				
				cd.setId(f1.getInt(1));
				cd.setName(f1.getString(2));
				cd.setEmail(f1.getString(3));
				
			}
			
			//con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cd;

	}	
	
	public String deleteFunders(int id) {
		String output = "";
		try {
			Connection con = getconnection();
			
			String deleteFunders = "DELETE FROM `funders` WHERE `id`= '"+id+"'";
			PreparedStatement ps = con.prepareStatement(deleteFunders);
			
			ps.execute();
			
			output = "Deleted Successful";
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
	public String updateFunders(Funders funder) {
		String output = "";
		
		try {
			Connection con = getconnection();
			
			String updateFunders = "UPDATE `funders` SET `id`='"+funder.getId()+"',name='"+funder.getName()+"',email='"+funder.getEmail()+"' WHERE id='"+funder.getId()+"'";
			PreparedStatement st = con.prepareStatement(updateFunders);

			st.executeUpdate();
			output = "Updated Successful";
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return output;

	}
	

	public String readFundBody() {
		String output = "";
		
		try {
			//connect to database
			Connection con = getconnection();
			
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th>"+
			"<th>Update</th><th>Remove</th></tr>";
			String query = "SELECT * FROM funders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				+ "<td><form method='post' action=''>"
				+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				+ "<input name='ID' type='hidden' value='" + id + "'>" + "</form></td></tr>";
			}
			
			//close the db connection
			con.close();
			
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e){
			output = "Error while reading the funding bodies.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}















	










	
