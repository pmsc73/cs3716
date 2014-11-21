
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

	/* (non-Javadoc)
	 * @see group.CreateGroupStrategy#addRemainingStudents(java.util.Collection, java.util.Collection)
	 */
	@Override
	public void addRemainingStudents(Collection<Group> groups,Collection<Student> unassignedStudents) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see group.CreateGroupStrategy#displayWarning()
	 */
	@Override
	public void displayWarning() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see group.CreateGroupStrategy#fillGroups(java.util.Collection, java.util.Collection)
	 */
	@Override
	public void fillGroups(Collection<Group> groups) {
		/*ArrayList<Student>addedStudents = new ArrayList<Student>();
		for(Student s: unAddedStudents){
			for(Group g:groups){
				if(!g.isFull()){
					g.add(s);
					addedStudents.add(s);
					break;
				}
			}
		}
		for(Student s: addedStudents) unAddedStudents.remove(s);*/
		
		
	}

}
