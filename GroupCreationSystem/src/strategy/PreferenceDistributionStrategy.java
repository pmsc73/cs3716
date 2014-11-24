
package strategy;

import java.util.ArrayList;
import java.util.Collection;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;
import group.Preference;
import group.PreferenceManager;

/****
 * This class implements the strategy that will be used to fill the groups when preferences are involved
 * @author Emily
 *
 ****/
public class PreferenceDistributionStrategy implements CreateGroupStrategy{
	private PreferenceManager manager;
	private Collection<Student> unAddedStudents;
	
	public PreferenceDistributionStrategy(Collection <Student> students,PreferenceManager preferences) {
		manager=preferences;
		unAddedStudents = students;
	}


	public void addRemainingStudents(Collection<Group> groups,Collection<Student> unassignedStudents) {
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


	@Override
	public void displayWarning() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillGroups(Collection<Group> groups) {
		//start by separating the banned students
		while(manager.hasDisallowedSets()){
			split(manager.getDisallowedSet(),groups);
				
		}
		//add all of the required groupings
		while(manager.hasRequiredSets()){
			groupAdd(manager.getAllowedSet(),groups);
		}
		
	}
	/****
	 * Method to split up students which aren't supposed to work together. 
	 * If students can't be split up, they'll stay in the unadded students list and get added later at random.
	 ****/
	private void split(Collection<Student> students,Collection<Group> groups){
		int size=students.size();
		
			Student s = students.iterator().next();
			int i=0;
			for(Group g: groups){
				if(!g.isFull()){
					g.add(s);
					students.remove(s);
					unAddedStudents.remove(s);
					i++;
					if(i<size){
						s=students.iterator().next();
						
					}
					else break;
				}
					
			}
		
	}

	/****
	 * Method to add students together. If only some of the students can be added, the unadded students are returned so that the system can try to place them
	 * together in a different group.
	 ****/
	private void groupAdd(Collection<Student> students, Collection<Group> groups){
		int requiredSpace = students.size();
		int bestFit=999999999;
		Group bestGroup= groups.iterator().next();
		for(Group g: groups){
			//find the difference between the group's current capacity and the number of students which need to be added.
			//This number needs to be minimized in order to put as many of these students together as possible.
			int diff = Math.abs(g.getGroupCapacity()-g.getCurrentSize()-requiredSpace);
			if (diff<bestFit){
				bestFit=diff;
				bestGroup=g;
			}
			
		}
		ArrayList<Student> unadded = new ArrayList<Student>();
		
		for(Student s: students){
			if(!bestGroup.isFull()){
				bestGroup.add(s);
			}
			else{
				unadded.add(s);
			}
		}
		groupAdd(unadded,groups);
		
	}
}
