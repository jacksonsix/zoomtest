package fsm;

import graph.Path;

public class Transit extends Path{
	String evt;

	public Transit() {}
	
	public Transit(String name) {
		super(name);
		
	}
	public String getEvt() {
		return evt;
	}
	public void setEvt(String evt) {
		this.evt = evt;
	}
	
	public void trigger() {
		System.out.println("before: " + this.getFrom().getName());
		System.out.println("trigger on : " + this.evt);
		System.out.println("after: " + this.getTo().getName());
	}
	

}