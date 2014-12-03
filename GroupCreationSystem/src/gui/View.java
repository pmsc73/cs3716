/**
 * 
 */
package gui;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.CardLayout;
import utility.Controller;

/**
 * @author Philip
 *
 */
public class View extends JFrame{
	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;
	private static GroupProjectGUI projGui;
	private Controller controller;

	public View() {
		Controller controller = new Controller();
		controller.setCourseNumber("cs3716");
		controller.setGroupSize(3);
		controller.finalizeParameters();
		controller.generateGroups();
		//projGui = new GroupProjectGUI(view.controller);
		//projGui.setVisible(true);

		
		setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		setVisible(true);
		JPanel panels = new JPanel();
		panels.setBounds(0,0,640,480);
		CardLayout frameCards = new CardLayout();
		panels.setLayout(frameCards);
		InitPanel init = new InitPanel(controller);
		PreferencePanel pref = new PreferencePanel(controller);
		MovePanel move = new MovePanel(controller);
		ShowGroupsPanel show = new ShowGroupsPanel(controller);
		panels.add(init,"init");
		panels.add(pref,"pref");
		panels.add(move, "move");
		panels.add(show, "show");
		add(panels);
		
		init.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controller.getSkillBased()) {
					frameCards.show(panels,"pref");
				}
				else {
					frameCards.show(panels,"move");
				}
			}
		});
		pref.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCards.show(panels,"move");
			}
		});
		move.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCards.show(panels,"show");
			}
		});
	}
	public static void main(String[] args) {
		View view = new View();
		view.setDefaultCloseOperation(EXIT_ON_CLOSE);
		view.setVisible(true);
	}
	
}
