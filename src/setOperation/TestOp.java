package setOperation;

import java.util.HashSet;
import java.util.Set;

public class TestOp {
	
	public static void main(String[] args) {
		
		Set<Record> a1 = new HashSet<>();
		Set<Record> a2 = new HashSet<>();
		
		for(int i=0;i< 10;i++) {
			
			Record r = new Record();
			r.addCol("Id", i);
			r.addCol("name", i+"r");
			a1.add(r);
		}
		
		print(a1);
	}
	
	
	private static void print(Set<Record> tab) {
		for(Record r : tab) {
			System.out.print(r.getId() +": " );
			for(String name: r.getCols()) {
				System.out.print(name+", " );
			}
			System.out.println();
			for(Object o: r.getValues()) {
				System.out.print("   "+o.toString()+", " );
			}
			System.out.println();
		}

	}

}
