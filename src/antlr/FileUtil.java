package antlr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {
	
	public static void main(String[] args) {
		String dir ="C:\\Users\\jliang\\DEV_LAB\\Ordering3\\github\\project-domain-service\\src";
		//listSubFolder(dir);
		delFiles(dir);
	}
	
	public static void listSubFolder(String dir) {
		Set<String> nodes = new HashSet<>();
		Set<String> edges = new HashSet<>();
		list(dir,nodes,edges);
		for(String n: nodes) {
			System.out.println(n);
		}
		
		for(String n: edges) {
			//System.out.println(n);
		}
	}
	
	private static void list(String dir,Set<String> nodes, Set<String> edges) {
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
        String dirshort = dir.substring(dir.lastIndexOf("\\")+1);
		for (int i = 0; i < listOfFiles.length; i++) {
		  String filename = listOfFiles[i].getName();
		if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + filename);
		    nodes.add(dir+"\\"+filename);
		    edges.add(makeEdge(dirshort, filename));
		  } else if (listOfFiles[i].isDirectory()) {
		    //System.out.println("Directory " + filename);
		    //nodes.add(filename);
		    edges.add(makeEdge(dirshort, filename));
		    list(dir+"\\"+filename,nodes,edges);
		  }
		}
		

	}
	
	public static void listFolder(String dir) {
		Set<String> nodes = new HashSet<>();
		Set<String> edges = new HashSet<>();
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
        String dirshort = dir.substring(dir.lastIndexOf("\\")+1);
		for (int i = 0; i < listOfFiles.length; i++) {
		  String filename = listOfFiles[i].getName();
		if (listOfFiles[i].isFile()) {
		    System.out.println("File " + filename);
		    nodes.add(filename);
		    edges.add(makeEdge(dirshort, filename));
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + filename);
		    nodes.add(filename);
		    edges.add(makeEdge(dirshort, filename));
		  }
		}
		
		for(String n: nodes) {
			System.out.println(n);
		}
		
		for(String n: edges) {
			System.out.println(n);
		}
	}

	private static String makeEdge(String dirshort, String filename) {
		String t = dirshort+"->"+filename+"," + dirshort+","+filename;
		//String t = "f.edge('" + dirshort + "', '" + filename + "')";
		return t;
		
	}
	
	private static void delFiles(String dir) {
		Set<String> nodes = new HashSet<>();
		Set<String> edges = new HashSet<>();
		
	   Set<String> filenames = new HashSet<>();
	   String filename ="C:\\_dev\\PythonTest\\todel.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int state = 0;
		    
		    while ((line = br.readLine()) != null) {
		    	//System.out.println(line);
		    	//if(line.indexOf("/")>-1) continue;
		    	String shortf =  line.substring(line.lastIndexOf(".")+1);
		    	filenames.add(shortf);
		    }
		}catch(Exception e) {
			
		}
		
		dellist(dir,nodes,edges,filenames);
	}
	private static void dellist(String dir,Set<String> nodes, Set<String> edges,Set<String> todel) {
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
        String dirshort = dir.substring(dir.lastIndexOf("\\")+1);
		for (int i = 0; i < listOfFiles.length; i++) {
		  String filename = listOfFiles[i].getName();
		if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + filename);
		    nodes.add(dir+"\\"+filename);
		    edges.add(makeEdge(dirshort, filename));
		    
		    // del here
		    String f =filename.substring(0,filename.indexOf("."));
		    //System.out.println(f);
		   
		    if(todel.contains(f)) {
		    	//del
		    	File file = new File(dir+"\\"+filename); 
		          
		        file.delete(); 
		        System.out.println(f);
		    }
		    //
		  } else if (listOfFiles[i].isDirectory()) {
		    //System.out.println("Directory " + filename);

		    dellist(dir+"\\"+filename,nodes,edges,todel);
		  }
		}
		

	}
}
