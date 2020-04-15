package graph;

public interface IPath {

	String getName();

	void setName(String name);

	INode getFrom();

	void setFrom(INode from);

	INode getTo();

	void setTo(INode to);
	
	String getTag();
	
	void setTag(String tag);
}
