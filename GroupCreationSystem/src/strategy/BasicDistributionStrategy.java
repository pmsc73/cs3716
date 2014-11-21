package strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;
/****
 * Basic strategy for dividing students into groups.
 * @author Emily
 *
 ***/
public class BasicDistributionStrategy implements CreateGroupStrategy{
	private Collection<Student> unAddedStudents;
	
	public BasicDistributionStrategy(Collection<Student> students){
	
		this.unAddedStudents=students;
	}
	
	/****
	 * Method used to fill the given groups with the given students
	 */
	public void fillGroups(Collection preferences, Collection<Group> groups) {

		ArrayList<Student>addedStudents = new ArrayList<Student>();
		for(Student s: unAddedStudents){
			for(Group g:groups){
				if(!g.isFull()){
					g.add(s);
					addedStudents.add(s);
					break;
				}
			}
		}
		for(Student s: addedStudents) unAddedStudents.remove(s);
	}
	/***
	 * This method would be used to add students which weren't added the first time, but it doesn't apply to this strategy. Not used.
	 */
	public void addRemainingStudents(Collection<Group> groups, Collection<Student> unassignedStudents){

		ArrayList<Student>addedStudents = new ArrayList<Student>();
		for(Student s: unAddedStudents){
			for(Group g:groups){
				if(!g.isFull()){
					g.add(s);
					addedStudents.add(s);
					break;
				}
			}
		}
		for(Student s: addedStudents) unAddedStudents.remove(s);

	}
	public void displayWarning(){
		
	}

}
