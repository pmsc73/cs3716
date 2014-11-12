package utility;

import java.util.Collection;

import questionnaire.Question;
import schedule.ClassSchedule;
import schedule.Schedule;

public class Student {
	private String name;
	private String id;
	private Schedule personalSched;
	private ClassSchedule schedule;
	private Collection<Question> questionnaire;
	
	public boolean compatible (Student s){
		//CompatibleStrategy strategy = new CompatibleStrategy();//commented out for testing purposes only
		//strategy.compatible(this, s);
		return false; //for testing purposes only
	}

	public void answerQuestion(int questionNumber){
		
	}
}
