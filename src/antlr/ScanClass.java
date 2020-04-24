package antlr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ScanClass {
	String baseFolder="";
	String className;
	String filename;
	String outputfilename;
	Set<String> files;
	Set<String> nodes;
	Set<String> edges;
	
	public ScanClass() {
		nodes = new HashSet<>();
		edges = new HashSet<>();
		files = new HashSet<>();
	}
	
	
	public void scan() throws IOException {
		
		files = FileUtil.listSubFolder(baseFolder);
		for(String f : files) {
			Set<String> ed = scanMembers(f);
			Set<String> ap = ed.stream().map(e -> e + "|" + this.className).collect(Collectors.toSet());
			edges.addAll(ap);
		}
		
		prepareSqlFile(outputfilename);
	}
	
	private Set<String> scanMembers(String filename) throws IOException {
		
		 CharStream is = (CharStream) CharStreams.fromFileName(filename);
		   // parse
	        Java8Lexer lexer = new Java8Lexer(is);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			Java8Parser parser = new Java8Parser(tokens);
			ParseTree tree = parser.compilationUnit();
	
			FunctionAndMembers up = new FunctionAndMembers();
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(up,tree);
			
	        Set<String> ed = up.getEdges();
	        this.className = up.getClassname();
	        return ed;     
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
	
	private void prepareSqlFile(String outfile) {
	     
	      try {
	          FileWriter myWriter = new FileWriter(outfile);
              String sql ="INSERT INTO projectMethodRefType (className, classMethod, classMethodRefType) VALUES( '";
              String end ="');";
              String del="', '";
              
              for(String n: edges) { 
	        	  String[] ss = n.trim().split("\\|");        	  
	        	  myWriter.write(sql+ss[3] + del + ss[1] +del + ss[2]+end+'\n');
	          }
	          
	          myWriter.close();
	          System.out.println("Successfully wrote to the file.");
	        } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	        }
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getOutputfilename() {
		return outputfilename;
	}


	public void setOutputfilename(String outputfilename) {
		this.outputfilename = outputfilename;
	}


	public String getBaseFolder() {
		return baseFolder;
	}


	public void setBaseFolder(String baseFolder) {
		this.baseFolder = baseFolder;
	}
	
	
	

}
