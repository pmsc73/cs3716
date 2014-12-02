/**
 * 
 */
package gui;

import javax.swing.JPanel;
import utility.Controller;

/**
 * @author Philip
 *
 */
public class InitPanel extends JPanel {
	private boolean done=false;
	private Controller controller;
	public InitPanel(Controller controller) {
		this.controller = controller;
	}
	public boolean isDone() {
		return done;
	}
}
