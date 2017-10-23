/* */
package model;

import java.awt.Dimension;
import javax.swing.JFrame;

/** Creates the game window */
public class Window {
		
	/** Constructor creates new window to display game object. */
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
