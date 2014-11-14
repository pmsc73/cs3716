/**
 * StudentRead.java
 * This class is used to read in a file containing the list of
 * student name and numbers, and produces a workable collection
 * of Student objects.
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

public class StudentReader { 
	public static Collection<Student> getList(File list) {
		/**
		 * Only method that actually gets used in the class. Is full
		 * class functionality.
		 * @param list : File containing student info
		 * @return studentList 
		 */
		Collection<Student> studentList = new ArrayList<Student>();
		try { // needs to be in try block because Scanner throws FileNotFound
			
			// Scanner for entire file
			Scanner fsc = new Scanner(list); 
			while(fsc.hasNextLine()) {
				// current line scanner
				Scanner line = new Scanner(fsc.nextLine()); 
				
				// name and number separated by commas
				line.useDelimiter(","); 
				String name;
				String number;
				while (!((name=line.next())==null) && !((number=line.next())==null)) {
					// this ensures all students will have non-null fields
					studentList.add(new Student(name,number));
				}
				line.close(); // eclipse suggest I close my scanners
			}
			fsc.close(); // eclipse suggests I close my scanners
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return studentList;
	}
}
