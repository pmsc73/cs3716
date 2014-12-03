package utility;

import group.GroupManager;
import group.PreferenceManager;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JLabel;

import utility.Group;
import utility.Registrar;
import utility.Student;

/****
 * This is the controller class which allows the GUI to interface nicely with the workings of our system.
 * @author Emily, Philip
 *
 ****/
public class Controller {
	
	private String courseNumber;
	private int groupSize;
	private boolean skillBased=true;
	private double deadline;
	private String instructor;
	private GroupManager creator;
	boolean initialized;
	private Collection<Student> allStudents;
	private PreferenceManager manager;
	
	public Controller(){
		initialized=false;
		manager=new PreferenceManager();
		courseNumber="";
		groupSize=0;
		
	}
	/****
	 * This is the method used once all of the parameters have been entered. Starts creation of empty groups.
	 * 
	 ****/
	public void setSkillBased(boolean b) {
		skillBased = b;
	}
	public boolean getSkillBased() {
		return skillBased;
	}
	public void finalizeParameters(){
		initialize();
		setCourseNumber(courseNumber);
		creator.createEmptyGroups(allStudents.size(), groupSize);
		if(skillBased){
			creator.setSkillBased();
		}
	}
	
	private void initialize(){
		
		initialized=true;
		/******
		 * Code for notifying students of deadline goes here
		 ******/
	}
	
	/****
	 * Starts the group generation process. Should probably throw an exception later.
	 ****/
	public void generateGroups(){
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
	/****
	 * This method will also load the students from this course into the system.
	 * @param courseNumber the courseNumber to set. 
	 ****/
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
		allStudents=Registrar.getStudents(courseNumber);
		ArrayList<Student> copy = new ArrayList<Student>();
		for(Student s: allStudents){
			Registrar.getSchoolSchedule(s);
			copy.add(s);
		}
		creator= new GroupManager(copy, manager);
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

	

//inserted code
	

	/****
	 * This method gives all of the students
	 ****/
	public Collection<Student> getAllStudents(){
		return allStudents;
	}
	/****
	 * This method gives all of the students that haven't been assigned to groups
	 ****/
	public Collection<Student> getUnassignedStudents(){
		return creator.getUnaddedStudents();
	}
	/****
	 * This method removes a student from a group. Might throw an exception in the future if the student couldn't be removed
	 ****/
	public void removeStudent(Student s, Group g){
		boolean b=creator.removeStudent(s, g);
		if(!b); //throw exception? We'll figure this out later
	}
	/****
	 * This method adds a student to a group. Probably will throw an exception if unsuccessful
	 ****/
	public void addStudent(Student s, Group g){
		if(g.getCurrentSize()>=creator.getMaxGroupSize()){
			JDialog popup2 = new JDialog();
			popup2.setSize(200,50);
			popup2.add(new JLabel("WARNING: "+g.getName()+" is over capacity!"));
			popup2.setVisible(true);
		}
		creator.addStudent(s, g);
		
		
	}
	
	/****
	 * Gets the group members from a group with a given name.
	 * @param groupName - name of the group
	 * @return members in the group with groupName.
	 ****/
	public Collection<Student> getStudentsFromGroup(String groupName){
		Group g = new Group(0);
		g.setName(groupName);
		for(Group x: creator.getGroups()){
			if (x.equals(g)) return x.getGroupMembers();
		}
		return null;
		
	}
	
	/****
	 * @param Given a students name, returns the Student object which represents that student.
	 *  Returns null if the student does not exist
	 * 
	 ****/
	public Student getStudentByName(String name){
		Student s = new Student(name, null,0.0);
		for(Student x: allStudents){
			if(s.equalsByName(x)) return x;
		}
		return null;
	}
	/****
	 * @param Given a group name, returns the Group object which represents that group.
	 *  Returns null if the student does not exist
	 * 
	 ****/
	public Group getGroupByName(String name){
		Group g = new Group(0);
		g.setName(name);
		Collection<Group> groups = creator.getGroups();
		for(Group x: groups){
			if (x.equals(g)) return x;
		}
		return null;
		
	}
	/****
	 * Method to add a preference between two students
	 * @param s1
	 * @param s2
	 * @param preferred
	 *****/
	public void addPreference(Student s1, Student s2, boolean preferred){
		if(preferred) manager.specifyPreference(s1, s2, 1);
		else manager.specifyPreference(s1,s2,-1);
		
	}
	/*****
	 * Method to add a preference between a number of students
	 * @param preferred
	 ****/
	public void addGroupPreference(Collection<Student> students, boolean preferred){
		for (Student s1: students){
			for(Student s2: students){
				addPreference(s1,s2,preferred);
			}
		}
		
	}
	/****
	 * Method to get the names of courses for which there are files.
	 ****/
	public Collection<String> getCourses(){
		//TODO
		return null;
	}
}
