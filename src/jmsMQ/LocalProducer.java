package jmsMQ;
/*
 *  this is a local model. everybody go to the same postoffice. no postman.
 *  each has a own mailbox. each sent letter by drop to mailbox
 * */
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LocalProducer implements Runnable{
	 ActiveMQConnectionFactory connectionFactory;
	 Session session;
	 Connection connection ;
	 MessageProducer producer;
	 String destname;

	 String text ;
	 boolean isTopic = false;
	 
	public LocalProducer(){
		
	}
	public LocalProducer(boolean isTopic) {
		this.isTopic = isTopic;
	}
	
	public void send() {
		// TODO Auto-generated method stub
		  try {
			     connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
                 // System.out.println("producer start");
		         // Create a Connection
		         connection = connectionFactory.createConnection();
		         connection.start();

		         // Create a Session
		         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		         // Create the destination (Topic or Queue)
		        
		         Destination destination;
		         if(isTopic) {
		        	 destination = session.createTopic(destname);
		         }else {
		        	 destination = session.createQueue(destname);
		         }
		         
		         // Create a MessageProducer from the Session to the Topic or Queue
		         producer = session.createProducer(destination);
		         producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		         
				 
				 TextMessage message = session.createTextMessage(text);
				  producer.send(message);
		          // Clean up
		          session.close();
		          connection.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	public String getMessage() {
		return text;
	}
	public void setMessage(String text) {
		String ordertext = new SequenceMessage(text).getMsg(); 
		this.text = ordertext;
	}

	public String getdestname() {
		return destname;
	}

	public void setdestname(String queName) {
		this.destname = queName;
	}



	@Override
	public void run() {
		send();
	}
	

}


class SequenceMessage {
	   static int sequencenumber=0;
	   private int msgId;
	   private String msg;
	   
	   public SequenceMessage() {
		   this.msgId = sequencenumber++;
	   }
	   
	   public SequenceMessage(String msg) {
		   this.msg = msg;
		   this.msgId = sequencenumber++;
	   }
	   
	   public String getMsg() {
		   return  this.msgId +":" + this.msg;
	   }

	}