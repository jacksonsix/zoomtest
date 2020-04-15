package other;


import java.util.Random; 
import java.util.concurrent.Callable; 
import java.util.concurrent.FutureTask; 
  
class CallableExample implements Callable 
{ 
  volatile static int val = 0;
  
  public Object call() throws Exception 
  { 
    Random generator = new Random(); 
    Integer randomNumber = generator.nextInt(5); 
    //val++;
    Thread.sleep( 10  *randomNumber); 
  
    return val++; 
  } 
  
} 
  
public class CallableTest 
{ 
  public static void main(String[] args) throws Exception 
  { 
  
    // FutureTask is a concrete class that 
    // implements both Runnable and Future 
    FutureTask[] randomNumberTasks = new FutureTask[5]; 
  
    for (int i = 0; i < 5; i++) 
    { 
      Callable callable = new CallableExample(); 
  
      // Create the FutureTask with Callable 
      randomNumberTasks[i] = new FutureTask(callable); 
  
      // As it implements Runnable, create Thread 
      // with FutureTask 
      Thread t = new Thread(randomNumberTasks[i]); 
      t.start(); 
    } 
  
    for (int i = 0; i < 5; i++) 
    { 
      // As it implements Future, we can call get() 
      System.out.println(randomNumberTasks[i].get()); 
  
      // This method blocks till the result is obtained 
      // The get method can throw checked exceptions 
      // like when it is interrupted. This is the reason 
      // for adding the throws clause to main 
    } 
  } 
} 