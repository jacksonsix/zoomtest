package antlr;


public class Gen {
	
	public static void main(String[] args) {

		//genSqilite();
		genJava();
		
		//genLog();
		
		//genJson();
		
		//genLispMachine();
	}
	
	private static void genLispMachine() {
		   
	    String[] arg0 = { "-visitor", "C:\\_dev\\testspace\\antlr\\src\\lispmachine\\LispMachine.g4", "-package", "lispmachine" };
	    org.antlr.v4.Tool.main(arg0);
	}
	
	private static void genSqlbase() {
   
	    String[] arg0 = { "-visitor", "C:\\_dev\\testspace\\antlr\\src\\sql\\SqlBase.g4", "-package", "sql" };
	    org.antlr.v4.Tool.main(arg0);
	}
	
	private static void genSqilite() {
	    String[] arg0 = { "-visitor", "C:\\_dev\\testspace\\antlr\\src\\sqlite\\SQLite.g4", "-package", "sqlite" };
	    org.antlr.v4.Tool.main(arg0);
	}

	
	private static void genJava() {
	    String[] arg0 = { "-visitor", "C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\antlr\\Java8.g4", "-package", "antlr" };
	    org.antlr.v4.Tool.main(arg0);
	}
	
	private static void genLog() {
	    String[] arg0 = { "-visitor", "C:\\_dev\\testspace\\antlr\\src\\log\\Log.g4", "-package", "log" };
	    org.antlr.v4.Tool.main(arg0);
	}
	
	
	private static void genJson() {
	    String[] arg0 = { "-visitor", "C:\\_dev\\testspace\\antlr\\src\\json\\Jcksn.g4", "-package", "json" };
	    org.antlr.v4.Tool.main(arg0);
	}
}

