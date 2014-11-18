package utility;

import java.util.Collection;

import questionnaire.Question;
import schedule.ClassSchedule;
import schedule.Schedule;

/****
 * Class to represent a student
 * @author Emily
 *
 */
public class Student {
	private String name;
	private String id;
	private Schedule personalSched;
	private ClassSchedule schedule;
	private Collection<Question> questionnaire;
	
	public Student(String name, String id){
		this.name=name;
		this.id=id;
	}
	

	
	public void answerQuestion(int questionNumber){
		
	}


	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}


	public Schedule getPersonalSched() {
		return personalSched;
	}


	public Collection<Question> getQuestionnaire() {
		return questionnaire;
	}

	
	public void setQuestionnaire(Collection<Question> questionnaire) {
		this.questionnaire = questionnaire;
	}


	public ClassSchedule getSchedule() {
		return schedule;
	}
}

