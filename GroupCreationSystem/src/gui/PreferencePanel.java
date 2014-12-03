/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import utility.Student;
import utility.Controller;
/**
 * @author Philip
 *
 */
public class PreferencePanel extends JPanel {
	private Controller controller;
	
	private JList<String> lstStudents;
	private DefaultListModel<String> mdlStudents;
	private JComboBox<String> cmbPreference;
	private DefaultComboBoxModel<String> mdlPreference;
	private JTextArea txtPreferences;
	private JLabel lblCourseName;
	private JLabel lblPreferences;
	private JLabel lblSetPrefs;
	
	private JButton btnAddPref;
	private JButton btnNext;
	
	private boolean done=false;
	
	public static final int WINDOW_WIDTH = 440;
	public static final int WINDOW_HEIGHT = 330;
	
	public void addComponents() {
		setLayout(new BorderLayout());
		add(lblCourseName,BorderLayout.NORTH);
		JPanel center = new JPanel(new GridLayout(2,1));
		JPanel p = new JPanel(new BorderLayout());
		p.add(lblPreferences,BorderLayout.NORTH);
		JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JScrollPane scrlStudents = new JScrollPane(lstStudents);
		pCenter.add(scrlStudents);
		pCenter.add(cmbPreference);
		pCenter.add(btnAddPref);
		p.add(pCenter);
		center.add(p);
		JPanel q = new JPanel(new BorderLayout());
		q.add(lblSetPrefs,BorderLayout.NORTH);
		q.add(txtPreferences,BorderLayout.CENTER);
		btnNext.setPreferredSize(new Dimension(64,28));
		JPanel qSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		qSouth.add(btnNext);
		q.add(qSouth,BorderLayout.SOUTH);
		center.add(q);
		add(center);
	}
	
	public PreferencePanel(Controller controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		lblCourseName = new JLabel(controller.getCourseNumber());
		lblPreferences = new JLabel("Select students who should/shouldn't work together from list");
		lblSetPrefs = new JLabel("Your preferences:");
		
		txtPreferences = new JTextArea();
		
		mdlStudents = new DefaultListModel<String>();
		for(Student student : controller.getAllStudents()) {
			mdlStudents.addElement(student.getName());
		}
		lstStudents = new JList<String>(mdlStudents);
		lstStudents.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		mdlPreference = new DefaultComboBoxModel<String>();
		mdlPreference.addElement("must work together");
		mdlPreference.addElement("cannot work together");
		cmbPreference = new JComboBox<String>(mdlPreference);
		btnNext = new JButton("Next");
		
		btnAddPref = new JButton("Confirm");
		btnAddPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPreferenceToList();
			}
		});
		addComponents();
	}
	public boolean isDone() {
		return done;
	}
	public void addNextListener(ActionListener buttonListener) {
		btnNext.addActionListener(buttonListener);
	}
	public void addPreferenceToList() {
		String prefString = "";
		boolean preferenceLevel = (cmbPreference.getSelectedIndex() == 0);
		for(int i=0;i<lstStudents.getSelectedValuesList().size();i++) {

			String name = lstStudents.getSelectedValuesList().get(i);
			Student s1 = controller.getStudentByName(name);
			
			for(int j=i+1;j<lstStudents.getSelectedValuesList().size();j++) {
				Student s2 = controller.getStudentByName(lstStudents.getSelectedValuesList().get(j));
				controller.addPreference(s1, s2, preferenceLevel);
			}
			if(i==0) {
				prefString+=name;
			}
			else if(i == lstStudents.getSelectedValuesList().size() - 1) {
				prefString +=" and "+name;
			}
			else {
				prefString += ", "+name;
			}
		}
		prefString += " " + (String)cmbPreference.getSelectedItem()+"\n";
		txtPreferences.append(prefString);
	}
}
