package group;

import java.util.Collection;

import utility.Student;

public class PreferenceManager {
	private Collection<Preference> requiredGroups;
	private Collection<Preference> disallowedGroups;
	private Collection<Preference> requestedGroups;
	private Collection<Preference> unpreferredGroups;
	
	public Collection<Preference> getInstructorPreferences(){
		return requiredGroups;
	}
	public Collection<Preference> getInstructorDisallowed(){
		return disallowedGroups;
	}
	public Preference getStudentPreferences(Student s){
		return null;
	}
	public Preference getStudentUnPreferred(Student s){
		return null;
	}
	public void addPreference(Student s1, Student s2, boolean preferred){
		//this method should check that the preference is not in the professor's list of disallowed preferences, or in the students list of unpreferred partners. Otherwise it can be added.
	}
	public void removePreference(Student s1, Student s2, boolean preferred){
		
	}
}
