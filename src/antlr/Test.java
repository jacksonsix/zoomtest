package antlr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Test {
   public static void main(String[] args) throws IOException {
	   
		//test1();
	   //test2();
	   //test3();
	   test4();
	   
   }
   
   private static void test4() throws IOException {
	   String baseFolder ="C:/Users/jliang/DEV_LAB/Ordering3/github/project-domain-service/src";
	   String outputfile="C:/_dev/PythonTest/sql.txt";
	   
	   //ScanProject p = new ScanProject();
	   //p.setBaseFolder(baseFolder);
	   //p.setOutputfile(outputfile);
	   
	   //p.scan();
	   
	   ScanClass pc = new ScanClass();
	   pc.setBaseFolder(baseFolder);
	   pc.setOutputfilename(outputfile);
	   pc.scan();
   }
   
   private static void test3() throws IOException {
	   CharStream is = (CharStream) CharStreams.fromFileName("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\antlr\\StockEvent.java");
	   // parse
        Java8Lexer lexer = new Java8Lexer(is);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Java8Parser parser = new Java8Parser(tokens);
		ParseTree tree = parser.compilationUnit();
		
		//System.out.println(tree.toStringTree(parser));
		
		FunctionAndMembers up = new FunctionAndMembers();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(up,tree);
		
        Set<String> imp = up.getImports();
        for(String i: imp) {
        	//System.out.println(i);
        }
        Set<String> funs = up.getFunctions();
        for(String i: funs) {
        	//System.out.println(i);
        }
        
        Set<String> nodes = up.getNodes();
        for(String i: nodes) {
        	//System.out.println(i);
        }
        //System.out.println("Path");
        Set<String> edges = up.getEdges();
        for(String i: edges) {
        	//System.out.println(i);
        }
		
   }
   
   private static void test1() throws IOException {
	 //ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("C:\\_dev\\testspace\\antlr\\src\\sql\\test.sql")); 
	   CharStream is = (CharStream) CharStreams.fromFileName("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\antlr\\StockEvent.java");
	   // parse
        Java8Lexer lexer = new Java8Lexer(is);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Java8Parser parser = new Java8Parser(tokens);
		ParseTree tree = parser.compilationUnit();
		
		//System.out.println(tree.toStringTree(parser));
		
		UpdateCode up = new UpdateCode(tokens);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(up,tree);
		
		Collection<String> r = up.getResult();
		for(String n: r) {
			System.out.println(n);
		}
		
		System.out.println(up.rewriter.getText());
   }
   
   private static void test2() throws IOException {
	   List<String> filenames = new LinkedList<>();
	   Set<String> nodes = new HashSet<>();
	   Set<String> edges = new HashSet<>();
	   
	   
	   String filename ="C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\c.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int state = 0;
		    
		    while ((line = br.readLine()) != null) {
		    	//System.out.println(line);
		    	filenames.add(line);
		    }
		}catch(Exception e) {
			
		}
	   
	
	   //filenames.add("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\antlr\\StockEvent.java");
	   for(String name :filenames) {
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
				edges.add(up.getClassname() +"->"+n +"," + up.getClassname()+","+n);
			}
			//System.out.println(up.getClassname());
			nodes.add(up.getClassname());
			 
			//break;
	   }
	   
		 
		 
		  
		
      String outfile ="C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\output.txt";
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
   
   
   
   
}
