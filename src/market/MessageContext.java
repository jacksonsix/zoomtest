package market;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageContext {
	volatile BlockingQueue<IMessage>  tasks;
	MessageProducer producer;
	MessageConsumer consumer;
	
	public MessageContext() {
		tasks = new LinkedBlockingDeque<>();
	}

	
	public void clearTask() throws InterruptedException {

		
		for(int i=10; i>0;i--) {
			Message msg = new Message();
			msg.setName("msg");
			msg.setValue(String.valueOf(i));
			MessageProducer p = new MessageProducer();
			p.setMsg(msg);
			p.setEnv(this);
			p.produce();
		}

		int count = 3;
		while(tasks.size() >0) {
			MessageConsumer c  = new MessageConsumer();
			c.setEnv(this);
			c.consume();
			count--;
			Thread.sleep(50);
		}
		Thread.currentThread().join();
	}
	
	public BlockingQueue<IMessage> getTasks() {
		return tasks;
	}

	public void setTasks(BlockingQueue<IMessage> tasks) {
		this.tasks = tasks;
	}

	public MessageProducer getProducer() {
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}
	
	
}
