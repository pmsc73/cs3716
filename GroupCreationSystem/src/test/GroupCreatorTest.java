package test;

import java.util.ArrayList;
import java.util.Collection;

import utility.Group;
import utility.Student;
import utility.StudentFileReader;
import group.Controller;
import group.GroupCreationSystem;
import group.GroupManager;

/**
 * Test class to make sure that the system divides the students into reasonably sized groups
 * @author Emily
 *
 */
public class GroupCreatorTest {
	public static void main(String[] args){
		//testMemberDivision(29,5);
		//testMemberDivision(28,5);
		//testMemberDivision(26,5);
		//testMemberDivision(6,2);
		//testMemberDivision(40, 3);
		//importTest("cs3716.dat");
		controllerTest();
	}
	/****
	 * Method to test that the groups are created with appropriate maximum student values
	 * @param total
	 * @param groupsize
	 ****/
	public static void testMemberDivision(int total, int groupsize){
		System.out.println("Dividing "+ total+" members into groups of " +groupsize);
		GroupManager x=new GroupManager(null);
		ArrayList<Integer> ints;
		ints=(ArrayList<Integer>) x.calculateGroupSizes(total, groupsize);
		int total2=0;
		for(int i=0;i<ints.size();i++){
			System.out.println("Group "+i+" has "+ ints.get(i)+ " members!");
			total2+=ints.get(i);
		}
		System.out.println("Adds up to:"+total2);
	}
	/*****
	 * Method to test that students are properly added to groups
	 ****/
	public static void controllerTest() {
		Controller control = new Controller();
		control.setGroupSize(3);
		control.setCourseNumber("cs3716");
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
		System.out.println("Removing "+ students2[0]+" and "+students2[4]+"\n_____________________");
		control.removeStudent((Student)students2[4], (Group)groups2[1]);
		control.removeStudent((Student)students2[0], (Group)groups2[0]);
		System.out.println("New groups are:\n_____________________");
		for(Group g: groups){
			g.printGroup();
		
		}
		Collection<Student> groupless= control.getUnassignedStudents();
		System.out.println("Unassigned Students!:");
		for(Student s: groupless){
			System.out.println(s);
		}

		System.out.println("__________________");
		System.out.println("Readding unassigned students");
		control.generateGroups();
		System.out.println("New groups are:\n_____________________");
		for(Group g: groups){
			g.printGroup();
		
		}
	}
	public static void importTest(String path){
		StudentFileReader read = new StudentFileReader();
		Collection<Student> list = read.getList(path);
		for(Student s: list){
			System.out.println(s);
		}
		
	}
}
