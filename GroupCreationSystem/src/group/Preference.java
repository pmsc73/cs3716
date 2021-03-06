
package group;
import utility.Student;

/****
 * Preference class 
 * An instructor preference is undirected, and can be thought of as 
 * positive weight --> must work together
 * negative weight --> cannot work together
 *
 * @author pmsc73
 ****/
public class Preference {
	private int level; //indicates positive or negative preference, or 0 for no
	private Student student1;
	private Student student2;
	
	public Preference(Student student1, Student student2, int preferred) {
		this.student1 = student1;
		this.student2 = student2;
		this.level = preferred;
	}
	/**
	 * getters
	 * Considering renaming them getStudent1 and getStudent2 but
	 * those names are pretty lackluster, idk.
	 */
	// suitable name for this method?
	public Student getTarget() { 
		return this.student2;
	}
	// suitable name for this method?
	public Student getSource() {
		return this.student1;
	}
	public int getLevel() {
		return this.level;
	}
	public boolean involves(Student s){
		return student1.equals(s)||student2.equals(s);
	}
	/****
	 * @param s  The student whose partner is being found
	 * @return The student's partner
	 *****/
	public Student getPartner(Student s) {
		if (s.equals(student1)) return student2;
		if (s.equals(student2)) return student1;
		return null;
	}
	public boolean equals(Object o){
		Preference pref = (Preference)o;
		boolean studentsEqual = (this.student1.equals(pref.student1)||this.student1.equals(pref.student2))&&(this.student2.equals(pref.student1)||this.student2.equals(pref.student2));
		return studentsEqual&&equals(this.level==pref.level);
	}

}
