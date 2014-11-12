package test;

import java.util.Collection;

import utility.Group;
import utility.Student;
import group.CreateGroupStrategy;

public class BasicDistributionStrategy implements CreateGroupStrategy{
	private Collection<Student> students;
	
	public BasicDistributionStrategy(Collection<Student> students){
		this.students=students;
	}
	public void fillGroups(Collection preferences, Collection<Group> groups) {
		int i,j;
		i=0;
		j=0;
		for(Group g: groups){
			for(Student s:students)
			if(!g.isFull()){
				g.add(s);
				students.remove(s);
			}
		}
	}
	public void addRemainingStudents(Collection<Group> groups, Collection<Student> unassignedStudents){
		for(Student s: students){
			boolean added=false;
			for(Group g: groups){
				if(g.Compatible(s)){
					g.add(s);
					unassignedStudents.remove(s);
					added=true;
				}

				
			}
			if(!added) displayWarning();
		}
	}
	public void displayWarning(){
		
	}

}
