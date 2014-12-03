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

/****
 * Test class to test that group distribution works properly
 * @author Emily
 *
 ****/
public class CreationTest {
	public static void main(String[] args){
			testMemberDivision(17,3);
			testMemberDivision(17,5);
			testMemberDivision(14,3);
			testMemberDivision(14,4);
			mixedPreferenceCreation("cs3716",4);
			mixedPreferenceCreation("cs4770",5);

	}
	/****
	 * Method to test that students are properly added to groups when a variety of preferences have been specified
	 * 
	 ****/
	private static void mixedPreferenceCreation(String courseName,int groupSize) {
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber(courseName);
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
	public static void negativePreferenceCreation(String courseName,int groupSize){
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber(courseName);
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
	public static void positivePreferenceCreation(String courseName,int groupSize){
		Controller control = new Controller();
		control.setGroupSize(groupSize);
		control.setCourseNumber(courseName);
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
