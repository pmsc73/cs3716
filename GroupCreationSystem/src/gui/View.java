/**
 * 
 */
package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;

import utility.Controller;

/**
 * @author Philip
 *
 */
public class View {
	private static GroupSizeGUI sizeGui;
	private static GroupProjectGUI projGui;
	private Controller controller;
	public View() {
		controller = new Controller();
	}
	public static void main(String[] args) {
		View view = new View();
		projGui = new GroupProjectGUI(view.controller);
		projGui.setVisible(true);
		
	}

}
