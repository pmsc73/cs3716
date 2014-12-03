/**
 * 
 */
package gui;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import utility.Controller;
import utility.Group;
import utility.Student;
/**
 * @author Philip
 *
 */
public class ShowGroupsPanel extends JPanel {
	private Controller controller;
	private JTextArea txtGroups;
	
	public ShowGroupsPanel(Controller controller){
		this.controller = controller;
		this.setLayout(new BorderLayout());
		add(new JLabel("Final groups:"),BorderLayout.NORTH);
		txtGroups = new JTextArea();
		txtGroups.setEditable(false);
		txtGroups.setBackground(null);
		txtGroups.setBounds(0,0,640,480);
		for(Group group : controller.getGroups()) {
			String groupString = group.getName();
			for(Student student : group.getGroupMembers()) {
				groupString+="\n\t-" + student.getName();
			}
			txtGroups.append(groupString+"\n-----------------\n");
		}
		JScrollPane scrlGroups = new JScrollPane(txtGroups,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrlGroups.setBounds(0,0,640,480);
		add(scrlGroups,BorderLayout.CENTER);
	}
}
