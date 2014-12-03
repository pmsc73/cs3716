package group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import utility.Student;
/****
 * This is a class to manage the adding of instructor preferences
 * @author Emily Innes
 *
 ****/
public class PreferenceManager {
	private HashSet<Preference> requiredGroups;
	private HashSet<Preference> disallowedGroups;
	private boolean hasPreferences=false;
	
	public PreferenceManager(){
		disallowedGroups= new HashSet<Preference>();
		requiredGroups = new HashSet<Preference>();
		
	}
	public Collection<Preference> getInstructorPreferences(){
		return requiredGroups;
	}
	public Collection<Preference> getInstructorDisallowed(){
		return disallowedGroups;
	}
	/****
	 * Method to get a grouping of students which are not allowed to work together. Note that
	 * the group is removed from the PreferenceManager when this method is called.
	 ****/
	public Collection<Student> getDisallowedSet(){
		Preference p;
		ArrayList<Student> students = new ArrayList<Student>();
		if(disallowedGroups.size()>0){
			p=disallowedGroups.iterator().next();
			students.add(p.getSource());
			students.add(p.getTarget());
			disallowedGroups.remove(p);
			ArrayList<Preference> usedPreferences= new ArrayList<Preference>();
			for(int i=0;i<students.size();i++){
				for(Preference x: disallowedGroups){
					if(x.involves(students.get(i))){
						usedPreferences.add(x);
						students.add(x.getPartner(students.get(i)));
					}
				}
				for(Preference x: usedPreferences)disallowedGroups.remove(x);
			}
			return students;
		}
		
		else return null;
	}
	/****
	 * Method to get a grouping of students which must work together. Note that
	 * the group is removed from the PreferenceManager when this method is called.
	 ****/
	public Collection<Student> getRequiredSet(){
		Preference p;
		ArrayList<Student> students = new ArrayList<Student>();
		if(requiredGroups.size()>0){
			int index =0;
			p=requiredGroups.iterator().next();
			students.add(p.getSource());
			students.add(p.getTarget());
			requiredGroups.remove(p);
			ArrayList<Preference> usedPreferences= new ArrayList<Preference>();
			for(int i=0;i<students.size();i++){
				for(Preference x: requiredGroups){
					if(x.involves(students.get(i))){
						usedPreferences.add(x);
						students.add(x.getPartner(students.get(i)));
					}
				}
				for(Preference x: usedPreferences)requiredGroups.remove(x);
			}
			return students;
			
		}
		else return null;
	}
	
	public boolean hasDisallowedSets(){
		return disallowedGroups.size()>0;
	}
	public boolean hasRequiredSets(){
		return requiredGroups.size()>0;
	}
	/****
	 * A method to specify a preference between two students
	 ****/
	public void specifyPreference(Student s1, Student s2, int pref){
		if(s1.equals(s2))return;
		if(pref==1){
			requiredGroups.add(new Preference(s1, s2, pref));
		}
		if(pref==-1){
			disallowedGroups.add(new Preference(s1,s2,pref));
		}
		hasPreferences=true;
	}
	/****
	 * A method to remove an existing preference between two students
	 ****/
	public void removePreference(Student s1, Student s2, int pref){
		if(s1.equals(s2))return;
		if(pref==1){
			requiredGroups.remove(new Preference(s1, s2, pref));
		}
		if(pref==-1){
			disallowedGroups.remove(new Preference(s1,s2,pref));
		}
		if(disallowedGroups.size()==0&&requiredGroups.size()==0)hasPreferences=false;
	}
	/****
	 * Method to determine if any preferences have been added to the manager
	 ****/
	public boolean hasPreferences(){
		return hasPreferences;
	}

	
}
