package bigmachine;

import fsm.Machine;
import fsm.State;
import fsm.Transit;

public class Second {
	
	public static void main(String[] args) {
		test1();
		
	}
	
	private static void test1() {
		Machine m = new Machine();
		
		State s1 = new State("s1");

		State s2 = new State("s2");
		s2.setAccept(true);
		
		TransitWithListener t1 = new TransitWithListener("t1");
		t1.setEvt("a");
		t1.setFrom(s1);
		t1.setTo(s2);
		
		//Listener l = new Listener();
		//l.setName("l1");
		//t1.addAfterListener(l);
		
		
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
