package market;

public class MessageConsumer implements IConsumer, Runnable{
	IMessage msg;
	MessageContext env;
	
	
	@Override
	public void consume() {
		
		Thread t1 = new Thread(this);
		t1.start();
		
		
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
	
	@Override
	public void run() {
		System.out.println("consumer comming");
		msg = env.getTasks().poll();
		if(msg != null)
		  System.out.println("Message consumed: " + msg.getName() +" " + msg.getValue());
		
		System.out.println("consumer bye");
	}
	
	
	
	
}
