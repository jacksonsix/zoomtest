package jmsMQ;


public class ProducerConsumerClient {
  
	static String nickname;
	public static void main(String[] args) {
	   
		commandloop();
		
    }
	
	private static void commandloop() {
		while(true) {
			String cmd = System.console().readLine();
			
			if(cmd.equalsIgnoreCase("stop")) {
				System.out.println("exit program");
				System.exit(0);
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
		case "name":
			nickname = cmd.substring(cmd.indexOf("name")+5);
		default:
			break;
		}
	}
	
	private static void send(String msg) {
		String[] mmsg = msg.split(" ");
		String recname =mmsg[0];
		String data = msg.substring(msg.indexOf(recname)+recname.length() +1);
		LocalProducer prod = new LocalProducer(false);
		prod.setdestname( recname+ "_in");
		prod.setMessage(data);
		Thread t = new Thread(prod);
		t.start();
		System.out.println("send func");
	}
	
	private static void recv() {
		
	    LocalConsumer consum = new LocalConsumer(false);
		consum.setDestname(nickname + "_in");
		Thread t = new Thread(consum);
		t.setName("");
		t.start();
		System.out.println("recv func");
	}
	
	
   
}
