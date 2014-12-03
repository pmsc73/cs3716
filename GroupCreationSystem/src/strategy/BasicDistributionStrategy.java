package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.Random;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;

/****
 * Basic strategy for dividing students into groups. Sorts based on GPA.
 * 
 * @author Emily
 *
 ***/
public class BasicDistributionStrategy implements CreateGroupStrategy {
	private Collection<Student> unAddedStudents;

	public BasicDistributionStrategy(Collection<Student> students) {

		this.unAddedStudents = students;
	}

	/****
	 * Method used to fill the given groups with the given students
	 */
	public void fillGroups(Collection<Group> groups) {

		ArrayList<Student> addedStudents = new ArrayList<Student>();

		ArrayList<Student> sortedStudents = ((ArrayList<Student>) unAddedStudents);
		Collections.sort(sortedStudents);
		for(Student s : sortedStudents) {
			System.out.println(s.getName());
		}
		System.out.println(sortedStudents.size());
		
		for (int j = 0; j < sortedStudents.size(); j++) {
			System.out.print(j);
		}
		System.out.println();
		
		int i = groups.size();
		boolean forward = false;
		for (int j = sortedStudents.size()-1; j >= 0; j--) {
			Student s = sortedStudents.get(j);
			
			if(!(i <groups.size())) {
				forward = false;
				i--;
			}
			if(i<0) {
				forward = true;
				i++;
			}
			Group g = ((ArrayList<Group>)groups).get(i);
			System.out.println("adding "+s.getName()+" to "+g.getName()+" j="+j);
			if(!g.isFull()) {
				g.add(s);
				addedStudents.add(s);
			}
			i+=(forward)?1:-1;
		}
		for(Group g : groups) {
			double avgGpa = 0;
			for(Student s : g.getGroupMembers()) {
				avgGpa += s.getGPA();
			}
			avgGpa /= ((ArrayList<Student>)(g.getGroupMembers())).size();
			System.out.println(avgGpa);
		}
		for (Student s : addedStudents)
			unAddedStudents.remove(s);
	}
	
	/***
	 * This method would be used to add students which weren't added the first
	 * time, but it doesn't apply to this strategy. Not used.
	 */
	public void addRemainingStudents(Collection<Group> groups,
			Collection<Student> unassignedStudents) {

		ArrayList<Student> addedStudents = new ArrayList<Student>();
		for (Student s : unAddedStudents) {
			for (Group g : groups) {
				if (!g.isFull()) {
					g.add(s);
					addedStudents.add(s);
					break;
				}
			}
		}
		for (Student s : addedStudents)
			unAddedStudents.remove(s);

	}

	public void displayWarning() {

	}

}
