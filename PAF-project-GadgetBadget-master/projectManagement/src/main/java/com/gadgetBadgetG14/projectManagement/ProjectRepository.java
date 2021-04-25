package com.gadgetBadgetG14.projectManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;  
import java.sql.*;

public class ProjectRepository {
	
	//A common method to connect to the DB
	public Connection getconnection() {
	
	Connection con = null;
	//Provide the correct details: DBServer/DBName, username, password
	String url = "Jdbc:mysql://localhost:3306/gadget_badget";
	String username = "root";
	String password = "";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
		
	}catch (Exception e) {
		System.out.println(e);
	}
	
	System.out.println("success");
	return con;

}
	
	List<Project> projects;
	
	public ProjectRepository() {
		
		
		String url = "Jdbc:mysql://localhost:3306/gadget_badget";
		String username = "root";
		String password = "";
		Connection con = getconnection();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		projects = new ArrayList<>();
		
		Project p1 = new Project();
		
		p1.setP_id(1);
		p1.setP_name("GadgetBadget");
		p1.setStatus("Completed");
		p1.setStartDate("2020-05-01");
		p1.setEndDate("2020-12-01");
		p1.setDescription("Project 1 - Web app");
		p1.setBudget(50000);
		p1.setPrice(75000);
		p1.setResearcher_id(2);
		p1.setSponsor_id(1);
		projects.add(p1);
	}
	
	public List<Project> getAllProjects(){
		
		return projects;
	}
	
	public Project createProject(Project p1) {
		String output = "";
		
		// create a prepared statement
		String insertSql = "INSERT INTO `projects`(`p_id`, `p_name`, `status`, `startDate`, `endDate`, `description`, `budget`, `price`, `researcher_id`, `sponsor_id`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		Connection con = getconnection();
		try {
			
			PreparedStatement st = con.prepareStatement(insertSql);
			
			// binding values
			st.setInt(1, p1.p_id);
			st.setString(2, p1.p_name);
			st.setString(3, p1.status);
			st.setString(4, p1.startDate);
			st.setString(5, p1.endDate);
			st.setString(6, p1.description);
			st.setFloat(7, p1.budget);
			st.setFloat(8, p1.price);
			st.setInt(9, p1.researcher_id);
			st.setInt(10, p1.sponsor_id);
			
			// execute the statement
			st.executeUpdate();
			con.close();
			
			output = "success";
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		projects.add(p1);
		System.out.println(projects);
		
		return (p1);
	}

	
	
	public String readProjects() {
		String output = "";
		
		try {
			//connect to database
			Connection con = getconnection();
			
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Project ID</th><th>Project Name</th><th>Status</th><th>Start Date</th><th>End Date</th><th>Description</th><th>Budget</th><th>Price</th><th>Researcher ID</th><th>Sponsor ID</th>" +
			"<th>Update</th><th>Remove</th></tr>";
			String query = "SELECT * FROM `projects`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String p_id = Integer.toString(rs.getInt("p_id"));
				String p_name = rs.getString("p_name");
				String status = rs.getString("status");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");
				String description = rs.getString("description");
				String budget = Float.toString(rs.getFloat("budget"));
				String price = Float.toString(rs.getFloat("price"));
				String researcher_id = Integer.toString(rs.getInt("researcher_id"));
				String sponsor_id = Integer.toString(rs.getInt("sponsor_id"));
				
				// Add into the html table
				output += "<tr><td>" + p_id + "</td>";
				output += "<td>" + p_name + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + startDate + "</td>";
				output += "<td>" + endDate + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + budget + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + researcher_id + "</td>";
				output += "<td>" + sponsor_id + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='items.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + p_id + "'>" + "</form></td></tr>";
			}
			
			//close the db connection
			con.close();
			
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e){
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
	public Project getprojectid(int p_id) {
		String getsql = "SELECT * FROM `projects` WHERE `p_id` = '"+p_id+"' ";
		Project pr = new Project();
		Connection con = getconnection();
		
		try {
			Statement st = con.createStatement();
			ResultSet p1 = st.executeQuery(getsql);
			
			while(p1.next()) {
				
				pr.setP_id(p1.getInt(1));
				pr.setP_name(p1.getString(2));
				pr.setStatus(p1.getString(3));
				pr.setStartDate(p1.getString(4));
				pr.setEndDate(p1.getString(5));
				pr.setDescription(p1.getString(6));
				pr.setBudget(p1.getFloat(7));
				pr.setPrice(p1.getFloat(8));
				pr.setResearcher_id(p1.getInt(9));
				pr.setSponsor_id(p1.getInt(10));
				
			}
			
			//con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pr;
	}

	
	public String deleteProject(int p_id) {
		String output = "";
		try {
			Connection con = getconnection();
			
			String deleteProject = "DELETE FROM projects WHERE p_id = '"+p_id+"'";
			PreparedStatement ps = con.prepareStatement(deleteProject);
			ps.execute();
			
			output = "Delete Successful";
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
		
	public String updateProject(Project project) {
		String output = "";
		
		try {
			Connection con = getconnection();
			
			String updateProject = "UPDATE `projects` SET `p_id`='"+project.getP_id()+"',p_name='"+project.getP_name()+"',status='"+project.getStatus()+"',startDate='"+project.getStartDate()+"',endDate='"+project.getEndDate()+"',description='"+project.getDescription()+"',budget='"+project.getBudget()+"',price='"+project.getPrice()+"',researcher_id='"+project.getResearcher_id()+"',sponsor_id='"+project.getSponsor_id()+"' WHERE p_id='"+project.getP_id()+"'";
			PreparedStatement st = con.prepareStatement(updateProject);

			st.executeUpdate();
			output = "Updated Successful";
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return output;

	}
}
