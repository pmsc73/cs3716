/**
 * 
 */
package gui;

import utility.Controller;

/**
 * @author Philip
 *
 */
public class View {
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
