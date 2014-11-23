/**
 * 
 */
package utility;

import group.GroupCreationSystem;
import group.GroupManager;

import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/****
 * Controller class which contains all of the methods that the GUI might need. Responsible for
 * delegating work to other classes.
 * @author Emily
 *
 ****/
public class Controller {
		
	private GroupCreationSystem sys;
	
	public Controller(){
		sys=new GroupCreationSystem();
	}
	
	public void setCourseNumber(String num){
		sys.setCourseNumber(num);
	}
	public String getCourseNumber(){
		return sys.getCourseNumber();
	}
	public void setGroupSize(int size){
		sys.setGroupSize(size);
	}
	public int getGroupSize(){
		return sys.getGroupSize();
	}
	public void setDeadline(double deadline){
		sys.setDeadline(deadline);
	}
	public double getDeadline(){
		return sys.getDeadline();
	}
	public void setInstructor(String ins){
		sys.setInstructor(ins);
	}
	public String getInstructor(){
		return sys.getInstructor();
	}
	/****
	 * This is the method used once all of the parameters have been entered. Starts creation of empty groups.
	 * 
	 ****/
	public void finalizeParameters(){
		sys.startEmptyGroups();
	}
	/****
	 * This method gives all of the groups
	 *  
	 ****/
	public Collection<Group> getGroups(){
		return sys.getGroups();
	}
	/****
	 * This method gives all of the students
	 ****/
	public Collection<Student> getAllStudents(){
		return sys.getStudents();
	}
	/****
	 * This method gives all of the students that haven't been assigned to groups
	 */
	public Collection<Student> getUnassignedStudents(){
		return sys.getGroupManager().getUnaddedStudents();
	}
	/****
	 * This method removes a student from a group. Might throw an exception in the future if the student couldn't be removed
	 ****/
	public void removeStudent(Student s, Group g){
		GroupManager creator = sys.getGroupManager();
		boolean b=creator.removeStudent(s, g);
		if(!b); //throw exception? We'll figure this out later
	}
	/****
	 * This method adds a student to a group. Probably will throw an exception if unsuccessful
	 ****/
	public void addStudent(Student s, Group g){
		GroupManager creator = sys.getGroupManager();
		if(g.getCurrentSize()>=creator.getMaxGroupSize()){
			JDialog popup2 = new JDialog();
			popup2.setSize(200,50);
			popup2.add(new JLabel("WARNING: "+g.getName()+" is over capacity!"));
			popup2.setVisible(true);
		}
		creator.addStudent(s, g);
		
		
	}
	/****
	 * Starts the group generation process. Should probably throw an exception later.
	 ****/
	public void generateGroups(){
		sys.addStudentsToGroups();
	}
	
	/****
	 * Gets the group members from a group with a given name.
	 * @param groupName - name of the group
	 * @return members in the group with groupName.
	 ****/
	public Collection<Student> getStudentsFromGroup(String groupName){
		Group g = new Group(0);
		g.setName(groupName);
		for(Group x: sys.getGroups()){
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
		Collection<Student> students = sys.getStudents();
		for(Student x: students){
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
		Collection<Group> groups = sys.getGroups();
		for(Group x: groups){
			if (x.equals(g)) return x;
		}
		return null;
		
	}
}
