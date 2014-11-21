/**
 * StudentFileReader.java
 * This class is used to read in a file containing the list of
 * student name and numbers, and produces a workable collection
 * of Student objects.
 * 
 * @author pmsc73
 */


package utility;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentFileReader implements StudentReader {
	
	public Collection<Student> getList(String path) {
		/**
		 * Only method that actually gets used in the class. Is full
		 * class functionality.
		 * @param path: path to file with list in it
		 * @return studentList 
		 */
		Collection<Student> studentList = new ArrayList<Student>();
		try { // needs to be in try block because Scanner throws FileNotFound
			
			// Scanner for entire file
			Scanner fsc = new Scanner(new File(System.getProperty("user.dir")+"/src/test/"+path)); 
			while(fsc.hasNextLine()) {
				// current line scanner
				Scanner line = new Scanner(fsc.nextLine()); 
				
				// name and number separated by commas
				line.useDelimiter(","); 
				String name;
				String number;
				double gpa;
				while(line.hasNext()) {
					name = line.next();
					if(line.hasNext()) {
						number = line.next();
						if(line.hasNextDouble()) {
							gpa = line.nextDouble();
							studentList.add(new Student(name,number,gpa));
						}
					}
					
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
