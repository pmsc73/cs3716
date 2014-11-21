package utility;

import java.util.ArrayList;
import java.util.Collection;

/****
 * Class to represent a group
 * 
 * @author Emily
 *
 ****/
public class Group{
	
	private int MAXSTUDENTS;
	private int numStudents;
	private Collection<Student> members;
	private String groupName;
	private static int id=0;
	
	public Group(int max){
		MAXSTUDENTS=max;
		members=new ArrayList<Student>();
		groupName = "Group "+id++;
	}

	/****
	 * Method to add a student to a group. Will only add a student if the group isn't full.
	 * 
	 ****/
	public  void add(Student s){
		
		//if(numStudents<MAXSTUDENTS){
			members.add(s);
			numStudents++;
			
		//}
		//else return false;
		
	}
	/****
	 * Method to remove a student from a group. Returns true if the student was sucessfully removed.
	 * 
	 ****/
	public boolean remove(Student s){
		boolean removed = members.remove(s);
		if(removed) numStudents--;
		return removed;
	
	}
	public void setName(String s){
		groupName=s;
	}
	
	public String getName(){
		return groupName;
	}
	
	public Collection<Student> getGroupMembers(){
		return members;
	}

	public boolean isFull() {
		if(numStudents>=MAXSTUDENTS) return true;
		else return false;
	}
	
	public boolean equals(Object o){
		return ((Group)o).groupName.equals(this.groupName);
	}
	/**
	 * Test method
	 */
	public void printGroup() {
		for(Student s: members){
			System.out.println(s.getName());
		}
		System.out.println("____________________");
		
	}
	public int getGroupCapacity(){
		return MAXSTUDENTS;
	}
	public int getCurrentSize(){
		return numStudents;
	}


}