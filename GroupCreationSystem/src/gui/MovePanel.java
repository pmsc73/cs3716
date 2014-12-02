/**
 * 
 */
package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utility.Controller;
import utility.Group;
import utility.Student;

/**
 * @author Matt
 *
 */
public class MovePanel extends JPanel {
	private Controller cont;

	/**
	 * Create the panel.
	 */
	public MovePanel(Controller cont) {
		this.cont = cont;

		setBounds(100, 100, 439, 326);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		// Panel that holds the group list
		JPanel pnlGroups = new JPanel();
		pnlGroups.setBounds(10, 76, 92, 201);
		this.add(pnlGroups);
		pnlGroups.setLayout(null);

		// Scrollpane for group list
		JScrollPane scrlGroups = new JScrollPane();
		scrlGroups.setBounds(0, 0, 92, 201);
		pnlGroups.add(scrlGroups);

		// Model for group list
		DefaultListModel<String> groupModel = new DefaultListModel<String>();

		final Collection<Group> groups = this.cont.getGroups();
		// list of all groups from controller

		// For debug purposes
		for (Group group : groups) {
			System.out.println("******#*#*#*#*#*#********");
			group.printGroup();
			groupModel.addElement(group.getName());
			// the model gets each group name added
		}
		groupModel.addElement("Unassigned");

		// Group list
		JList<String> lstGroups = new JList<>();
		scrlGroups.setViewportView(lstGroups);
		lstGroups.setModel(groupModel);

		JLabel lblGroups = new JLabel("Groups");
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGroups.setBounds(10, 51, 46, 14);
		this.add(lblGroups);

		// Panel for group memebers (students)
		JPanel pnlStudents = new JPanel();
		pnlStudents.setBounds(144, 76, 256, 114);
		this.add(pnlStudents);
		pnlStudents.setLayout(null);

		JScrollPane scrlStudents = new JScrollPane();
		scrlStudents.setBounds(0, 0, 256, 114);
		pnlStudents.add(scrlStudents);

		final DefaultListModel<String> lmModel = new DefaultListModel<String>();
		// lmModel is like groupModel but for the GROUP MEMBER LIST

		// Group member list
		JList<String> lstMembers = new JList<>(lmModel);

		// debugging
		for (Group g : groups) {
			g.printGroup();
			System.out.println("%%%%%%........%%%%%");
		}

		lstGroups.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				for (Group group : groups) {
					group.printGroup(); // for debugging
				}
				int i = 0;
				lmModel.clear(); // make sure lmModel is empty to begin
				// handles getting the unassigned students
				if (lstGroups.getSelectedValue() == "Unassigned") {
					Collection<Student> unassignedCollection = cont
							.getUnassignedStudents();
					for (Student stud : unassignedCollection) {
						lmModel.addElement(stud.getName());
					}
				} else {
					for (i = 0; i < groups.size(); i++) {
						// debugging print
						System.out.println(((ArrayList<Group>) groups).get(i)
								.getName()
								+ " : "
								+ lstGroups.getSelectedValue());

						// tl;dr for this if: current selected group
						// name ==
						// group(i)
						if ((((ArrayList<Group>) groups).get(i).getName())
								.equals(lstGroups.getSelectedValue())) {
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

		scrlStudents.setViewportView(lstMembers);

		// label for the course
		JLabel lblCourse = new JLabel(this.cont.getCourseNumber().toUpperCase());
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourse.setBounds(144, 11, 105, 14);
		this.add(lblCourse);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStudents.setBounds(144, 51, 59, 14);
		this.add(lblStudents);

		JLabel lblMoveTo = new JLabel("Move student to:");
		lblMoveTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMoveTo.setBounds(144, 201, 105, 14);
		this.add(lblMoveTo);

		//model for group moving list
		//basically just the list of all groups
		DefaultComboBoxModel<String> moveGroupModel = new DefaultComboBoxModel<String>();

		ArrayList<Group> moveGroupList = (ArrayList<Group>) cont.getGroups();

		//populating combo box model
		for (int i = 0; i < moveGroupList.size(); i++) {
			moveGroupModel.addElement(moveGroupList.get(i).getName());
		}
		moveGroupModel.addElement("Unassigned");

		JComboBox<String> cmbMoveGroupList = new JComboBox<String>();
		cmbMoveGroupList.setBounds(144, 226, 92, 20);
		cmbMoveGroupList.setModel(moveGroupModel);
		this.add(cmbMoveGroupList);

		JButton btnMove = new JButton("MOVE");
		btnMove.setBounds(144, 254, 72, 23);
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String selectedGroup = lstGroups.getSelectedValue();
				final String selectedStudent = lstMembers.getSelectedValue();
				if (selectedStudent == null) {
				} else {

					//group to move student into
					final String targetGroup = (String) cmbMoveGroupList
							.getSelectedItem();
					//group moving student out of
					Group fromGroup = cont.getGroupByName(selectedGroup);
					Group toGroup = cont.getGroupByName(targetGroup);
					cont.removeStudent(cont.getStudentByName(selectedStudent),
							fromGroup);
					if (!targetGroup.equals("Unassigned")) {
						cont.addStudent(cont.getStudentByName(selectedStudent),
								toGroup);
					}

					ArrayList<Student> students = new ArrayList<Student>();

					students = (ArrayList<Student>) fromGroup.getGroupMembers();

					//generating new list of students
					for (Student student : students) {
						System.out.println(student.getName());
						lmModel.addElement(student.getName());
					}
				}
			}
		});
		this.add(btnMove);

		JButton btnFinalize = new JButton("FINALIZE");
		btnFinalize.setBounds(340, 292, 89, 23);
		this.add(btnFinalize);
	}
}
