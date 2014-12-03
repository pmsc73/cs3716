/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import utility.StudentFileReader;
import utility.Controller;

/**
 * @author Philip
 *
 */
public class InitPanel extends JPanel {
	private boolean done=true;
	private JButton btnNext;
	private JLabel lblCourseSize;
	private JRadioButton radGPASort;
	private JRadioButton radPrefSort;
	private JComboBox<String> cmbGroupSize;
	private JComboBox<String> cmbCourses;
	private Controller controller;
	
	public InitPanel(Controller controller) {
		this.controller = controller;
		StudentFileReader sfr = new StudentFileReader();
		DefaultComboBoxModel<String> mdlCourses = new DefaultComboBoxModel<String>();
		for(String course : sfr.getCourses())  {
			mdlCourses.addElement(course);
		}
		add(new JLabel("Course name:"));
		cmbCourses = new JComboBox<String>(mdlCourses);
		add(cmbCourses);
		String course = (String)cmbCourses.getSelectedItem();
		if(course==null) {
			course = "";
		}
		lblCourseSize = new JLabel("Number of students in course: " + sfr.getList(course+".dat").size());
		add(lblCourseSize);
		cmbCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = (String)cmbCourses.getSelectedItem();
				if(course==null) {
					course="";
				}
				lblCourseSize.setText("Number of students in course: " + sfr.getList(course+".dat").size()); 
			}
		});
		add(new JLabel("Group size: "));
		DefaultComboBoxModel<String> mdlGroupSize = new DefaultComboBoxModel<String>();
		for(int i = 0;i<sfr.getList(course+".dat").size();i++) {
			mdlGroupSize.addElement(Integer.toString(i));
		}
		cmbGroupSize = new JComboBox<String>(mdlGroupSize);
		add(cmbGroupSize);
		radGPASort = new JRadioButton("Sort by GPA");
		radGPASort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSkillBased(true);
			}
		});
		radPrefSort = new JRadioButton("Sort by preferences");
		radPrefSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSkillBased(false);
			}
		});
		ButtonGroup radioButtons = new ButtonGroup();
		radioButtons.add(radGPASort);
		radioButtons.add(radPrefSort);
		add(radGPASort);
		add(radPrefSort);
		btnNext = new JButton("Next");
		add(btnNext);
	}
	public void addNextListener(ActionListener buttonListener) {
		btnNext.addActionListener(buttonListener);
	}
	public boolean isDone() {
		return done;
	}
}
