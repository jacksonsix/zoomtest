package market;

public class FileSortConsumer  implements IConsumer, Runnable{

	IMessage msg;
	IContext context;
	
	@Override
	public void run() {
		
		msg = context.getTasks().poll();
		if(msg != null) {
			//System.out.println("Message consumed: " + msg.getName() +" " + msg.getValue());
			String e = msg.getValue().toUpperCase();
			((FileSortContext)context).getResult().add(e);
		}
		  
	}

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

	public IContext getContext() {
		return context;
	}

	public void setContext(IContext context) {
		this.context = context;
	}
	
	

}
