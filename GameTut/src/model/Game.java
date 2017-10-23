/*  */
package model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/** Game creates the instance of a game and runs it. */
public class Game extends Canvas implements Runnable {
	
	//Static constants:
	
	/** Default serialization UID. */
	private static final long serialVersionUID = 1L;

	/** The width of the window. */
	private static final int WIDTH = 1000;
	
	/** The height of the window (12x9 aspect ratio). */
	private static final int HEIGHT = WIDTH/16*9;
	
	/** The name of the game. */
	private static final String NAME = "Game Tutorial";
	
	// Instance fields:
	
	/** Whether game is currently running or not. */
	private boolean myGameRunning = false;
	
	/** A thread of execution. */
	private Thread myThread;
	
	
	/** Constructor for Game object. */
	public Game() {
		//Passes dimensions and name to to Window to create the window.
		new Window(WIDTH, HEIGHT, NAME, this);
		start();
	}
	
	/** Creates a new thread that calls the Game object's run method. */
	private synchronized void start() {
		myGameRunning = true;
		myThread = new Thread(this);
		myThread.start();
	}
	
	/** Kills currently running thread. */
	private synchronized void stop() {
		myGameRunning = false;
		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** The actual game loop. */
	@Override
	public void run() {
		
		//Requests that this component gets the input focus.
		//Video used "this.requestFocus();" but according to documentation, that method is discouraged.
		//this.requestFocus();
		this.requestFocusInWindow();

		//The last time (in nanoseconds) that the game was updated.
		long lastTime = System.nanoTime();
		
		/* How many nanoseconds there are per desired update.
		 * (60 updates per second in this case)*/
		double nsPerTick = 1_000_000_000D/60D;
		
		//How many nanoseconds have gone by between updates.
		double delta = 0;
		
		//The last time (in milliseconds) that the game was updated.
		long timer = System.nanoTime();
		
		//Keeps track of FPS.
		int frames = 0;
		
		//The main game loop.
		while (myGameRunning) {
			//Gets currents time.
			long now = System.nanoTime();
			
			/* Takes time between now and last update, divides by nsPerTick
			 * to get the percentage of the way to an update interval you are, and adds
			 * that to delta.
			 * 
			 * i.e. if only 1,000,000ns have passed since lastTime was set,
			 * you'd divide by nsPerTick to get 0.06 or, simply put, since
			 * the last time this loop ran, it's only been 6% of the time
			 * required between update intervals.*/
			delta += (now-lastTime)/nsPerTick;
			
			//updates lastTime to the current time.
			lastTime = now;
			
			//Loop checks to see if enough time has passed since the last update.
			//Prevents game logic and variable from updating to quickly.
			while (delta >= 1) {				
				//update game logic. 
				tick();				
				//Reset delta.
				delta--;
			}						
			//Update graphics on screen.
			render();
			//Increase frame counter.
			frames++;
			
			//Resets frame counter every second.
			if (System.nanoTime() - timer > 1_000_000_000) {
				System.out.println("FPS: " + frames);
				//Reset timer to current time.
				timer += 1_000_000_000;				
				//Reset frame counter.
				frames = 0;
			}
		}
		stop();
	}
	
	/** Updates logic of the game (i.e. variables).*/
	public void tick() {
		
	}
	/** Draws everything in the game. */
	public void render() {
		
		//Gets BufferStrategy currently being used by the game.
		BufferStrategy bs = this.getBufferStrategy();
		
		//Queues up to 3 frames so they don't have to be drawn in real time.
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//Creates graphics context for the drawing buffer.
		Graphics g = bs.getDrawGraphics();
		
		///////Stuff that actually gets drawn to frame.////////
		
		//Make the whole window red.
		g.setColor(Color.RED);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		////////////////////////////////////////////////////////
		
		//Disposes of graphics context and releases resources being used.
		g.dispose();
		
		//Makes next available buffer visible.
		bs.show();
	}
	
	/** Main method that creates initial instance of Game object. */
	public static void main(String[] args) {
		new Game();
	}
}
