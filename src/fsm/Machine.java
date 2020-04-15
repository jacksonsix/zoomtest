package fsm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graph.INode;
import graph.IPath;

public class Machine{
	List<INode> states ;
	List<IPath> paths ;
	List<String> evts;
	
	List<INode> setup_states ;
	List<IPath> setup_paths ;
	
	State currentState = null;
	
	public Machine() {
		 states = new LinkedList<INode>();
		 paths = new LinkedList<IPath>();
		 evts = new LinkedList<String>();
		
		 setup_states = new LinkedList<INode>();
		 setup_paths = new LinkedList<IPath>();
		
		 currentState = null;
	}
	
	public State getCurrentState() {
		return this.currentState;
	}
	
	public Machine setCurrentState(State st) {
		this.currentState = st;
		return this;
	}
	
	public  State trigger(String evt) {
		evts.add(evt);
		Set<IPath>tos = this.currentState.getDown();
		boolean foundPath = false;
		
		for(IPath tp : tos) {
			Transit t =(Transit)tp;
			if(evt.equalsIgnoreCase(t.getEvt())) {
				paths.add(t);
				t.trigger();
				INode next = t.getTo();
				this.currentState = (State)next;
				foundPath=true;
			}
		}
		if(foundPath) {
			//System.out.println(" move forward");
			//System.out.println("after: " + this.currentState.getName());
		}else {
			System.out.println(" no path forward");
		}
		
		return currentState;
	}
	
	public Machine addState(State st) {
		setup_states.add(st);
		return this;
	}
	
	public Machine addTansit(Transit trans) {
		setup_paths.add(trans);
		return this;
	}
	
	public Machine setup() {
		// don't set path in node init. do it here
		for(IPath p: setup_paths) {
			INode from = p.getFrom();
			INode to = p.getTo();
			from.addDown(p);
			to.addUp(p);
		}
		return this;
	}
	
	public Machine mergeMachineOnInOut(Machine sub,Transit in, Transit out) {
		
		return this;
	}
	
	public void printSetup() {
		System.out.println("setup: nodes ");
		for(INode s : setup_states) {
			System.out.println(s.toString());
		}
		System.out.println("setup : paths  ");
		for(IPath s : setup_paths) {
			System.out.println(s.toString());
		}
		
	}
	
	public Machine readFromFile(String filename)  {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int state = 0;
		    
		    while ((line = br.readLine()) != null) {
		      if(state == 0) {
		    	  
		    	  if(line.equalsIgnoreCase("Path")) {
		    		  state = 1;
		    		  continue;
		    	  }else {
		    		  State s = new State(line);
		    		  setup_states.add(s);
		    	  }
		    	  
		      }else {
		    	  if(line.isEmpty()) continue;
		    	  String[] segs = line.split(",");
		    	  String name = segs[0];
		    	  String from = segs[1];
		    	  String to = segs[2];
		    	  String evt = segs[3];
		    	  
		    	  Transit t = new Transit(name);
		    	  t.setFrom(getStateByName(from));
		    	  t.setTo(getStateByName(to));
		    	  t.setEvt(evt);
		    	  
		    	  setup_paths.add(t);
		      }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setup();
		this.setCurrentState((State)setup_states.get(0));
		
		return this;
	}
	
	private INode getStateByName(String name) {
		for(INode s: setup_states) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	private IPath getTransitByName(String name) {
		for(IPath s: setup_paths) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
}
