/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utility.Student;
import utility.Controller;
/**
 * This panel is used by the GroupProjectGUI in entering preferences to
 * hold a list of students
 * @author pmsc73
 *
 */
public class StudentListPanel extends JPanel {
	private static int count = 0;
	private final int WIDTH=2,HEIGHT=120;
	private JList<String> lstStudents;
	private Controller cont;
	
	public StudentListPanel(Controller cont) {
		this.cont = cont;
	
		DefaultListModel<String> studentModel = new DefaultListModel<String>();
		for(Student student : cont.getAllStudents()) {
			studentModel.addElement(student.getName());
		}
		lstStudents = new JList<String>(studentModel);
		lstStudents.setBounds((WIDTH+10)*(count),0,WIDTH,HEIGHT-15);
		JScrollPane scrlStudents = new JScrollPane(lstStudents);
		scrlStudents.setBounds(lstStudents.getBounds());
		this.setBounds((WIDTH+10)*(count),0,WIDTH,HEIGHT);
		this.add(scrlStudents);
		count++;
	}
	
	/*
	 * The following methods are overrides from the JPanel methods
	 * They are overridden because the sizing would not work properly
	 * otherwise
	 * 
	 * @author pmsc73
	 */
	
	public Student getSelectedStudent() {
		return cont.getStudentByName(lstStudents.getSelectedValue());
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH,HEIGHT);
	}
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
}
