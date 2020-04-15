package setOperation;

import java.util.LinkedList;
import java.util.List;

public class Record {
	List<String> cols;
	List<Object> values;
	static int id =0;
	int rid;
	
	public Record() {
		cols = new LinkedList<>();
		values = new LinkedList<>();
		rid = id++;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public int getId() {
		return this.rid;
	}
	
	public Record addCol(String name, Object value) {
		cols.add(name);
		values.add(value);
		return this;
	}

	public List<String> getCols() {
		return cols;
	}

	public void setCols(List<String> cols) {
		this.cols = cols;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
	

}
