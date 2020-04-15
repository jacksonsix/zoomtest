package graphattrib;

import java.util.List;
import java.util.Set;

import fsm.IAttribs;
import graph.INode;
import graph.IPath;

public class BasePath implements IPath , IAttribs {
    List<String>   attribNames;
    List<Object>   attribValues;
    BaseNode from;
    BaseNode to;
    String tag;
    String name;
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
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public INode getFrom() {
		
		return from;
	}

	@Override
	public void setFrom(INode from) {
		this.from = (BaseNode)from;
		
	}

	@Override
	public INode getTo() {
		
		return to;
	}

	@Override
	public void setTo(INode to) {
		this.to = (BaseNode)to;
		
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
