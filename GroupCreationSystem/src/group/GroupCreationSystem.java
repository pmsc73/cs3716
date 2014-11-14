package group;

import java.util.Collection;

import utility.Group;
import utility.Registrar;
import utility.Student;

public class GroupCreationSystem {
	
	private int courseNumber;
	private int groupSize;
	private boolean skillBased;
	private double deadline;
	private String instructor;
	private ProjectGroups creator;
	boolean initialized;
	private Collection<Student> allStudents;
	
	public GroupCreationSystem(){
		creator=new ProjectGroups();
		initialized=false;
	}
	
	public Collection<Group> createGroups(int courseNum, int groupSize, boolean skillBased, double deadline, String instructor){
		initialize(courseNum, groupSize, skillBased, deadline,instructor);
		allStudents=Registrar.getStudents(courseNum);
		for(Student s: allStudents){
			Registrar.getSchoolSchedule(s);
		}
		creator.createEmptyGroups(allStudents.size(), groupSize);
		if(skillBased){
			creator.setSkillBased();
		}
		return null;
	}
	
	public void initialize(int courseNum, int groupSize, boolean skillBased, double deadline, String instructor){
		this.courseNumber=courseNum;
		this.groupSize=groupSize;
		this.skillBased=skillBased;
		this.deadline=deadline;
		this.instructor=instructor;
		//lst=Registrar.getStudents(courseNumber); This would be where the system retrieves student information from the registrar
		initialized=true;
		/******
		 * Code for notifying students of deadline goes here
		 ******/
	}
	public void addStudentsToGroups(){
		creator.fillGroups(skillBased);
	}
}
