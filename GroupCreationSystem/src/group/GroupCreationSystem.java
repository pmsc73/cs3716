package group;

import java.util.Collection;

import utility.Group;
import utility.Registrar;
import utility.Student;
/****
 * Class to represent our group creation system
 * @author Emily
 *
 ****/
public class GroupCreationSystem {
	
	private String courseNumber;
	private int groupSize;
	private boolean skillBased;
	private double deadline;
	private String instructor;
	private ProjectGroups creator;
	boolean initialized;
	private Collection<Student> allStudents;
	
	public GroupCreationSystem(){
		initialized=false;
	}
	/****
	 *  This method is used to initialize a set of empty groups. 
	 * @param courseNum String representing the course number
	 * @param groupSize Maximum number of students per group
	 * @param skillBased Whether or not the groups will be based on skill
	 * @param deadline Deadline for entry of student information
	 * @param instructor String to represent the instructor
	 * @return
	 ****/
	public Collection<Group> startEmptyGroups(){
		initialize();
		allStudents=Registrar.getStudents(courseNumber);
		for(Student s: allStudents){
			Registrar.getSchoolSchedule(s);
		}
		creator= new ProjectGroups(allStudents);
		creator.createEmptyGroups(allStudents.size(), groupSize);
		if(skillBased){
			creator.setSkillBased();
		}
		return creator.getGroups();
	}
	
	private void initialize(){
		
		initialized=true;
		/******
		 * Code for notifying students of deadline goes here
		 ******/
	}
	
	/****
	 * Method used to create groups!
	 ****/
	public void addStudentsToGroups(){
		creator.fillGroups(skillBased);
	}

	/****
	 * Method which returns all of the groups
	 ****/
	public Collection<Group> getGroups() {
		return creator.getGroups();
		
	}
	/**
	 * @return the courseNumber
	 */
	public String getCourseNumber() {
		return courseNumber;
	}
	/**
	 * @param courseNumber the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	/**
	 * @return the groupSize
	 */
	public int getGroupSize() {
		return groupSize;
	}
	/**
	 * @param groupSize the groupSize to set
	 */
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	/**
	 * @return the instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * @return the deadline
	 */
	public double getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(double deadline) {
		this.deadline = deadline;
	}
}
