package group;

import java.util.Collection;

import utility.Student;

public class PreferenceManager {
	private Collection<Collection<Student>> requiredGroups;
	private Collection<Collection<Student>> disallowedGroups;
	private Collection<Collection<Student>> requestedGroups;
	private Collection<Collection<Student>> unpreferredGroups;
	
	public Collection<Collection<Student>> getInstructorPreferences(){
		return requiredGroups;
	}
	public Collection<Collection<Student>> getInstructorDisallowed(){
		return disallowedGroups;
	}
	public Collection<Student> getStudentPreferences(Student s){
		return null;
	}
	public Collection<Student> getStudentUnPreferred(Student s){
		return null;
	}
	public void addPreference(Student s1, Student s2, boolean preferred){
		//this method should check that the preference is not in the professor's list of disallowed preferences, or in the students list of unpreferred partners. Otherwise it can be added.
	}
	public void removePreference(Student s1, Student s2, boolean preferred){
		
	}
}
