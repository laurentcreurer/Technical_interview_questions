import java.io.File;

public class UnitTesting {

    /*
     * Calculates if there is enough space on a file system to write x bytes.
     * @param discName name of the file system
     * @param x number of bytes to write
     * @return true or false
     */
    public static Boolean freeSpace(String discName, long x) {
        System.out.println("-- UnitTesting --");
			
        File file = new File(discName);
        long usableSpace = file.getUsableSpace();
        if (x < usableSpace)
            return true;        		
        else
            return false;        		
    }
}
