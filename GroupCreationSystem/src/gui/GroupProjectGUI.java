/**
 * @Author-Noah
 */

package gui;
import group.*;

import java.util.Collection;

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
		
		DefaultListModel groupModel=new DefaultListModel();
		
		Collection<Group> groups=controller.getGroups();
		/*
		for (Group group:groups){
			groupModel.addElement(group.getName());
		}
		*/
		for (int i=0; i<controller.getGroupSize(); i++){
			groupModel.addElement("Group"+i);
		}
		JList groupList = new JList(groupModel);
		groupList.setBounds(0, 0, 109, 230);
		groupList.setBorder(null);
		
		groupList.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged(ListSelectionEvent arg){
				System.out.print("help");
				
			}
		});
		
		
		
		JScrollPane scrlGroups = new JScrollPane();
		scrlGroups.setViewportBorder(null);
		scrlGroups.setViewportView(groupList);
		scrlGroups.setBounds(0, 0, 109, 263);
		groupsPanel.add(scrlGroups);
		
		JPanel groupMembersPanel = new JPanel();
		groupMembersPanel.setBounds(172, 30, 252, 120);
		contentPane.add(groupMembersPanel);
		groupMembersPanel.setLayout(null);
		
		JList lstMembers = new JList();
		lstMembers.setModel(new AbstractListModel() {
			String[] values = new String[] {"Member 1", "Member 2", "Member 3", "Bob Smith Worthenshire III"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lstMembers.setBounds(0, 0, 252, 120);
		
		JScrollPane scrlMembers = new JScrollPane();
		scrlMembers.setBounds(0, 0, 252, 120);
		scrlMembers.setColumnHeaderView(lstMembers);
		groupMembersPanel.add(scrlMembers);
		
		JPanel pnlUnassigned = new JPanel();
		pnlUnassigned.setBounds(173, 198, 251, 93);
		contentPane.add(pnlUnassigned);
		pnlUnassigned.setLayout(null);
		
		JList unassignedStudentList = new JList();
		unassignedStudentList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Null", "John", "Stephen", "Member XYZ"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		unassignedStudentList.setBounds(0, 0, 251, 93);
		
		JScrollPane scrlUnassigned = new JScrollPane();
		scrlUnassigned.setViewportView(unassignedStudentList);
		scrlUnassigned.setBounds(0, 0, 251, 93);
		pnlUnassigned.add(scrlUnassigned);
		
		JButton btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMove.setBounds(335, 156, 89, 23);
		contentPane.add(btnMove);
		
		JLabel lblGroups = new JLabel("Group List:");
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGroups.setBounds(10, 5, 109, 14);
		contentPane.add(lblGroups);
		
		JLabel lblMembers = new JLabel("Group Members:");
		lblMembers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMembers.setBounds(172, 5, 117, 14);
		contentPane.add(lblMembers);
		
		JLabel lblUnassigned = new JLabel("Unassigned Members:");
		lblUnassigned.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUnassigned.setBounds(172, 175, 153, 14);
		contentPane.add(lblUnassigned);
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
