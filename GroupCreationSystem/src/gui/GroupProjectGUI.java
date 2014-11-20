/**
 * @Author-Noah,Philip
 */

package gui;
import group.*;

import java.util.Collection;
import java.util.ArrayList;

import utility.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;


public class GroupProjectGUI extends JFrame {
	private Controller controller;
	private JPanel contentPane;

	/**
	 * Launch the GUI page.
	 */
	public void start(){
		//controller=this.controller; //intializes variables 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupProjectGUI frame = new GroupProjectGUI(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Error creating swing window.");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GroupProjectGUI(Controller controller) {
		
		setTitle("Group Creation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel groupsPanel = new JPanel();
		groupsPanel.setBounds(10, 30, 109, 263);
		contentPane.add(groupsPanel);
		groupsPanel.setLayout(null);
		
		controller.generateGroups(); // makes the groups in the controller
		DefaultListModel<String> groupModel=new DefaultListModel<String>(); 
		// groupModel is a list of the group names, effectively
		
		Collection<Group> groups=controller.getGroups();
		// list of all groups from controller
		
		for (Group group:groups){
			groupModel.addElement(group.getName());
			//the model gets each group name added
		}
		
		JList<String> groupList = new JList<String>(groupModel);
		// groupList is a list who implements the model
		groupList.setBounds(0, 0, 109, 230);
		groupList.setBorder(null);
		
		JScrollPane groupsScroll = new JScrollPane();
		groupsScroll.setViewportBorder(null);
		groupsScroll.setViewportView(groupList);
		groupsScroll.setBounds(0, 0, 109, 263);
		groupsPanel.add(groupsScroll);
		
		JPanel groupMembersPanel = new JPanel();
		groupMembersPanel.setBounds(172, 30, 252, 120);
		contentPane.add(groupMembersPanel);
		groupMembersPanel.setLayout(null);
		
		DefaultListModel<String> memberModel = new DefaultListModel<String>();
		// memberModel is like groupModel but for the GROUP MEMBER LIST
		JList<String> groupMembersList = new JList<String>(memberModel);
		// groupMembersList is the JList who gets to hold memberModel
		
		groupMembersList.setBounds(0, 0, 252, 120);
		
		JScrollPane groupMembersScroll = new JScrollPane();
		groupMembersScroll.setBounds(0, 0, 252, 120);
		groupMembersScroll.setColumnHeaderView(groupMembersList);
		groupMembersPanel.add(groupMembersScroll);
		
		JPanel unassignedPanel = new JPanel();
		unassignedPanel.setBounds(173, 198, 251, 93);
		contentPane.add(unassignedPanel);
		unassignedPanel.setLayout(null);
		
		JList unassignedStudentList = new JList();
		
		unassignedStudentList.setBounds(0, 0, 251, 93);
		
		JScrollPane unassignedScroll = new JScrollPane();
		unassignedScroll.setViewportView(unassignedStudentList);
		unassignedScroll.setBounds(0, 0, 251, 93);
		unassignedPanel.add(unassignedScroll);
		
		/*
		 * This is by no means the best way, or the final way that this task will be done
		 * I ran into some trouble trying to use the ActionEvent that should be fired
		 * any time a list selection is changed. The following is TEMPORARY
		 * 
		 * Philip
		 */
		
		
		// TEMOPRARILY CHANGED MOVE BUTTON TO SAY REFRESH //
		JButton moveButton = new JButton("REFRESH");
		moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * This ActionListener should make the Group Member list have
				 * the appropriate things in it. There must be an error somewhere, 
				 * however, as it does not seem to do what I described
				 */
				for(Group group:groups) {
					group.printGroup(); // for debugging
				}
				int i = 0;
				memberModel.clear(); //make sure memberModel is empty to begin
				for(i=0;i<groups.size();i++){
					//debugging print
					System.out.println( ((ArrayList<Group>)groups).get(i).getName() + " : " + groupList.getSelectedValue());
					
					// tl;dr for this if: current selected group name == group(i) 
					if ((((ArrayList<Group>)groups).get(i).getName()).equals(groupList.getSelectedValue())) {
						System.out.println("success"); //prints our success
						break;
					}
				}
				ArrayList<Student> students = new ArrayList<Student>();
				if (i<groups.size()) {
					students = (ArrayList<Student>) ((ArrayList<Group>)groups).get(i).getGroupMembers();
				}
				for(Student student : students){ 
					System.out.println(student.getName());
					memberModel.addElement(student.getName());
				}
				
			}
		});
		moveButton.setBounds(335, 156, 89, 23);
		contentPane.add(moveButton);
		
		JLabel groupsLabel = new JLabel("Group List:");
		groupsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		groupsLabel.setBounds(10, 5, 109, 14);
		contentPane.add(groupsLabel);
		
		JLabel membersLabel = new JLabel("Group Members:");
		membersLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		membersLabel.setBounds(172, 5, 117, 14);
		contentPane.add(membersLabel);
		
		JLabel unassignedLabel = new JLabel("Unassigned Members:");
		unassignedLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		unassignedLabel.setBounds(172, 175, 153, 14);
		contentPane.add(unassignedLabel);
	}
}

//listener for changing the group list
class groupChangeListener implements ListSelectionListener{
	private Controller controller;//holds the controller to get groups
	public groupChangeListener(Controller controller){
		this.controller=controller;
	}
	public void valueChanged(ListSelectionEvent e){
		Collection<Group> groups=controller.getGroups();
		for (Group group:groups){
			
		}
	}
}
