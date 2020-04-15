package graphattrib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import graph.Graph;
import graph.INode;
import graph.IPath;
import tools.GraphTool;

public class GraphWithAttrib extends Graph{
	
	List<IEvent> events;
	public GraphWithAttrib(){
		events = new LinkedList<>();
	}
	
	public GraphWithAttrib(Graph g) {
		super.setSetup_nodes(g.getSetup_nodes());
		super.setSetup_paths(g.getSetup_paths());
		super.setup();
		events = new LinkedList<>();
	}
	public static void main(String[] args) {
		
		
		Graph a = GraphTool.readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\a.txt"); 
		GraphWithAttrib t = new GraphWithAttrib(a);
		INode begin = t.getNodeByName("a0");
		t.walk(begin);
		
		for(IEvent e: t.events) {
			System.out.println(e.getName() +" " + e.getValue());
		}
		
		t.handleEvents();
	}
	
	
	public void walk(INode begin) {
		Queue<INode> grey = new LinkedList<>();
		Set<INode> black = new HashSet<>();
		Set<IPath> blackcol = new HashSet<>();
		
		grey.add(begin);
		
		while(grey.size() > 0) {
			INode test = grey.poll();
			black.add(test);
			events.add(new Event("enterNode",test.getName()));
			Set<IPath> ups = test.getUp();
			Set<IPath> downs = test.getDown();
			ups.addAll(downs);
			ups.removeAll(blackcol);
			for(IPath p: ups) {
				events.add(new Event("enterPath",p.getName()));
				INode from = p.getFrom();
				INode to = p.getTo();
				blackcol.add(p);
				
				if(!black.contains(from)) grey.add(from);
				if(!black.contains(to))  grey.add(to);
				events.add(new Event("leavePath",p.getName()));
			}
			
			events.add(new Event("leaveNode",test.getName()));
		}
	}
	
	public void handleEvents() {
		Listener l = new Listener();
		for(IEvent e: events) {
			l.process(e);
			INode n = getNodeByName(e.getValue());
			if(n != null)
			  System.out.println(n.toString());
		}
	}
}
