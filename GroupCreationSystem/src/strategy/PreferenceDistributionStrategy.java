
package strategy;

import java.util.ArrayList;
import java.util.Collection;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;
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
		//add all of the required groupings. Retry unadded students only once
		while(manager.hasRequiredSets()){
			Collection<Student>retry=groupAdd(manager.getRequiredSet(),groups);
			if(retry.size()>0) groupAdd(retry,groups);
		}
		
	}
	/****
	 * Method to split up students which aren't supposed to work together. 
	 * If students can't be split up, they'll stay in the unadded students list and get added later at random.
	 ****/
	private void split(Collection<Student> students,Collection<Group> groups){
		int size=students.size();
		
		for(int i=0;i<size;i++){
			int mostSpace=0; 
			Group bestGroup=groups.iterator().next();
			Student stu = students.iterator().next();
			//find the group to put the student in
			if(unAddedStudents.contains(stu)){
				for(Group g: groups){
					int space = g.getGroupCapacity()-g.getCurrentSize();
					if(space>mostSpace){
						mostSpace=space;
						bestGroup=g;
					}
				}
				bestGroup.add(stu);
				unAddedStudents.remove(stu);
			}

			students.remove(stu);
			
		}
		
		
	}

	/****
	 * Method to add students together. If only some of the students can be added, the unadded students are returned so that the system can try to place them
	 * together in a different group.
	 ****/
	private Collection<Student> groupAdd(Collection<Student> students, Collection<Group> groups){
		int requiredSpace = students.size();
		int bestFit=999999999;
		Group bestGroup=groups.iterator().next();
		boolean groupStarted=false;
		for(Group g: groups){
			for(Student s: students){
				if(g.getGroupMembers().contains(s)){
					students.remove(s);
					bestGroup=g;
					groupStarted=true;
					break;
				}
			}
			if(groupStarted)break;
		}
		if(!groupStarted){
			for(Group g: groups){
				//find the difference between the group's current capacity and the number of students which need to be added.
				//This number needs to be minimized in order to put as many of these students together as possible.
				int diff = g.getGroupCapacity()-g.getCurrentSize()-requiredSpace;
				if(diff==0){
					bestGroup=g;
					break;
				}
				else{
					if((diff>0&&bestFit<0)||(diff>0&&bestFit>0&&diff<bestFit)||(diff<0&&bestFit<diff)){
						bestFit=diff;
						bestGroup=g;
					}
				}
				
			}
		}
		ArrayList<Student> unadded = new ArrayList<Student>();
		
		for(Student s: students){
			if(!bestGroup.isFull()){
				bestGroup.add(s);
				unAddedStudents.remove(s);
			}
			else{
				unadded.add(s);
			}
		}
		return unadded;
		
	}
}
