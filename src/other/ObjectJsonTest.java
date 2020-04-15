package other;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import graph.Graph;
import graph.INode;
import tools.GraphTool;

public class ObjectJsonTest {
	  public static void main(String[] a) 
	    { 
	  
	        // Creating object of Organisation 
	        Organisation org = new Organisation(); 
	  
	        // Insert the data into the object 
	        org = ObjectToJson.getObjectData(org); 
	        Graph ga = GraphTool.readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\a.txt");
	        ga.setup();
	        
	        INode n = ga.getNodeByName("a0");
	        // Creating Object of ObjectMapper define in Jakson Api 
	        ObjectMapper Obj = new ObjectMapper(); 
	  
	        try { 
	  
	            // get Oraganisation object as a json string 
	            String jsonStr = Obj.writeValueAsString(n); 
	  
	            // Displaying JSON String 
	            System.out.println(jsonStr); 
	        } 
	  
	        catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
}



class ObjectToJson { 
  
  
    // Get the data to be inserted into the object 
    public static  Organisation getObjectData(Organisation org) 
    { 
  
        // Insert the data 
        org.setName("GeeksforGeeks"); 
        org.setDescription("A computer Science portal for Geeks"); 
        org.setEmployees(2000); 
  
        // Return the object 
        return org; 
    } 
 }
 class Organisation { 
    private String organisation_name; 
    private String description; 
    private int Employees; 
  
    // Calling getters and setters 
    public String getOrganisation_name() 
    { 
        return organisation_name; 
    } 
  
    public void setName(String organisation_name) 
    { 
        this.organisation_name = organisation_name; 
    } 
  
    public String getDescription() 
    { 
        return description; 
    } 
  
    public void setDescription(String description) 
    { 
        this.description = description; 
    } 
  
    public int getEmployees() 
    { 
        return Employees; 
    } 
  
    public void setEmployees(int employees) 
    { 
        Employees = employees; 
    } 
  
    // Creating toString 
    @Override
    public String toString() 
    { 
        return "Organisation [organisation_name="
            + organisation_name 
            + ", description="
            + description 
            + ", Employees="
            + Employees + "]"; 
    } 
} 