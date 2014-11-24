package utility;

import java.util.Collection;

import questionnaire.Question;
import schedule.ClassSchedule;
import schedule.Schedule;

/****
 * Class to represent a student
 * @author Emily, Philip
 *
 ****/
public class Student {
	private String name;
	private String id;
	private double gpa;
	
	private Schedule personalSched;
	private ClassSchedule schedule;
	private Collection<Question> questionnaire;
	
	public Student(String name, String id, double gpa){
		this.name=name;
		this.id=id;
		this.gpa=gpa;
	}
	

	
	public void answerQuestion(int questionNumber){
		
	}


	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}
	public double getGPA() {
		return gpa;
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
	public String toString(){
		String s = name;
		return s;
	}
	public boolean equals(Object o){
		return this.name.equals(((Student)o).name)&&this.id.equals(((Student)o).id);
	}



	
	public boolean equalsByName(Student x) {
		return x.name.equalsIgnoreCase(this.name);
	}
}

