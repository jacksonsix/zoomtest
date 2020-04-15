package jmsMQ;

public class PublishSubscribeClient {
	public static void main(String[] args) {
		   
		commandloop();
		
    }
	
	private static void commandloop() {
		while(true) {
			String cmd = System.console().readLine();
			
			if(cmd.equalsIgnoreCase("stop")) {
				break;
			}
			dispatch(cmd);
		}

	}
	
	private static void dispatch(String cmd) {
		String[] cmsg = cmd.split(" ");
		String cm = cmsg[0];
		
		switch(cm) {
		case "send":
			String data = cmd.substring(cmd.indexOf("send")+5);
			send(data);
			break;
		case "recv":
			recv();
			break;
		default:
			break;
		}
	}
	
	private static void send(String msg) {
		LocalProducer prod = new LocalProducer(true);
		prod.setdestname("Topic1");
		prod.setMessage(msg);
		Thread t = new Thread(prod);
		t.start();
		System.out.println("send func");
	}
	
	private static void recv() {
		
		LocalConsumer consum = new LocalConsumer(true);
		consum.setDestname("Topic1");
		Thread t = new Thread(consum);
		t.setName("");
		t.start();
		System.out.println("recv func");
	}
	
}
