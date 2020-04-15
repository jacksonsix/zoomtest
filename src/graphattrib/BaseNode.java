package graphattrib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fsm.IAttribs;
import graph.INode;
import graph.IPath;

public class BaseNode implements INode, IAttribs {
    List<String>   attribNames;
    List<Object>   attribValues;
    Set<BasePath>  ups;
    Set<BasePath>  downs;
    String tag;
    String name;
    
    public BaseNode() {
    	attribNames = new LinkedList<>();
    	attribValues= new LinkedList<>();
    	ups = new HashSet<>();
    	downs = new HashSet<>();
    	
    }
	@Override
	public List<String> getAttribNames() {
		
		return attribNames;
	}

	@Override
	public List<Object> getAtribValues() {
		
		return attribValues;
	}

	@Override
	public void addAttribName(String name) {
		attribNames.add(name);
		
	}

	@Override
	public void addAttribValue(Object value) {
		attribValues.add(value);
		
	}

	@Override
	public String getName() {
		
		return name;
	}



	@Override
	public void addUp(IPath up) {
		ups.add((BasePath)up);
		
	}

	@Override
	public void addDown(IPath down) {
		downs.add((BasePath)down);
	}
	@Override
	public Set<IPath> getUp() {
		return new HashSet<IPath>(ups);
	}
	@Override
	public Set<IPath> getDown() {
		return new HashSet<IPath>(downs);
	}
	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return tag;
	}
	@Override
	public void setTag(String tag) {
		this.tag = tag;
		
	}
	

}
