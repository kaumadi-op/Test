package com.gadgetBadgetG14.projectManagement;



import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/projects")

public class ProjectResource {
	
	ProjectRepository pr = new ProjectRepository();
	
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllprojects() {
		return pr.getAllProjects();
    }
	
	
	@POST
	@Path("/project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Project createProject(Project p1) {
		return pr.createProject(p1);
	}
	
	@GET
	@Path("/project/{p_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProjects(@PathParam("p_id") String p_id) {
	Project res = new Project();
	res = pr.getprojectid(Integer.parseInt(p_id));
	Gson test = new Gson();
	String jsonObject = test.toJson(res);
	return jsonObject;
	}
	
	@DELETE
	@Path("/deleteProject/{p_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@PathParam("p_id") int p_id) {
		return pr.deleteProject(p_id);
	}
	
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateProject(Project p1) 
	{ 
		return pr.updateProject(p1);
	}
	
	
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return pr.readProjects();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjects(){
		return pr.getAllProjects();
	}

}
