import java.util.ArrayList;
import java.util.List;
/*
 * This class print the Fibonacci sequence.
 * This class use a Non recursive function.
 * The advantage of using a non-recursive is that requires less resources and its depth is limited by JVM.
 */
public class Fib {
	
	/*
	 * Length of the sequence
	 */
	static private final int NUMBER = 10;
	
	/*
	 * Non recursive function that prints the Fibonacci sequence.
	 */
	public static void fib() {
	
		System.out.println("-- Fib --");
		// Variable declaration
		List<Integer> fib = new ArrayList<Integer>();
		
		// Initialisation
		fib.add(0);
		System.out.println("0");	
		fib.add(1);
		System.out.println("1");	
		
		// Print the Fibonacci sequence.
		for (int i = 2; i < NUMBER; i++) {
			fib.add(fib.get(i-1) + fib.get(i-2));
			System.out.println(fib.get(i));	
		}
	}
}



