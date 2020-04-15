package fsm;

import graph.Node;

public class State extends Node{
	boolean isAccept;
	
	public State() {}
	public State(String name) {
		super(name);
		isAccept = false;
	}
	
	public State setAccept(boolean s) {
		this.isAccept= s;
		return this;
	}
	
}