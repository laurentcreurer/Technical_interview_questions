public class StwAndo {

	/*
	 * Length of the sequence
	 */
	static private final int NUMBER = 100;
	
	static private final String THREEMULTIPLE = "Stw";
	static private final String FIVEMULTIPLE = "Ando";
	
	/*
	 * This methode prints the numbers from 1 to 100.
	 * But for multiples of three print “Stw” instead of the number 
	 * and for the multiples of five print “Ando”. For numbers which are
	 *  multiples of both three and five print “StwAndo”.
	 */
	public static void StwAndoFunction() {
		System.out.println("-- StwAndo --");
		StringBuffer out = new StringBuffer();
		
		 for (int i = 1; i <= NUMBER; i++) {
			 
			 // is multiples of three ?
			 if (i%3  == 0)
			 {
				 out.append(THREEMULTIPLE);
			 }
			 // is multiples of five ?
			 if (i%5  == 0)
			 {
				 out.append(FIVEMULTIPLE);
			 }
			 // is multiples of anything ?
			 if (out.length() == 0)
			 {
				 out.append(i);
			 }
			 // Print the result
			 System.out.println(out);

			 // Clean the buffer
			 out.setLength(0);
		}
	}

}