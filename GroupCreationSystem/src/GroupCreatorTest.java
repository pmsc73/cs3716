

import java.util.ArrayList;
import java.util.Collection;

import utility.Group;
import utility.Student;
import utility.StudentFileReader;
import group.GroupCreationSystem;
import group.ProjectGroups;

/**
 * Test class to make sure that the system divides the students into reasonably sized groups
 * @author Emily
 *
 */
public class GroupCreatorTest {
	public static void main(String[] args){
		//testMemberDivision(29,5);
		testMemberDivision(28,5);
		testMemberDivision(26,5);
		testMemberDivision(6,2);
		testMemberDivision(40, 3);
		importTest("cs3716.dat");
		systemTest();
	}
	/****
	 * Method to test that the groups are created with appropriate maximum student values
	 * @param total
	 * @param groupsize
	 ****/
	public static void testMemberDivision(int total, int groupsize){
		System.out.println("Dividing "+ total+" members into groups of " +groupsize);
		ProjectGroups x=new ProjectGroups(null);
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
	public static void systemTest() {
		GroupCreationSystem sys = new GroupCreationSystem();
		sys.createGroups("3716", 2, false, 234029345, "testname");
		sys.addStudentsToGroups();
		Collection<Group> groups = sys.getGroups();
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
