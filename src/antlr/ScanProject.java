package antlr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ScanProject {
	String baseFolder="";
	String outputfile="";
	Set<String> files;
	Set<String> nodes;
	Set<String> edges;
	
	public ScanProject() {
		files = new HashSet<>();
		nodes = new HashSet<>();
		edges = new HashSet<>();
	}
	public void getAllfiles() {
		files = FileUtil.listSubFolder(baseFolder);
		for(String f : files) {
			//System.out.println(f);
		}
	}
	
	public void scanImports() throws IOException {
		for(String name :files) {
			   CharStream is = (CharStream) CharStreams.fromFileName(name);
			   // parse
		        Java8Lexer lexer = new Java8Lexer(is);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				Java8Parser parser = new Java8Parser(tokens);
				ParseTree tree = parser.compilationUnit();
							
				PackageAndRefs up = new PackageAndRefs();
				ParseTreeWalker walker = new ParseTreeWalker();
				walker.walk(up,tree);
				
				Collection<String> r = up.getImports();
				for(String n: r) {
					//System.out.println(n);
					nodes.add(n);
					edges.add(up.getClassname() +"->"+n +"|" + up.getClassname()+"|"+n);
				}
				//System.out.println(up.getClassname());
				nodes.add(up.getClassname());	 
				//break;
		   }
	}
	
	
	public void scan() throws IOException {
		
		getAllfiles();
		scanImports();
		//writeoutFile(outputfile);
		prepareSql(outputfile);     			

	}
	   
	private void writeoutFile(String outfile) {
	      //String outfile ="C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\output.txt";
	      try {
	          FileWriter myWriter = new FileWriter(outfile);
	          //myWriter.write("Files in Java might be tricky, but it is fun enough!");
	          for(String n: nodes) {
	        	  //System.out.println(n);
	        	  myWriter.write(n+'\n');
	          } 
	          myWriter.write("Path"+'\n');
	          for(String n: edges) { 
	        	  //System.out.println(n);
	        	  myWriter.write(n+'\n');
	          }
	          
	          myWriter.close();
	          System.out.println("Successfully wrote to the file.");
	        } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	        }
	}
	
	private void prepareSql(String outfile) {
		 try {
	          FileWriter myWriter = new FileWriter(outfile);
	          //String sql = " INSERT INTO projectClass (className) VALUES('";
	          //String end ="');";

	          String sql = "INSERT INTO projectClass (className) VALUES('";
	          String end ="');";
	          for(String n: nodes) { 
	        	  ;
	        	  myWriter.write(sql+n.trim()+end);
	        	  
	          }
	          sql = "INSERT INTO projectClassImports (attrId, className, importClass) VALUES('";
	          String del="', '";
	          for(String n: edges) { 
	        	  String[] ss = n.trim().split("\\|");
	        	  
	        	  myWriter.write(sql+ss[1] +del + ss[2]+ end);
	        	  
	          }
	          
	          myWriter.close();
	          System.out.println("Successfully wrote to the file.");
	        } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	        }
	}
	public String getBaseFolder() {
		return baseFolder;
	}
	public void setBaseFolder(String baseFolder) {
		this.baseFolder = baseFolder;
	}
	public String getOutputfile() {
		return outputfile;
	}
	public void setOutputfile(String outputfile) {
		this.outputfile = outputfile;
	}
	
	

}
