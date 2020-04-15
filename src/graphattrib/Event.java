package graphattrib;

public class Event implements IEvent{
	String name;
	String value;
	
	public Event() {}
	public Event(String name, String value) {
		this.name = name;
		this.value = value;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	

}
