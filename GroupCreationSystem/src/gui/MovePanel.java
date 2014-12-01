/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * @author Matt
 *
 */
public class MovePanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovePanel frame = new MovePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MovePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlGroups = new JPanel();
		pnlGroups.setBounds(10, 76, 92, 201);
		contentPane.add(pnlGroups);
		pnlGroups.setLayout(null);
		
		JScrollPane scrlGroups = new JScrollPane();
		scrlGroups.setBounds(0, 0, 92, 201);
		pnlGroups.add(scrlGroups);
		
		JList lstGroups = new JList();
		scrlGroups.setViewportView(lstGroups);
		lstGroups.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblGroups = new JLabel("Groups");
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGroups.setBounds(10, 51, 46, 14);
		contentPane.add(lblGroups);
		
		JPanel pnlStudents = new JPanel();
		pnlStudents.setBounds(144, 76, 256, 114);
		contentPane.add(pnlStudents);
		pnlStudents.setLayout(null);
		
		JScrollPane scrlStudents = new JScrollPane();
		scrlStudents.setBounds(0, 0, 256, 114);
		pnlStudents.add(scrlStudents);
		
		JList lstStudents = new JList();
		scrlStudents.setViewportView(lstStudents);
		
		JLabel lblCourse = new JLabel("COURSE NAME");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourse.setBounds(144, 11, 105, 14);
		contentPane.add(lblCourse);
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStudents.setBounds(144, 51, 59, 14);
		contentPane.add(lblStudents);
		
		JLabel lblMoveTo = new JLabel("Move to:");
		lblMoveTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMoveTo.setBounds(144, 201, 59, 14);
		contentPane.add(lblMoveTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(144, 226, 92, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("MOVE");
		btnNewButton.setBounds(144, 254, 72, 23);
		contentPane.add(btnNewButton);
		
		JButton btnFinalize = new JButton("FINALIZE");
		btnFinalize.setBounds(324, 254, 89, 23);
		contentPane.add(btnFinalize);
	}
}
