package utility;

import java.util.ArrayList;
import java.util.Collection;

public class Registrar {

	public static Collection<Student> getStudents(String string) {
		// TODO Filled in for testing purposes, this isn't what this method will do later
		
		StudentFileReader reader = new StudentFileReader();
		return reader.getList(string+".dat");
		//students.add(new Student("Bob Smith", "1"));
		//students.add(new Student("Bib Smoth", "2"));
		//students.add(new Student("Charles Buckworth", "3"));
		//students.add(new Student("Gnarles Barkley", "4"));
		//students.add(new Student("Not Registered", "5"));
		//students.add(n
	}

	public static void getSchoolSchedule(Student s) {
		// TODO Auto-generated method stub
		
	}

}
