package jmsMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * A JMS client example program that synchronously receives a message a Topic
 *  
 * @author Scott.Stark@jboss.org
 * @version $Revision: 1.9 $
 */
public class TopicRecvClient
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    
    public void setupPubSub()
        throws JMSException, NamingException
    {
        //InitialContext iniCtx = new InitialContext();
        //Object tmp = iniCtx.lookup("ConnectionFactory");
        //TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        ActiveMQConnectionFactory tcf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        conn = tcf.createTopicConnection();
       //(Topic) iniCtx.lookup("topic/testTopic");
        conn.setClientID("client1");
        session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        topic =  session.createTopic("topic1");
        conn.start();
    }
    
    public void recvSync()
        throws JMSException, NamingException
    {
        System.out.println("Begin recvSync");
        // Setup the pub/sub connection, session
        setupPubSub();

        // Wait upto 5 seconds for the message
        TopicSubscriber recv = session.createDurableSubscriber(topic, "SUB_NAME1");
        Message msg = recv.receive(5000);
        if (msg == null) {
            System.out.println("Timed out waiting for msg");
        } else {
            System.out.println("TopicSubscriber.recv, msgt="+msg);
        }
    }
    
    public void stop()
        throws JMSException
    {
        conn.stop();
        session.close();
        conn.close();
    }
    
    public static void main(String args[]) 
        throws Exception
    {
        System.out.println("Begin TopicRecvClient, now=" +
                           System.currentTimeMillis());
        TopicRecvClient client = new TopicRecvClient();
        client.recvSync();
        client.stop();
        System.out.println("End TopicRecvClient");
        System.exit(0);
    }
    
}
