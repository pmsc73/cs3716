package group;

import java.util.ArrayList;
import java.util.Collection;

import Strategy.BasicDistributionStrategy;

import questionnaire.QuestionnaireManager;
import utility.Group;
import utility.Student;

/****
 * Class to represent a set of project groups. Holds data pertaining to the specified class of students and the project.
 * @author Emily
 *
 ****/
public class GroupManager {
	
	private Collection<Group> groups;
	private Collection<Student> unassignedStudents;
	private PreferenceManager preferences;
	private QuestionnaireManager questionnaire;
	
	public GroupManager(Collection<Student> allStudents){
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
	/****
	 * Remove a student from a group. Returns false if the student wasn't in the group to begin with.s
	 ***/
	public boolean removeStudent(Student s, Group g){
		boolean b=g.remove(s);
		if(b) unassignedStudents.add(s);
		return b;
	}
	/****
	 * Add a student to a group. This method will add a student to a full group, so be careful with it.
	 ****/
	public void addStudent(Student s, Group g){
		g.add(s);
		unassignedStudents.remove(s);
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

	/****
	 * @return The collection of groups for the project group
	 ****/
	public Collection<Group> getGroups() {
		return groups;
	}

	/****
	 * @return All of the students which don't currently belong to a group.
	 ****/
	public Collection<Student> getUnaddedStudents() {
		return unassignedStudents;
	}
}
