/*
 * This class incrementing an integer in thread-safe using singleton Design Pattern
 */
public class ThreadSafe3
{	
	
	private int count;
	
	/*
	 * Private constructor
	 */
	private ThreadSafe3()
	{}
 
	/*
	 *  Holder 
	 */
	private static class ThreadSafe3Holder
	{		
		// Single instance not pre-initialized
		private final static ThreadSafe3 instance = new ThreadSafe3();
	}
 
	// Access point to the single instance of the singleton
	public static ThreadSafe3 getInstance()
	{
		return ThreadSafe3Holder.instance;
	}
	  
	public synchronized int getCountSingleton(){
		return count++;
	}
}