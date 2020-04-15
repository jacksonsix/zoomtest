package market;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileSortContext implements IContext {

	volatile BlockingQueue<IMessage>  tasks;
	volatile List<String> result;
	
	public FileSortContext() {
		tasks = new LinkedBlockingQueue<>();
		result = new LinkedList<>();
	}
	
	
	public void clearTask() throws InterruptedException {
		try {
		      File myObj = new File("C:\\Users\\jliang\\Downloads\\a.json");
		      Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		        Message msg = new Message();
		        msg.setName("line");
		        msg.setValue(data);
		        FileSortProducer p = new FileSortProducer();
		        p.setMsg(msg);
		        p.setEnv(this);
		        p.produce();
		        
		        
		       
		      }
		      myReader.close();
		      
		      while(true) {
		        	FileSortConsumer c  = new FileSortConsumer();
					c.setContext(this);
					c.consume();
					Thread.sleep(50);
					if(tasks.size() == 0) break;
				}
		      int i=0;
		      for(String s: result) {
		    	  i++;
		    	  System.out.println(s + String.valueOf(i));
		      }
		      
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		
	}


	public BlockingQueue<IMessage> getTasks() {
		return tasks;
	}


	public void setTasks(BlockingQueue<IMessage> tasks) {
		this.tasks = tasks;
	}


	public List<String> getResult() {
		return result;
	}


	public void setResult(List<String> result) {
		this.result = result;
	}
	
	
}
