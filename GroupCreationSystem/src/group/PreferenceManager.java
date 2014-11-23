package group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import utility.Student;

public class PreferenceManager {
	private HashSet<Preference> requiredGroups;
	private HashSet<Preference> disallowedGroups;
	
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
		ArrayList<Student> students= new ArrayList<Student>();
		if(disallowedGroups.size()>0){
			p=disallowedGroups.iterator().next();
			students.add(p.getSource());
			students.add(p.getTarget());
			for(Preference x: disallowedGroups){
				for(Student s: students){
					if(x.involves(s)) students.add(x.getPartner(s));
					disallowedGroups.remove(x);
				}
			}
			return students;
		}
		
		else return null;
	}
	/****
	 * Method to get a grouping of students which must work together. Note that
	 * the group is removed from the PreferenceManager when this method is called.
	 ****/
	public Collection<Student> getAllowedSet(){
		Preference p;
		ArrayList<Student> students= new ArrayList<Student>();
		if(requiredGroups.size()>0){
			p=requiredGroups.iterator().next();
			students.add(p.getSource());
			students.add(p.getTarget());
			for(Preference x: requiredGroups){
				for(Student s: students){
					if(x.involves(s)) students.add(x.getPartner(s));
					requiredGroups.remove(x);
				}
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
	public void specifyPreference(Student s1, Student s2, int pref){
		if(pref==1){
			requiredGroups.add(new Preference(s1, s2, pref));
		}
		if(pref==-1){
			disallowedGroups.add(new Preference(s1,s2,pref));
		}
	}
	
}
