package test;

import java.util.ArrayList;
import java.util.Collection;
import utility.Controller;
import utility.Group;
import utility.Student;
import group.GroupManager;
import group.PreferenceManager;

/****
 * Test class to make sure that the system divides the students into reasonably sized groups
 * @author Emily
 ****/
public class ControllerTest {
	public static void main(String[] args){

		System.out.println("Controller Test for groups of size 3 and CS3716 file\n____________________________________");
		controllerTest(3,"cs3716");
		System.out.println("Controller Test for groups of size 7 and CS3716 file\n____________________________________");
		controllerTest(7,"cs3716");
		System.out.println("Controller Test for groups of size 3 and CS4770 file\n____________________________________");
		controllerTest(3,"cs4770");
		System.out.println("Controller Test for groups of size 7 and CS4770 file\n____________________________________");
		controllerTest(7,"cs4770");
		System.out.println("Controller Reset Test\n____________________________________");
		controllerResetTest();
		
	}

	/****
	 * A test to make sure that you can regenerate the groups with a different size
	 ****/
	private static void controllerResetTest() {
		Controller control = new Controller();
		control.setGroupSize(3);
		control.setCourseNumber("cs3716");
		control.finalizeParameters();
		control.generateGroups();
		Collection<Group> groups = control.getGroups();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
		System.out.println("Changing group size to 6");
		control.setGroupSize(6);
		control.finalizeParameters();
		control.generateGroups();
		groups = control.getGroups();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
		
	}
	

	
	/*****
	 * Method to test that students are properly added to groups when using a controller object
	 ****/
	public static void controllerTest(int size, String courseNumber) {
		Controller control = new Controller();
		control.setGroupSize(size);
		control.setCourseNumber(courseNumber);
		control.finalizeParameters();
		control.generateGroups();
		Collection<Group> groups = control.getGroups();
		Collection<Student> students =control.getAllStudents();
		Object[] students2 =  students.toArray();
		Object[] groups2=  groups.toArray();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
	}

	

}
