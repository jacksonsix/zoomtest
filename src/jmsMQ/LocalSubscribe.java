package jmsMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LocalSubscribe implements Runnable,ExceptionListener{
	 ActiveMQConnectionFactory connectionFactory;
	 Session session;
	 Connection connection ;
	 TextMessage message ;
	 String destname;
	 TopicSubscriber subscriber;
	 
	@Override
	public void onException(JMSException arg0) {
		// TODO Auto-generated method stub
		
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
	         destination = session.createTopic(destname);
	         
	         // Create a MessageProducer from the Session to the Topic or Queue
	         //consumer = session.createConsumer(destination);
	          subscriber = session.createSubscriber(destination);
	          //TopicSubscriber subscriber = session.createDurableSubscriber(destination, "D_SUB_000001");
           // Wait for a message
	        while(true) {
	             Message message = subscriber.receive();
               if(message == null) break;
	 	        if (message instanceof TextMessage) {
	 	            TextMessage textMessage = (TextMessage) message;
	 	            String text = textMessage.getText();
	 	            System.out.println(Thread.currentThread().getName() + " Received: " + text);
	 	        } else {
	 	            System.out.println(Thread.currentThread().getName() +" Received: " + message);
	 	        }
	         }


	        subscriber.close();
	        session.close();
	        connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	 
	 
}
