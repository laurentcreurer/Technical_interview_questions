import java.util.concurrent.atomic.AtomicInteger;

/*
 * This class incrementing an integer in thread-safe using atomic values
 */
public class ThreadSafe2 extends Thread{

	AtomicInteger atomicCount = new AtomicInteger( 0 );
	  
	public int getCountAtomically(){
		return atomicCount.incrementAndGet();
	}
}
