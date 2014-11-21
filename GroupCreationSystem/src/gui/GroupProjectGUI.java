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
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
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
		final Controller cont=controller;
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
		
		cont.generateGroups(); // makes the groups in the controller
		DefaultListModel<String> groupModel=new DefaultListModel<String>(); 
		// groupModel is a list of the group names, effectively
		
		final Collection<Group> groups=cont.getGroups();
		// list of all groups from controller
		
		for (Group group:groups){
			groupModel.addElement(group.getName());
			//the model gets each group name added
		}
		groupModel.addElement("Unassigned");
		
		final JList<String> groupList = new JList<String>(groupModel);
		// groupList is a list who implements the model
		groupList.setBounds(0, 0, 109, 230);
		groupList.setBorder(null);
		
		JScrollPane scrlGroups = new JScrollPane();
		scrlGroups.setViewportBorder(null);
		scrlGroups.setViewportView(groupList);
		scrlGroups.setBounds(0, 0, 109, 263);
		groupsPanel.add(scrlGroups);
		
		JPanel groupMembersPanel = new JPanel();
		groupMembersPanel.setBounds(172, 30, 252, 120);
		contentPane.add(groupMembersPanel);
		groupMembersPanel.setLayout(null);
		
		final DefaultListModel<String> lmModel = new DefaultListModel<String>();
		// lmModel is like groupModel but for the GROUP MEMBER LIST
		final JList<String> lstMembers = new JList<String>(lmModel);
		// lstMembers is the JList who gets to hold lmModel

		groupList.addListSelectionListener( new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				for(Group group:groups) {
					group.printGroup(); // for debugging
				}
				int i = 0;
				lmModel.clear(); //make sure lmModel is empty to begin
				//handles getting the unassigned students
				if(groupList.getSelectedValue()=="Unassigned"){
					Collection<Student> unassignedCollection = cont.getUnassignedStudents();
					for (Student stud:unassignedCollection){
						lmModel.addElement(stud.getName());
					}
				}
				else{
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
						lmModel.addElement(student.getName());
					}
				}
				
			}
		});
		lstMembers.setBounds(0, 0, 252, 120);
		
		JScrollPane scrlMembers = new JScrollPane();
		scrlMembers.setBounds(0, 0, 252, 120);
		scrlMembers.setColumnHeaderView(lstMembers);
		scrlMembers.setViewportView(lstMembers);
		groupMembersPanel.add(scrlMembers);
		
		
		
		JButton moveButton = new JButton("MOVE");
		
		moveButton.setBounds(335, 156, 89, 23);
		//listener for move button, onclick it opens a popup window
		//for the group to move the selected student into.
		//remember to allow moving student to unassigned.
		DefaultListModel<String> popupGroupModel=new DefaultListModel<String>(); 
		for (Group group:groups){
			popupGroupModel.addElement(group.getName());
			//the model gets each group name added
		}
		//HERE WE ARE WAHOO
		popupGroupModel.addElement("Unassigned");
		
		final JList<String> popupGroupList = new JList<String>(popupGroupModel);
		moveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final String selectedGroup = groupList.getSelectedValue();
				final String selectedStudent= lstMembers.getSelectedValue();
				if(selectedStudent == null){
				}
				else{
					
					final JDialog popup = new JDialog();
					popup.setLayout(new FlowLayout());
					popup.setSize(400,400);
					
					final JPanel popupGroups = new JPanel();
					JLabel overhead = new JLabel("Move "+selectedStudent+" Where?");
					overhead.setBounds(0, 0, 109, 14);
					popup.add(overhead);
					popup.setSize(300,300);
					
					JScrollPane groupsScroll = new JScrollPane();
					groupsScroll.setViewportView(popupGroupList);
					popupGroups.setBounds(0,20,200,300);
					popupGroups.add(groupsScroll);
					popup.add(popupGroups);
					JButton moveIt =new JButton("MOVE");
					//PUT IN MOVING TO THE ABYSS
					moveIt.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							//if from the unassigned list
							String moveTo = popupGroupList.getSelectedValue(); 
							Student student= cont.getStudentByName(selectedStudent);
							if (selectedGroup =="Unassigned"){
								if (moveTo=="Unassigned"){
									//do nothing should change this
								}
								else{
									Group toGroup = cont.getGroupByName(moveTo);

									cont.addStudent(student, toGroup);
								}
							}
							else{
								Group fromGroup = cont.getGroupByName(selectedGroup);//HANDLE IF FROM GROUP IS UNASSIGNED
								//check if group is full
								if (moveTo=="Unassigned"){
									cont.removeStudent(student, fromGroup);
								}
								else{
									Group toGroup = cont.getGroupByName(moveTo);
									
									cont.removeStudent(student,fromGroup);
									cont.addStudent(student, toGroup);
									/* This was here for testing, had a problem tho, so ignoring for now
									if(toGroup.isFull()) {
										JDialog popup2 = new JDialog();
										popup2.setSize(50,200);
										popup2.add(new JLabel("WARNING: "+toGroup.getName()+" is full, moved anyway."));
										popup2.setVisible(true);
									}
									*/
								}
							}
							System.out.println("here");
							groupList.setSelectedValue(moveTo,true);
							popup.setVisible(false);
						}
					});
					
					moveIt.setBounds(100,100,120,120);
					popup.add(moveIt);
					popup.setVisible(true);
				}
			}
		});
		
		contentPane.add(moveButton);
		
		JLabel groupsLabel = new JLabel("Group List:");
		groupsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		groupsLabel.setBounds(10, 5, 109, 14);
		contentPane.add(groupsLabel);
		
		JLabel membersLabel = new JLabel("Group Members:");
		membersLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		membersLabel.setBounds(172, 5, 117, 14);
		contentPane.add(membersLabel);
		
	}
}

