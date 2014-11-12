package group;

import java.util.ArrayList;
import java.util.Collection;

import questionnaire.QuestionnaireManager;
import test.BasicDistributionStrategy;
import utility.Group;
import utility.Student;

public class GroupCreator {
	
	private Collection<Group> groups;
	private Collection<Student> unassignedStudents;
	private PreferenceManager preferences;
	private QuestionnaireManager questionnaire;
	public Collection<Group> createEmptyGroups(int numStudents, int maxCap){
		
			Collection<Integer> groupSizes=calculateGroupSizes(numStudents,maxCap);
			groups=new ArrayList<Group>();
			for(Integer i: groupSizes){
				groups.add(new Group(i));
			}

			return groups;
			
	}
	
	public Collection<Integer> calculateGroupSizes(int numStudents, int maxCap){
		int numGroups= numStudents/maxCap;
		int numSmallGroups= maxCap-(numStudents%maxCap);
		ArrayList<Integer> groupSizes= new ArrayList<Integer>();
		for(int i=0;i<numGroups+1;i++){
				groupSizes.add(maxCap);
		}
		for(int i=numGroups;i>numGroups-numSmallGroups;i--){
			groupSizes.set(i, maxCap-1);
		}
		return groupSizes;
	}
	
	public void addRemainingStudents(Collection<Student> students){
		CreateGroupStrategy strategy = new BasicDistributionStrategy(unassignedStudents);//testing purposes only!
		strategy.addRemainingStudents(groups, unassignedStudents);
	}
	
	public void fillGroups(boolean Skill){
		CreateGroupStrategy strategy = new BasicDistributionStrategy(unassignedStudents);//testing purposes only!
		strategy.fillGroups(preferences.getInstructorPreferences(), groups);
	}

	public void setSkillBased() {
		QuestionnaireManager questionnaire= new QuestionnaireManager();
	}
}
