/**
 * 
 */
package group;

import java.util.Collection;

import utility.Group;
import utility.Student;

/****
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
	 * This method removes a student from a group. Might throw an exception in the future if the student couldn't be removed
	 ****/
	public void removeStudent(Student s, Group g){
		GroupManager creator = sys.getGroupManager();
		boolean b=creator.removeStudent(s, g);
		if(!b); //throw exception? We'll figure this out later
	}
}
