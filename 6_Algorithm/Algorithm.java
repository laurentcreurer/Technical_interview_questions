/*
* Given a number between 0 and 999,999, write a method
* that writes that number in the english language.
* For example a call with the number 100282 would 
* return "One hundred thousand, two hundred eighty two".
*/
public class Algorithm {

	 
	static private final String ZERO = new String("zero");
    static private final String[] UNIT = new String[] {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
         "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
     };
    static private final String[] TENS = new String[] {
     	"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred", "thousand"
    };
    static private final String SEPARATOR = " ";
    static private final String COMMA = ",";
     
    /*
     * writes a number in the english language
     * @param value value to translate in english language (Must be < 999999)
     * @return value in english language
     */
	public static StringBuffer numberToWords(int value) {
		
		// Variable declaration
		StringBuffer stringValue = new StringBuffer();
		StringBuffer alphaNumber = new StringBuffer();
		stringValue.append(value);
		
		// Test Max and min value
		if (stringValue.length() > 6)
		{
			error();
			return null;
		}
		else if (value == 0)
		{
			return new StringBuffer(ZERO);
		}
		
		// Convert thousands
		if (stringValue.length() == 6)
		{
			alphaNumber.append(tensToWord(getDigits(stringValue, 5, 6)));
			alphaNumber.append(SEPARATOR);
			alphaNumber.append(TENS[10]);
			alphaNumber.append(SEPARATOR);
		}
		if (stringValue.length() > 3)
		{
			StringBuffer thousand = tensToWord(getDigits(stringValue, 3, 5));
			alphaNumber.append(thousand);
			if (thousand.length() > 0)
				alphaNumber.append(SEPARATOR);
			alphaNumber.append(TENS[11]);
		}
		
		// Insert comma
		if (getDigits(stringValue, 0, 3) != "000" && alphaNumber.length()>0)
		{
			alphaNumber.append(COMMA);
			alphaNumber.append(SEPARATOR);
		}
		
		// Convert hundreds
		if (stringValue.length() > 2)
		{
			StringBuffer hundred = tensToWord(getDigits(stringValue, 2, 3));
			if (hundred.length() > 0)
			{
				alphaNumber.append(hundred);
				alphaNumber.append(SEPARATOR);
				alphaNumber.append(TENS[10]);	
			}
		}
		StringBuffer unity = tensToWord(getDigits(stringValue, 0, 2));
		if (unity.length() > 0 && alphaNumber.length() > 0)
		{
			alphaNumber.append(SEPARATOR);
		}
		alphaNumber.append(unity);
		
		// Return result
		return alphaNumber;
	}
       
    /*
     * Get digits form a part of a StringBuffer
     * @param min Position Minimum of the digits
     * @param max Position Maximum of the digits
     * @return number in string
     */
	private static String getDigits(StringBuffer stringValue, int min, int max) {
		
		String reverseValue;
		stringValue.reverse();
								
		if (stringValue.length() >= max)
		{
			reverseValue = stringValue.substring(min,max);
		}
		else if (stringValue.length() >= min)
		{
			reverseValue = stringValue.substring(min,stringValue.length());
		}
		else
		{
			reverseValue = "0";
		}
		StringBuffer value = new StringBuffer(reverseValue);
		
		stringValue.reverse();
		return value.reverse().toString();
	}

	
    /*
     * writes an error message
     */
    private static void error() {
    	System.out.println("ERROR : Maximum integer value exceeded.");
    }
    
    
    /*
     * Translate number between 1 to 99 in english language
     * @param value value to translate in english language (Must be < 99)
     * @return value in english language
     */
	private static StringBuffer tensToWord(String value) {
		
		// Variable declaration
		StringBuffer words = new StringBuffer();
		int integerValue = Integer.parseInt(value);
		int unit = 0;
		int tens = 0;
		
		// Test Max value
		if (value.length() > 2)
		{
			error();
			return null;
		}
		
		// Split number
		if (value.length() > 1)
			tens = Integer.parseInt(value.substring(0, 1));
		if (value.length() == 2)
			unit = Integer.parseInt(value.substring(1, 2));
		
		// Convert value
		if (integerValue <= 19)
			words.append(UNIT[integerValue]);
		else
		{
			words.append(TENS[tens]);
			if (unit != 0)
				words.append(SEPARATOR);
			words.append(UNIT[unit]);
		}
		
		return words;
	}
}


