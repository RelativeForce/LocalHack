package environment.logic.overlays;

import environment.Constants;
import environment.graphics.objects.GraphicalObject;
import environment.logic.Drawable;
import environment.logic.entities.Entity;

/**
 * The screen that scrolls across the screen.
 * 
 * @author Joshua_Eddy
 */
public class TransitionScreen implements Drawable{

	private Entity screen;
	private int height;
	private Integer color;

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
	 * 
	 * @param color
	 *            The colour of the transition screen.
	 */
	public TransitionScreen(Integer color) {

		isFullScreen = false;
		height = 0;
		this.color = color;
		screen = Entity.newRectangle(0, 0, Constants.WINDOW_WIDTH, height, color);
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

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = 0;
		eD[1] = 0;
		eD[2] = Constants.WINDOW_WIDTH;
		eD[3] = height;
		eD[4] = color;

		screen = Entity.newRectangle(0, 0, Constants.WINDOW_WIDTH, height, color);

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
		screen = Entity.newRectangle(0, Constants.WINDOW_HEIGHT - height, Constants.WINDOW_WIDTH, height, color);

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
	 * 
	 * @param color
	 *            The new colour of the TransitionScreen.
	 */
	public void setColor(Integer color) {
		
		this.color = color;
		screen = Entity.newRectangle(0, 0, Constants.WINDOW_WIDTH, height, color);

	}

	@Override
	public GraphicalObject getGraphicalObject() {
		return screen.getGraphicalObject();
	}

	@Override
	public int getX() {
		return screen.getX();
	}

	@Override
	public int getY() {
		return screen.getY();
	}
}
