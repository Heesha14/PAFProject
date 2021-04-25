  
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

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData) {
//Convert the input string to a JSON object 
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
//Read the values from the JSON object
		String Project_ID = projectObject.get("Project_ID").getAsString();
		String pmid = projectObject.get("pmid").getAsString();
		String fbid = projectObject.get("fbid").getAsString();
		String Project_Name = projectObject.get("Project_Name").getAsString();
		String Start_Date = projectObject.get("Start_Date").getAsString();
		String Deadline_Date = projectObject.get("Deadline_Date").getAsString();
		String Project_Status = projectObject.get("Project_Status").getAsString();
		String Project_Fund_Amt = projectObject.get("Project_Fund_Amt").getAsString();
		String Project_Sell_Amt = projectObject.get("Project_Sell_Amt").getAsString();
		String output = projectObj.updateProject(Project_ID, pmid, fbid, Project_Name, Start_Date, Deadline_Date, Project_Status, Project_Fund_Amt, Project_Fund_Amt);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());

//Read the value from the element <Project_ID>
		String Project_ID = doc.select("Project_ID").text();
		String output = projectObj.deleteProject(Project_ID);
		return output;
	}
	
//	
//	@DELETE
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteProject(@FormParam("Project_ID") String Project_ID) {
//		return  projectObj.deleteProject(Project_ID);
//	}
	
	@PUT
    @Path("updateFundStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateFundStatus(String request) {
//Convert the input string to a JSON object
        JsonObject fundObj = new JsonParser().parse(request).getAsJsonObject();
//Read the values from the JSON object
        String fund_ID = fundObj.get("fundId").getAsString();
        String fundStatus = fundObj.get("status").getAsString();
        String output = projectObj.updatePaymentsStatus(fund_ID, fundStatus);
        return output;
    }

	
}
