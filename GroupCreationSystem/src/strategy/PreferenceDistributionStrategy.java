
package strategy;

import java.util.Collection;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;

/****
 * This class implements the strategy that will be used to fill the groups when preferences are involved
 * @author Emily
 *
 ****/
public class PreferenceDistributionStrategy implements CreateGroupStrategy{

	/* (non-Javadoc)
	 * @see group.CreateGroupStrategy#addRemainingStudents(java.util.Collection, java.util.Collection)
	 */
	@Override
	public void addRemainingStudents(Collection<Group> groups,
			Collection<Student> unassignedStudents) {
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
	public void fillGroups(Collection preferences, Collection<Group> groups) {
		// TODO Auto-generated method stub
		
	}

}
