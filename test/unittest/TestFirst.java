package unittest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import graph.Graph;
import graph.INode;


public class TestFirst {
	
	@Test
    public void test1() {
		System.out.println("come here");
	}
	
	@Test
	public void test2() {
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\a.txt");
		Graph b = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\b.txt");
        //Graph c = mergeMachineOnInOut(a,b,"t1","t2");
        //c.printSetup();
        //String file = GraphToString(c);
		a.setup();
        INode n = a.getNodeByName("a0");
        Set<INode> grey = new HashSet<>();
        grey.add(n);
        DFS(a,n,grey);
	}

}
