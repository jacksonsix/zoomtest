package market;

public class Message implements IMessage{
	String name;
	String value;
	
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
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
