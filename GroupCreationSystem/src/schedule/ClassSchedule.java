package schedule;

import utility.Registrar;
import utility.Student;

/****
 * A class to represent a student's class schedule.
 * Not used.
 ****/
public class ClassSchedule extends Schedule {

		private void retrieve(Student s){
			Registrar.getSchoolSchedule(s);
		}
		
}
