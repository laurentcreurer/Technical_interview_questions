
/*
 * This class incrementing an integer in thread-safe using synchronized method
 */
public class ThreadSafe1 extends Thread{

	private int count;
	  
	public synchronized int getCountSynchronized(){
		return count++;
	}
	  
}
