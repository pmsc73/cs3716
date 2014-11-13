package schedule;

import utility.Registrar;
import utility.Student;


public class ClassSchedule extends Schedule {

		private void retrieve(Student s){
			Registrar.getSchoolSchedule(s);
		}
		
}
