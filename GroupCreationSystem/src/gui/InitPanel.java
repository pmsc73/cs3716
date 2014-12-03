/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

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
		setLayout(new GridLayout(8,1));
		FlowLayout leftFlow = new FlowLayout(FlowLayout.LEFT);
		StudentFileReader sfr = new StudentFileReader();
		DefaultComboBoxModel<String> mdlCourses = new DefaultComboBoxModel<String>();
		for(String course : sfr.getCourses())  {
			mdlCourses.addElement(course);
		}
		JPanel p = new JPanel(leftFlow);
		p.add(new JLabel("Course name:"));
		cmbCourses = new JComboBox<String>(mdlCourses);
		p.add(cmbCourses);
		add(p);
		String course = (String)cmbCourses.getSelectedItem();
		if(course==null) {
			course = "";
		}
		p = new JPanel(leftFlow);
		lblCourseSize = new JLabel("Number of students in course: " + sfr.getList(course+".dat").size());
		p.add(lblCourseSize);
		cmbCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = (String)cmbCourses.getSelectedItem();
				if(course==null) {
					course="";
				}
				lblCourseSize.setText("Number of students in course: " + sfr.getList(course+".dat").size()); 
			}
		});
		add(p);
		p = new JPanel(leftFlow);
		p.add(new JLabel("Group size: "));
		DefaultComboBoxModel<String> mdlGroupSize = new DefaultComboBoxModel<String>();
		for(int i = 1;i<sfr.getList(course+".dat").size();i++) {
			mdlGroupSize.addElement(Integer.toString(i));
		}
		cmbGroupSize = new JComboBox<String>(mdlGroupSize);
		p.add(cmbGroupSize);
		add(p);
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
		radGPASort.setSelected(true);
		radioButtons.add(radGPASort);
		radioButtons.add(radPrefSort);
		p = new JPanel(leftFlow);
		p.add(new JLabel("How do you want to sort groups?"));
		p.add(radGPASort);
		p.add(radPrefSort);
		add(p);
		btnNext = new JButton("Start Group Project");
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.add(btnNext);
		add(p);
	}
	public void addNextListener(ActionListener buttonListener) {
		btnNext.addActionListener(buttonListener);
	}
	public boolean isDone() {
		return done;
	}
}
