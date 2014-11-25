/**
 * 
 */
package test;

import group.Preference;
import group.PreferenceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import utility.Student;
import utility.StudentFileReader;

/****
 * A test class for the PreferenceManager class
 * @author Emily
 *
 *****/
public class PreferenceManagerTest {
	
	public static void main(String[] args){
		StudentFileReader r = new StudentFileReader();
		PreferenceManager manager=new PreferenceManager();
		ArrayList<Student> students=(ArrayList<Student>)r.getList("cs3716.dat");
		int size =students.size();
		Random rand = new Random();
		for (int i=0;i<3;i++){
			manager.specifyPreference(students.get(rand.nextInt(size)),students.get(rand.nextInt(size)), -1);
		}
		for(Preference p: manager.getInstructorDisallowed()){
			System.out.println(p.getSource()+" can't work with "+p.getTarget());
		}
		System.out.println("The sets of disallowed students are:");
		while(manager.hasDisallowedSets()){
			Collection<Student> disallowed = manager.getDisallowedSet();
		
			for(Student s: disallowed) System.out.print(s+ ", ");
			System.out.println("");
		}
		for (int i=0;i<3;i++){
			manager.specifyPreference(students.get(rand.nextInt(size)),students.get(rand.nextInt(size)), 1);
		}
		for(Preference p: manager.getInstructorPreferences()){
			System.out.println(p.getSource()+" must work with "+p.getTarget());
		}
		System.out.println("The sets of required students are:");
		while(manager.hasRequiredSets()){
			Collection<Student> req = manager.getRequiredSet();
		
			for(Student s: req) System.out.print(s+ ", ");
			System.out.println("");
		}
		
	}

}
