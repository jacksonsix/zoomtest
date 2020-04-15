package other;

import java.util.Optional;

import com.sun.istack.Nullable;



public class OPtest {
	
	public static void main(String[] args) {
		test1(null,null);
	}
	
	private static void test1( @Nullable String name, @Nullable Integer id) {
		System.out.println("inside");
		
	}
	

}
