/**
 * 
 */
package gui;
import javax.swing.*;
import java.awt.*;
import utility.Controller;

/**
 * @author Philip
 *
 */
public class View {
	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;
	private static GroupProjectGUI projGui;
	private Controller controller;

	public static void main(String[] args) {
		Controller controller = new Controller();
		//projGui = new GroupProjectGUI(view.controller);
		//projGui.setVisible(true);

		JFrame mainView = new JFrame();
		mainView.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		mainView.setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		InitPanel init = new InitPanel(controller);
		mainView.add(init);
		while(!init.isDone()) {
			// do nothing
		}
		mainView.remove(init);
		PreferencePanel pref = new PreferencePanel(controller);
		mainView.add(pref);
		while(!pref.isDone()) {
			//do nothing
		}
		mainView.remove(pref);
		MovePanel move = new MovePanel(controller);
		mainView.add(move);
		while(!move.isDone()) {
			// do nothing
		}
		mainView.remove(move);
		ShowGroupsPanel show = new ShowGroupsPanel();
		mainView.add(show);
	}

}
