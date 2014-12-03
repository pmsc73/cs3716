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
	private static final int WINDOW_WIDTH = 460;
	private static final int WINDOW_HEIGHT = 360;
	private static GroupProjectGUI projGui;
	private Controller controller;

	public View() {
		final Controller controller = new Controller();
		//projGui = new GroupProjectGUI(view.controller);
		//projGui.setVisible(true);

		setTitle("Group Project Grouping System");
		setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		setVisible(true);
		final JPanel panels = new JPanel();
		panels.setBounds(0,0,640,480);
		final CardLayout frameCards = new CardLayout();
		panels.setLayout(frameCards);
		InitPanel init = new InitPanel(controller);
		final PreferencePanel pref = new PreferencePanel(controller);
		final MovePanel move = new MovePanel(controller);
		final ShowGroupsPanel show = new ShowGroupsPanel(controller);
		panels.add(init,"init");
		panels.add(pref,"pref");
		panels.add(move, "move");
		panels.add(show, "show");
		add(panels);
		
		init.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.finalizeParameters();
				setTitle("Group Creation System - "+controller.getCourseNumber());
				if(!controller.getSkillBased()) {
					pref.start();
					frameCards.show(panels,"pref");
				}
				else {
					controller.generateGroups();
					move.start();
					frameCards.show(panels,"move");
				}
			}
		});
		pref.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.generateGroups();
				move.start();
				frameCards.show(panels,"move");
			}
		});
		move.addNextListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show.start();
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
