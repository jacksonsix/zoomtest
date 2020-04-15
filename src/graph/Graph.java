package graph;

import java.util.LinkedList;
import java.util.List;

import fsm.IAttribs;


public class Graph implements IGraph{

	List<INode> setup_nodes ;
	List<IPath> setup_paths ;
	
	public Graph() {
		 setup_nodes = new LinkedList<INode>();
		 setup_paths = new LinkedList<IPath>();
	}
	
	public Graph(List<INode> nodes, List<IPath> paths) {
		this.setup_nodes = nodes;
		this.setup_paths = paths;
	}
	
	public Graph setup() {
		// don't set path in node init. do it here
		for(IPath p: setup_paths) {
			INode from = p.getFrom();
			INode to = p.getTo();
			from.addDown(p);
			to.addUp(p);
		}
		return this;
	}
	

	public void printSetup() {
		System.out.println("setup: nodes ");
		for(INode s : setup_nodes) {
			System.out.println(s.toString());
		}
		System.out.println("setup : paths  ");
		for(IPath s : setup_paths) {
			System.out.println(s.toString());
		}
		
	}
	
	public Graph addNode(INode node) {
		setup_nodes.add(node);
		return this;
	}
	
	public Graph addPath(IPath path) {
		setup_paths.add(path);
		return this;
	}
	
	public Graph delNode(INode node) {
		setup_nodes.remove(node);
		return this;
	}
	
	public Graph delPath(IPath path) {
		setup_paths.remove(path);
		return this;
	}
	
	
	public List<INode> getSetup_nodes() {
		return setup_nodes;
	}

	public void setSetup_nodes(List<INode> setup_nodes) {
		this.setup_nodes = setup_nodes;
	}

	public List<IPath> getSetup_paths() {
		return setup_paths;
	}

	public void setSetup_paths(List<IPath> setup_paths) {
		this.setup_paths = setup_paths;
	}
	
	@Override
	public INode getNodeByName(String name) {
		for(INode s: setup_nodes) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	@Override
	public  IPath getPathByName(String name) {
		for(IPath s: setup_paths) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
}
