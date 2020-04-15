package fsm;

import java.util.List;

public interface IAttribs {
	List<String> getAttribNames();
	List<Object> getAtribValues();
	
	void addAttribName(String name);
	void addAttribValue(Object value);
	
}
