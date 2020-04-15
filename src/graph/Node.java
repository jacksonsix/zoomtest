package graph;

import java.util.HashSet;
import java.util.Set;

public class Node implements INode {
	String name;
	Set<IPath> up;
	Set<IPath> down;
	String tag;
	
	public Node() {}
	public Node(String name) {
		this.name = name;
		up = new HashSet<IPath>();
		down = new HashSet<IPath>();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Set<IPath> getUp() {
		return up;
	}
	@Override
	public void addUp(IPath up) {
		this.up.add(up);
	}
	@Override
	public Set<IPath> getDown() {
		return down;
	}
	@Override
	public void addDown(IPath down) {
		this.down.add(down);
	}
	@Override
	public String toString() {
		StringBuilder  sb = new StringBuilder();
		sb.append("Node name: " +name);
		sb.append(" ups: ");
		for(IPath p : up) {
			sb.append(p.getName()+ ", ");
		} 
		sb.append(" downs: ");
		for(IPath p : down) {
			sb.append(p.getName() +", ");
		} 
		return sb.toString();
	}
	@Override
	public String getTag() {
		
		return tag;
	}
	@Override
	public void setTag(String tag) {
		this.tag = tag;
		
	}
	
	
	
}
