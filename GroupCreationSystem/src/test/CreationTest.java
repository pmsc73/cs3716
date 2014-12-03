/**
 * 
 */
package test;

import group.GroupManager;
import group.PreferenceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import utility.Controller;
import utility.Group;
import utility.Student;

/**
 * @author Emily
 *
 */
public class CreationTest {
	public static void main(String[] args){
		/*System.out.println("Creation Test with group size 3, negative preferences only\n____________________________________");
		negativePreferenceCreation(3);
		System.out.println("Creation Test with group size 4, negative preferences only\n____________________________________");
		negativePreferenceCreation(4);
		System.out.println("Creation Test with group size 5, negative preferences only\n____________________________________");
		negativePreferenceCreation(5);
		System.out.println("Creation Test with group size 2, postive preferences only\n____________________________________");
		positivePreferenceCreation(2);
		System.out.println("Creation Test with group size 4, postive preferences only\n____________________________________");
		positivePreferenceCreation(4);
		System.out.println("Creation Test with group size 5, postive preferences only\n____________________________________");
		positivePreferenceCreation(5);
		System.out.println("Creation Test with group size 4, random preferences\n____________________________________");
		mixedPreferenceCreation(4);*/
		for(int i=1;i<=17;i++){
			testMemberDivision(17,i);
		}
		for(int i=1;i<14;i++){
			testMemberDivision(14,i);
		}
	}
	/****
	 * Method to test that students are properly added to groups when a variety of preferences have been specified
	 * 
	 ****/
	private static void mixedPreferenceCreation(int groupSize) {
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber("cs3716");
		control.finalizeParameters();
		ArrayList<Student> students =(ArrayList<Student>)control.getAllStudents();
		Random r= new Random();
		int size =control.getAllStudents().size();
		for (int i=0;i<groupSize*2;i++){
			Student s1=students.get(r.nextInt(size));
			Student s2=students.get(r.nextInt(size));
			if(i%2!=0){
				System.out.println(s1+" cant work with "+s2);
				control.addPreference(s1,s2, false);
			}
			else{
				System.out.println(s1+" must work with "+ s2);
				control.addPreference(s1, s2, true);
			}
		}
		control.generateGroups();
		Collection<Group> groups = control.getGroups();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
		
	}
	/****
	 * Method to test that group creation works with preferences
	 *****/
	public static void negativePreferenceCreation(int groupSize){
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber("cs3716");
		control.finalizeParameters();
		ArrayList<Student> students =(ArrayList<Student>)control.getAllStudents();
		Random r= new Random();
		int size =control.getAllStudents().size();
		for (int i=0;i<3;i++){
			Student s1=students.get(r.nextInt(size));
			Student s2=students.get(r.nextInt(size));
			System.out.println(s1+" cant work with "+s2);
			control.addPreference(s1,s2, false);
		}
		control.generateGroups();
		Collection<Group> groups = control.getGroups();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
		
	}
	public static void positivePreferenceCreation(int groupSize){
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber("cs3716");
		control.finalizeParameters();
		ArrayList<Student> students =(ArrayList<Student>)control.getAllStudents();
		Random r= new Random();
		int size =control.getAllStudents().size();
		for (int i=0;i<groupSize+2;i++){
			Student s1=students.get(r.nextInt(size));
			Student s2=students.get(r.nextInt(size));
			System.out.println(s1+" must work with "+s2);
			control.addPreference(s1,s2, true);
		}
		control.generateGroups();
		Collection<Group> groups = control.getGroups();
		System.out.println("Generated these groups:\n______________________");
		for(Group g: groups){
			g.printGroup();
		
		}
	}
	/****
	 * Method to test that the groups are created with appropriate maximum student values
	 * @param total
	 * @param groupsize
	 ****/
	public static void testMemberDivision(int total, int groupsize){
		System.out.println("Dividing "+ total+" members into groups of " +groupsize);
		GroupManager x=new GroupManager(null,new PreferenceManager());
		ArrayList<Integer> ints;
		ints=(ArrayList<Integer>) x.calculateGroupSizes(total, groupsize);
		int total2=0;
		for(int i=0;i<ints.size();i++){
			System.out.println("Group "+i+" has "+ ints.get(i)+ " members!");
			total2+=ints.get(i);
		}
		System.out.println("Adds up to:"+total2);
	}
}
