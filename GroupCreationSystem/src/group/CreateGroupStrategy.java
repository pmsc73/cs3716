package group;

import java.util.Collection;

import utility.Group;
import utility.Student;

public interface CreateGroupStrategy {
	public void fillGroups(Collection<Group> groups);
	public void addRemainingStudents(Collection<Group> groups, Collection<Student> unassignedStudents);
	public void displayWarning();
}
