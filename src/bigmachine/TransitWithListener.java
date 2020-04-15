package bigmachine;

import java.util.LinkedList;
import java.util.List;

import fsm.Transit;
import graphattrib.IListener;

public class TransitWithListener extends Transit{
	List<IListener> beforelistners;
	List<IListener> afterlistners;
	
	public TransitWithListener() {
		beforelistners = new LinkedList<>();
		afterlistners = new LinkedList<>();
	}
	
	public TransitWithListener(String name) {
		super(name);
		beforelistners = new LinkedList<>();
		afterlistners = new LinkedList<>();
	}

	@Override
	public void trigger() {
		/*
		 * for(IListener l: beforelistners) { l.run(); } super.trigger();
		 * 
		 * for(IListener l: afterlistners) { l.run(); }
		 */
	}


	public TransitWithListener addBeforeListener(IListener b) {
		beforelistners.add(b);
		return this;
	}
	
	public TransitWithListener addAfterListener(IListener b) {
		afterlistners.add(b);
		return this;
	}
}
