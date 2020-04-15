package graph;

import java.util.Set;

public interface INode {
	String getName();

	//Set<? extends IPath> getUp();
	Set<IPath> getUp();
	void addUp(IPath up);

	Set<IPath> getDown();

	void addDown(IPath down);
	
	String getTag();
	
	void setTag(String tag);
}
