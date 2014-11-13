package utility;

import java.util.Collection;
public class Group{
	int MAXSTUDENTS;
	int numStudents;
	Collection<Student> members;
	
	public Group(int max){
		MAXSTUDENTS=max;
	}
	
	public boolean Compatible(Student s){
		if(numStudents>MAXSTUDENTS){
			//check other conditions in here first, then
			return true;
		}
		else return false;
	
	}
	
	public void add(Student s){
		
	}
	
	public void remove(Student s){
	
	}

	public boolean isFull() {
		if(numStudents>=MAXSTUDENTS) return true;
		else return false;
	}


}