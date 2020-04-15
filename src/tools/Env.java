package tools;

import java.util.LinkedList;
import java.util.List;

public class Env {
	List<Object> objects;
	
	public Env() {
		objects = new LinkedList<>();
	}
	
	public void addObject(Object o) {
		objects.add(o);
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	
	
	
}
