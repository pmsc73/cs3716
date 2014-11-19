/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import group.Controller;

/**
 * @author Matt
 *
 */
public class GroupSizeGUI extends JFrame {

	private JPanel contentPane;
	private String courseName;
	private boolean done;
	private int groupSize;
	private Controller controller;
	
	public boolean getStatus() {
		return done;
	}

	/**
	 * Create the frame.
	 */
	public GroupSizeGUI(Controller control) {
		this.controller = control;
		done = false;
		setTitle("Enter Group Size");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(260,175);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel groupSizeLabel = new JLabel("Group size:");
		JLabel courseNameLabel = new JLabel("Course name:");
		
		final JTextField groupSizeField = new JTextField(20);
		final JTextField courseNameField = new JTextField(20);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// button pressed
				try {
					groupSize = Integer.parseInt(groupSizeField.getText());
				}
				catch(NumberFormatException NaN) {
					groupSizeField.setText("Try again");
					return;
				}
				courseName = courseNameField.getText();
				if(courseName.equals("")) {
					return;
				}
				controller.setCourseNumber(courseName);
				controller.setGroupSize(groupSize);
				done = true;
			}
		});
		
		contentPane.add(courseNameLabel);
		contentPane.add(courseNameField);
		contentPane.add(groupSizeLabel);
		contentPane.add(groupSizeField);
		contentPane.add(confirm);
		
	}
}
