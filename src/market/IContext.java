package market;

import java.util.concurrent.BlockingQueue;

public interface IContext {
	
	BlockingQueue<IMessage> getTasks();
}
