package tools;

public class GenCode {
	String template="line1\n"
			        + "line2";
	public String getTemplate() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String template="line1 \n line2";
		System.out.print(template);
	}
	
	

}
