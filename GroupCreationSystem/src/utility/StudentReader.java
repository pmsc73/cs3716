/**
 * StudentReader.java
 * Interface implemented by the StudentFileReader class
 * This will be helpful if there is another implementation
 * of reading from a list of students, such as database
 * 
 * @author pmsc73
 * Updated 11/14/2014
 */


package utility;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public interface StudentReader {
	public Collection<Student> getList(String path);
	// For now the getList method takes some String arg,
	// assuming the way we access a database, or whatever
	// can be implemented via a string
}
