package utility;

import java.util.Collection;
public class Group{
	
	private int MAXSTUDENTS;
	private int numStudents;
	private Collection<Student> members;
	
	public Group(int max){
		MAXSTUDENTS=max;
	}

	
	public boolean add(Student s){
		
		if(numStudents<MAXSTUDENTS){
			members.add(s);
			return true;
		}
		else return false;
		
	}
	
	public void remove(Student s){
	
	}
	public Collection<Student> getGroupMembers(){
		return members;
	}

	public boolean isFull() {
		if(numStudents>=MAXSTUDENTS) return true;
		else return false;
	}


}