package fsm;


public class First {
	public static void main(String[] args) {
		test4();
		
		
	}
	
	private static void test4() {
		Machine m = new Machine();
		m.readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\work.txt");
		m.printSetup();
		
		String test = "ab";
		/*
		for(int i=0; i< test.length(); i++) {
			String ch = String.valueOf(test.charAt(i));
			m.trigger(ch);
		}
		*/
		System.out.println("current state: " + m.getCurrentState().getName());
		
	}
	
	private static void test3() {
		Machine m = new Machine();
		m.readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\machine1.txt");
		m.printSetup();
		
		String test = "ab";
		
		for(int i=0; i< test.length(); i++) {
			String ch = String.valueOf(test.charAt(i));
			m.trigger(ch);
		}
		System.out.println("current state: " + m.getCurrentState().getName());
		
	}
    private static void test2() {
    	
    	Machine m = new Machine();
    	State q1 = new State("q1");
		State q2 = new State("q2");
		State q0 = new State("q0");
		
		Transit t1 = new Transit("t1");
		t1.setEvt("b");
		t1.setFrom(q0);
		t1.setTo(q0);
		
		Transit t2 = new Transit("t2");
		t2.setEvt("a");
		t2.setFrom(q0);
		t2.setTo(q1);
		
		Transit t3 = new Transit("t3");
		t3.setEvt("a");
		t3.setFrom(q1);
		t3.setTo(q1);
		
		Transit t4 = new Transit("t4");
		t4.setEvt("b");
		t4.setFrom(q1);
		t4.setTo(q2);
		
		Transit t5 = new Transit("t5");
		t5.setEvt("a");
		t5.setFrom(q2);
		t5.setTo(q2);
		
		Transit t6 = new Transit("t6");
		t6.setEvt("b");
		t6.setFrom(q2);
		t6.setTo(q2);
		
		
		m.addState(q0)
		.addState(q1)
		.addState(q2)
		.addTansit(t1)
		.addTansit(t2)
		.addTansit(t3)
		.addTansit(t4)
		.addTansit(t5)
		.addTansit(t6)
		.setup()
		.setCurrentState(q0);
		
		m.printSetup();
		
		String test = "aaaaaaa";
		
		for(int i=0; i< test.length(); i++) {
			String ch = String.valueOf(test.charAt(i));
			m.trigger(ch);
		}
		System.out.println("current state: " + m.getCurrentState().getName());
		
    }
	private static void test1() {
		Machine m = new Machine();
		
		State s1 = new State("s1");

		State s2 = new State("s2");
		s2.isAccept = true;
		
		Transit t1 = new Transit("t1");
		t1.setEvt("a");
		t1.setFrom(s1);
		t1.setTo(s2);
		
		
		
		m.addState(s1);
		m.addState(s2);
		
		m.addTansit(t1);
	
		
		m.setup()
		  .setCurrentState(s1)
		  .trigger("a");
		
		m.printSetup();
		
		System.out.println("current state: " + m.getCurrentState().getName());
	}
	
	
}














