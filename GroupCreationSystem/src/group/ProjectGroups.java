package group;

import java.util.ArrayList;
import java.util.Collection;

import questionnaire.QuestionnaireManager;
import test.BasicDistributionStrategy;
import utility.Group;
import utility.Student;

/****
 * Class to represent a set of project groups
 * @author Emily
 *
 ****/
public class ProjectGroups {
	
	private Collection<Group> groups;
	private Collection<Student> unassignedStudents;
	private PreferenceManager preferences;
	private QuestionnaireManager questionnaire;
	
	public ProjectGroups(Collection<Student> allStudents){
		preferences=new PreferenceManager();
		unassignedStudents=allStudents;
	}
	
	/****
	 * Creates a set of empty groups for students to be placed in later
	 * @param numStudents Number of students to be divided
	 * @param maxCap maximum capacity for any group
	 * @return
	 ****/
	public Collection<Group> createEmptyGroups(int numStudents, int maxCap){
			Collection<Integer> groupSizes=calculateGroupSizes(numStudents,maxCap);
			groups=new ArrayList<Group>();
			for(Integer i: groupSizes){
				groups.add(new Group(i));
			}

			return groups;
			
	}
	/****
	 * Generates a list of the maximum capacities for each group.
	 * @param numStudents Number of students to be divided
	 * @param maxCap Maximum capacity for any group
	 * 
	 ****/
	public Collection<Integer> calculateGroupSizes(int numStudents, int maxCap){
		int numGroups= numStudents/maxCap;

		
		ArrayList<Integer> groupSizes= new ArrayList<Integer>();
		if(numStudents%maxCap==0){
			for(int i=0;i<numGroups;i++){
				groupSizes.add(maxCap);
			}
		}
		else{
			int numSmallGroups= maxCap-(numStudents%maxCap);
			for(int i=0;i<numGroups+1;i++){
					groupSizes.add(maxCap);
			}
			for(int i=numGroups;i>numGroups-numSmallGroups;i--){
				groupSizes.set(i, maxCap-1);
			}
			
		}
		return groupSizes;
	}
	
	//not really used (yet?)
	public void addRemainingStudents(Collection<Student> students){
		CreateGroupStrategy strategy = new BasicDistributionStrategy(unassignedStudents);//testing purposes only!
		strategy.addRemainingStudents(groups, unassignedStudents);
	}
	/****
	 * Method to fill the groups
	 * 
	 ****/
	public void fillGroups(boolean Skill){
		CreateGroupStrategy strategy = new BasicDistributionStrategy(unassignedStudents);//testing purposes only!
		strategy.fillGroups(preferences.getInstructorPreferences(), groups);
	}

	public void setSkillBased() {
		questionnaire= new QuestionnaireManager(); // fixed
	}

	/**
	 * @return The collection of groups for the project group
	 */
	public Collection<Group> getGroups() {
		return groups;
	}
}
