package market;

public class FileSortProducer implements IProducer, Runnable {

	IMessage msg;
	IContext env;
	
	@Override
	public void run() {

		
		env.getTasks().add(msg);
		//System.out.println("Message produced: " + msg.getName() + " " + msg.getValue());
	}

	@Override
	public void produce() {
		Thread t1 = new Thread(this);
		t1.start();
		
	}

	public IMessage getMsg() {
		return msg;
	}

	public void setMsg(IMessage msg) {
		this.msg = msg;
	}

	public IContext getEnv() {
		return env;
	}

	public void setEnv(IContext env) {
		this.env = env;
	}


	

}
