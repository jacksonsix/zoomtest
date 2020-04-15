package jmsMQ;
/*
 *  this is a local model. everybody go to the same postoffice. no postman.
 *  each has a own mailbox. each sent letter by drop to mailbox
 * */
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LocalConsumer implements Runnable,ExceptionListener{
	 ActiveMQConnectionFactory connectionFactory;
	 Session session;
	 Connection connection ;
	 MessageConsumer consumer;
	 TextMessage message ;
	 String destname;
	 boolean isTopic=false;
	 
	public LocalConsumer(){
		
	}
	
	public LocalConsumer(boolean isTopic) {
		this.isTopic = isTopic;
	}
	
	@Override
	public void run() {
		
		try {
			 connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

	         // Create a Connection
	         connection = connectionFactory.createConnection();
	         connection.setExceptionListener(this);
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
	         consumer = session.createConsumer(destination);
            // Wait for a message
	        while(true) {
	             Message message = consumer.receive();
                if(message == null) break;
	 	        if (message instanceof TextMessage) {
	 	            TextMessage textMessage = (TextMessage) message;
	 	            String text = textMessage.getText();
	 	            System.out.println(Thread.currentThread().getName() + " Received: " + text);
	 	        } else {
	 	            System.out.println(Thread.currentThread().getName() +" Received: " + message);
	 	        }
	         }


	        consumer.close();
	        session.close();
	        connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	
	@Override
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
	public TextMessage getMessage() {
		return message;
	}
	public void setMessage(TextMessage message) {
		this.message = message;
	}

	public String getDestname() {
		return destname;
	}

	public void setDestname(String destname) {
		this.destname = destname;
	}
	
	 
	 
}
