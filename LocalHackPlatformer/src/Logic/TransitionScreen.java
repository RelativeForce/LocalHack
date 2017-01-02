package Logic;

import Environment.Constants;
import entities.Entity;
import entities.Rectangle;

/**
 * The screen that scrolls across the screen.
 * 
 * @author Joshua_Eddy
 */
public class TransitionScreen {

	private Rectangle screen;
	private int height;

	/**
	 * Whether the DeathScreen fills the window.
	 */
	public boolean isFullScreen;

	/**
	 * Denotes whether the TransitionScreen is active or not.
	 */
	public boolean isActive;
	
	/*
	 * Denotes whether the TransitionScreen will increment on the next Step.
	 */
	public boolean willIncrement;

	/**
	 * Constructs a new DeathScreen object.
	 * @param color The colour of the transition screen.
	 */
	public TransitionScreen(Integer color) {
		isFullScreen = false;
		height = 0;
		screen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, color);
		isActive = false;
		willIncrement = true;
	}

	/**
	 * Increments the screen in height.
	 */
	public void increment() {

		isActive = true;

		if (height == Constants.WINDOW_HEIGHT) {
			isFullScreen = true;
			willIncrement = false;
			return;
		}

		height += Constants.TRANSITION_SCREEN_STEP;
		screen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, screen.getColor());
	}

	/**
	 * Decrements the screen in height.
	 */
	public void decrement() {
		isFullScreen = false;
		if (height == 0) {
			isActive = false;
			willIncrement = true;
			return;
		}
		height -= Constants.TRANSITION_SCREEN_STEP;
		screen = new Rectangle(0, Constants.WINDOW_HEIGHT - height, Constants.WINDOW_WIDTH, height,
				screen.getColor());

	}

	/**
	 * Retrieves the Entity assigned to the DeathScreen.
	 * 
	 * @return The Entity assigned to the DeathScreen.
	 */
	public Entity getEntity() {
		return screen;
	}

	/**
	 * Changes the colour of the TransitionScreen.
	 * @param color The new colour of the TransitionScreen.
	 */
	public void setColor(Integer color){
		screen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, color);
	}
}
