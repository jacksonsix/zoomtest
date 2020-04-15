package graph;

public class Path implements IPath{
	String name;
	INode from;
	INode to;
    String tag;
	
	public Path(String name) {
		this.name = name;
	}
	
	public Path() {}
	
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
		this.from = from;
	}
	@Override
	public INode getTo() {
		return to;
	}
	@Override
	public void setTo(INode to) {
		this.to = to;
	}

	@Override
	public String getTag() {
		
		return tag;
	}

	@Override
	public void setTag(String tag) {
		this.tag = tag;
	}

	
	@Override
	public String toString() {
		StringBuilder  sb = new StringBuilder();
		sb.append("Path name: " +name);
		sb.append(" from: ");
		
		sb.append(from.getName());
		
		sb.append(" to: ");
		
		sb.append(to.getName());
		
		return sb.toString();
	}
	
}