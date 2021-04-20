package com;

import model.Project;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Projects")
public class ProjectService {

	Project projectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return projectObj.readProjects();
	}
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("pmid") String pmid, @FormParam("fbid") String fbid, @FormParam("Project_Name") String Project_Name, @FormParam("Start_Date") String Start_Date ,@FormParam("Deadline_Date") String Deadline_Date, @FormParam("Project_Status") String Project_Status, @FormParam("Project_Fund_Amt") String Project_Fund_Amt, @FormParam("Project_Sell_Amt") String Project_Sell_Amt) {
		String output = projectObj.insertProject(pmid,fbid,Project_Name,Start_Date,Deadline_Date,Project_Status,Project_Fund_Amt,Project_Sell_Amt);
		return output;
	}



}
