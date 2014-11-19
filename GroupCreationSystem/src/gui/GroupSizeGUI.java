/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Matt
 *
 */
public class GroupSizeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfGroupSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupSizeGUI frame = new GroupSizeGUI();
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
	public GroupSizeGUI() {
		setTitle("Enter Group Size");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGroupSize = new JLabel("Size of each group:");
		lblGroupSize.setBounds(83, 0, 93, 36);
		contentPane.add(lblGroupSize);
		
		tfGroupSize = new JTextField();
		tfGroupSize.setBounds(83, 44, 93, 20);
		contentPane.add(tfGroupSize);
		tfGroupSize.setColumns(10);
	}
}
