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
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GroupProjectGUI extends JFrame {
	private Controller controller;
	private JPanel contentPane;
	private JTextField txtCourse;
	private JTextField txtSize;
	private int groupSize;
	private String courseName;
	private boolean done;

	/**
	 * Launch the GUI page.
	 */
	public void start() {
		// controller=this.controller; //initializes variables

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
		final Controller cont = controller;

		setTitle("Group Creation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 555, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JPanel groupsPanel = new JPanel();
		groupsPanel.setBounds(10, 85, 109, 131);
		contentPane.add(groupsPanel);
		groupsPanel.setLayout(null);

		txtCourse = new JTextField();
		txtCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCourse.setBounds(95, 11, 86, 20);
		contentPane.add(txtCourse);
		txtCourse.setColumns(10);

		JLabel lblCourseName = new JLabel("Course name:");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourseName.setBounds(10, 14, 86, 14);
		contentPane.add(lblCourseName);

		JLabel lblGroupSize = new JLabel("Group size:");
		lblGroupSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGroupSize.setBounds(202, 14, 71, 14);
		contentPane.add(lblGroupSize);

		txtSize = new JTextField();
		txtSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSize.setBounds(272, 11, 86, 20);
		contentPane.add(txtSize);
		txtSize.setColumns(10);

		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					int size = Integer.parseInt(txtSize.getText());
					if (size > 0) {
						groupSize = size;
					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException NaN) {
					txtSize.setText("Try again");
					return;
				}
				courseName = txtCourse.getText();
				if (courseName.equals("")) {
					return;
				}
				cont.setCourseNumber(courseName);
				cont.setGroupSize(groupSize);
				cont.finalizeParameters();

				cont.generateGroups(); // makes the groups in the controller
				DefaultListModel<String> groupModel = new DefaultListModel<String>();
				// groupModel is a list of the group names, effectively

				final Collection<Group> groups = cont.getGroups();
				// list of all groups from controller

				for (Group group : groups) {
					groupModel.addElement(group.getName());
					// the model gets each group name added
				}
				groupModel.addElement("Unassigned");

				final JList<String> groupList = new JList<String>(groupModel);
				// groupList is a list who implements the model
				groupList.setBounds(0, 0, 109, 230);
				groupList.setBorder(null);

				JScrollPane scrlGroups = new JScrollPane();
				scrlGroups.setViewportBorder(null);
				scrlGroups.setViewportView(groupList);
				scrlGroups.setBounds(0, 0, 109, 131);
				groupsPanel.add(scrlGroups);

				JPanel groupMembersPanel = new JPanel();
				groupMembersPanel.setBounds(172, 85, 252, 131);
				contentPane.add(groupMembersPanel);
				groupMembersPanel.setLayout(null);

				final DefaultListModel<String> lmModel = new DefaultListModel<String>();
				// lmModel is like groupModel but for the GROUP MEMBER LIST
				final JList<String> lstMembers = new JList<String>(lmModel);
				// lstMembers is the JList who gets to hold lmModel

				groupList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						for (Group group : groups) {
							group.printGroup(); // for debugging
						}
						int i = 0;
						lmModel.clear(); // make sure lmModel is empty to begin
						// handles getting the unassigned students
						if (groupList.getSelectedValue() == "Unassigned") {
							Collection<Student> unassignedCollection = cont
									.getUnassignedStudents();
							for (Student stud : unassignedCollection) {
								lmModel.addElement(stud.getName());
							}
						} else {
							for (i = 0; i < groups.size(); i++) {
								// debugging print
								System.out.println(((ArrayList<Group>) groups)
										.get(i).getName()
										+ " : "
										+ groupList.getSelectedValue());

								// tl;dr for this if: current selected group
								// name ==
								// group(i)
								if ((((ArrayList<Group>) groups).get(i)
										.getName()).equals(groupList
										.getSelectedValue())) {
									System.out.println("success"); // prints our
																	// success
									break;
								}
							}
							ArrayList<Student> students = new ArrayList<Student>();
							if (i < groups.size()) {
								students = (ArrayList<Student>) ((ArrayList<Group>) groups)
										.get(i).getGroupMembers();
							}
							for (Student student : students) {
								System.out.println(student.getName());
								lmModel.addElement(student.getName());
							}
						}

					}
				});
				lstMembers.setBounds(0, 0, 252, 120);

				JScrollPane scrlMembers = new JScrollPane();
				scrlMembers.setBounds(0, 0, 252, 130);
				scrlMembers.setColumnHeaderView(lstMembers);
				scrlMembers.setViewportView(lstMembers);
				groupMembersPanel.add(scrlMembers);

				JButton btnMove = new JButton("MOVE");

				btnMove.setBounds(442, 84, 89, 23);

				DefaultComboBoxModel<String> moveGroupModel = new DefaultComboBoxModel<String>();

				ArrayList<Group> moveGroupList = (ArrayList<Group>) cont
						.getGroups();

				for (int i = 0; i < moveGroupList.size(); i++) {
					moveGroupModel.addElement(moveGroupList.get(i).getName());
				}
				moveGroupModel.addElement("Unassigned");

				final JComboBox cmbGroups = new JComboBox();
				cmbGroups.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cmbGroups.setModel(moveGroupModel);
				cmbGroups.setBounds(442, 118, 89, 23);
				contentPane.add(cmbGroups);

				btnMove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final String selectedGroup = groupList
								.getSelectedValue();
						final String selectedStudent = lstMembers
								.getSelectedValue();
						if (selectedStudent == null) {
						} else {

							final String targetGroup = (String) cmbGroups
									.getSelectedItem();
							Group fromGroup = cont
									.getGroupByName(selectedGroup);
							Group toGroup = cont.getGroupByName(targetGroup);
							cont.removeStudent(
									cont.getStudentByName(selectedStudent),
									fromGroup);
							if (!targetGroup.equals("Unassigned")) {
								cont.addStudent(
										cont.getStudentByName(selectedStudent),
										toGroup);
							}

							ArrayList<Student> students = new ArrayList<Student>();

							students = (ArrayList<Student>) fromGroup
									.getGroupMembers();

							for (Student student : students) {
								System.out.println(student.getName());
								lmModel.addElement(student.getName());
							}
						}
					}
				});

				contentPane.add(btnMove);

				JLabel groupsLabel = new JLabel("Group List:");
				groupsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				groupsLabel.setBounds(10, 60, 109, 14);
				contentPane.add(groupsLabel);

				JLabel membersLabel = new JLabel("Group Members:");
				membersLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				membersLabel.setBounds(176, 60, 117, 14);
				contentPane.add(membersLabel);

				SwingUtilities.updateComponentTreeUI(contentPane);
			}

		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreate.setBounds(398, 10, 89, 23);
		contentPane.add(btnCreate);

	}
}