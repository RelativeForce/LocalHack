package environment;

import environment.logic.Point;
import environment.logic.SpriteDetails;

/**
 * Stores the various constant values that are used throughout the Game modules.
 * 
 * @author Joshua_Eddy
 *
 */
public class Constants {

	/**
	 * The value of gravity used in the game.
	 */
	public static final double GRAVITY = 0.4;

	/**
	 * The value of friction in the horizontal direction in the game.
	 */
	public static final int FRICTION = 5;

	/**
	 * The height the player jumps in the game.
	 */
	public static final int JUMP_HEIGHT = 18;

	/**
	 * The width of the window.
	 */
	public static final int WINDOW_WIDTH = 1000;

	/**
	 * The height of the window.
	 */
	public static final int WINDOW_HEIGHT = 500;

	/**
	 * The distance from each side of the screen that when the player passes
	 * will cause the screen to scroll.
	 */
	public static final int WINDOW_PADDING = 300;

	/**
	 * The distance the player moves when either of the right or left arrow keys
	 * is pressed.
	 */
	public static final int MOVE_DISTANCE = 4;

	/**
	 * The increment of the transition screen.
	 */
	public static final int TRANSITION_SCREEN_STEP = WINDOW_HEIGHT / 50;

	/**
	 * The default start position of an Entity if on is not read from the level
	 * file.
	 */
	public static final Point DEFAULT_POSITION = new Point(0, 0);

	/**
	 * The maximum number of keys that can be in the keyboard input queue at
	 * once.
	 */
	public static final int KEYBOARD_ROLLOVER = 5;

	/**
	 * The filename of the image containing the grunt sprite frames.
	 */
	public static final SpriteDetails GRUNT_SPRITE_DETAILS = new SpriteDetails("Grunt.png", 20, 25);;

	/**
	 * The filename of the image containing the player sprite frames.
	 */
	public static final SpriteDetails PLAYER_SPRITE_DETAILS = new SpriteDetails("Mario.png", 16, 32);

	/**
	 * The filename of the image containing the Arganok sprite frames.
	 */
	public static final SpriteDetails ARGANOK_SPRITE_DETAILS = new SpriteDetails("Arganok.png", 42, 42);

	/**
	 * In RGB the value is equivalent to 0xFF00FF.
	 */
	public static final Integer TRANSPARENT_COLOR = -0xFF01;

}
