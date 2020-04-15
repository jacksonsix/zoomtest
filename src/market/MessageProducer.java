package market;

public class MessageProducer implements IProducer, Runnable{
	IMessage msg;
	MessageContext env;
	
	
	
	@Override
	public void produce() {
				
		Thread t1 = new Thread(this);
		t1.start();
	}

     

	@Override
	public void run() {
		env.getTasks().add(msg);
		System.out.println("Message produced: " + msg.getName() + " " + msg.getValue());
		
	}



	public IMessage getMsg() {
		return msg;
	}



	public void setMsg(IMessage msg) {
		this.msg = msg;
	}



	public MessageContext getEnv() {
		return env;
	}



	public void setEnv(MessageContext env) {
		this.env = env;
	}
	

}
