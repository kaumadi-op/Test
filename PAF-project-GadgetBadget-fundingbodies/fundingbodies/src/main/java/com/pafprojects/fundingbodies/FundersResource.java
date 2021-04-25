package com.pafprojects.fundingbodies;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/funder")
public class FundersResource {
	
	FunderRepository sr = new FunderRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funders> getAllFunders()
	{
			
		return sr.getAllFunders();
		
	}
	@POST
	@Path("/funders")
	@Consumes(MediaType.APPLICATION_JSON)
	public Funders createFunders(Funders f1) {
	
	return sr.createFunders(f1);
	}
	//@GET
	//@Path("/Funders/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	//public String getCities(@PathParam("id") String id) {
	//Funders res = new Funders();
	//res = sr.getfundersid(Integer.parseInt(id));
	//Gson test = new Gson();
	//String jsonObject = test.toJson(res);
	//return jsonObject;
	//}

	
	
	@DELETE
	@Path("/deleteFunder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteFunders(@PathParam("id") int id) {
		return sr.deleteFunders(id);
	}
	
	@GET
	@Path("/funders/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCities1(@PathParam("id") String id) {
	Funders res = new Funders();
	res = sr.getfundersid(Integer.parseInt(id));
	Gson test = new Gson();
	String jsonObject = test.toJson(res);
	return jsonObject;
	}
	
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return sr.readFundBody();
	}
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFunders(Funders funder) 
	{ 
	
	 return sr.updateFunders(funder); 
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funders> getallfunders(){
		return sr.getAllFunders();
	}
}
























