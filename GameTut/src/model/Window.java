/* 
 * Wizard Game Tutorial
 * Started 10/22/2017
 */
package model;

import java.awt.Dimension;
import javax.swing.JFrame;

/** 
 * Creates the game window.
 * 
 * @author theNKetchum
 * @version 10/22/2017
 */
public class Window {
		
	/** 
	 * Constructor creates new window to display game object.
	 * 
	 * @param theWidth the width of the window.
	 * @param theHeight the height of the window.
	 * @param theTitle the title of the game, displayed at the top of the window.
	 * @param theGame the game object to create the window for. 
	 */
	public Window (int theWidth, int theHeight, String theTitle, Game theGame) {
		
		//Creates new JFrame object.
		JFrame myFrame = new JFrame(theTitle);
		
		//Sets the size of the canvas to a fixed size.
		myFrame.setPreferredSize(new Dimension(theWidth, theHeight));
		myFrame.setMaximumSize(new Dimension(theWidth, theHeight));
		myFrame.setMinimumSize(new Dimension(theWidth, theHeight));
	
		//Adds Game object to JFrame.
		myFrame.add(theGame);
		
		//Disables ability for frame to be resized.
		myFrame.setResizable(false);
		
		//Sets default close operation to System exit method.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Don't want frame set relative to anything, just centered.
		myFrame.setLocationRelativeTo(null);
		
		//Make the frame visible.
		myFrame.setVisible(true);
	}
}
