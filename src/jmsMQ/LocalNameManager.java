package jmsMQ;

/*
 * this is also a client to activemq, the user data is stored in activemq
 * 
 * */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LocalNameManager implements Runnable{
	Set<String> userList;
	public LocalNameManager() {
		userList = new HashSet<>();
	}
	
	public boolean queryUser(String user) {
		if(userList.contains(user)) return true;
		return false;
	}
	
	public void registerUser(String user) {
		userList.add(user);
	}
	
	@Override
	public void run() {
		
		
	}

}
