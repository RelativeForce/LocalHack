package environment;

import entities.*;
import graphics.*;
import logic.*;
import logic.enemy.Enemy;
import logic.player.Player;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Stores the Game logic and fundamental functions.
 * 
 * @author Joshua_Eddy
 */
public class Main {

	/**
	 * Stores the current Player object.
	 */
	public static Player player;

	/**
	 * Stores the current Level object.
	 */
	public static Level level;

	/**
	 * Stores the current DeathScreen object.
	 */
	public static TransitionScreen transitionScreen;
	
	/**
	 * The level that will be loaded upon Screen transition.
	 */
	public static int levelNumber;

	// Private
	private static ThreadHandler threadHandler;
	private static Display display;
	private static Screen screen;
	

	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            Starting string arguments.
	 */
	public static void main(String[] args) {

		display = new Display(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, "Platformer");
		screen = new Screen(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		levelNumber = 1;
		transitionScreen = new TransitionScreen(Color.RED.getRGB());
		start();

		// Thread initialisation
		threadHandler = new ThreadHandler();
		threadHandler.startThreads();
		
	}

	/**
	 * Starts the level and initialises the player.
	 * 
	 */
	public static void start() {
	
		level = new Level(levelNumber);
		player = new Player(Level.StartPosition.x, Level.StartPosition.y, 20, 20);
		
	}

	protected static void playerThread() {

		if(!transitionScreen.isActive){
			display.handleKeys();
			player.gravity();
			player.checkForDeath();
			player.checkObjectves();
		}

	}

	protected static void displayThread() {

		ArrayList<Entity> entities = new ArrayList<Entity>();

		entities.addAll(level.getAll());
		entities.add(player.getEntity());

		for (Entity entity : entities) {
			screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
		}

		if (transitionScreen.isActive) {
			if (!transitionScreen.isFullScreen && transitionScreen.willIncrement) {
				transitionScreen.increment();
			} else if(!transitionScreen.willIncrement) {
				transitionScreen.decrement();
			}
			
			if (transitionScreen.isFullScreen) {
				start();
			}

			screen.addGraphicalObject(transitionScreen.getEntity().getGraphicalObject(),
					transitionScreen.getEntity().getX(), transitionScreen.getEntity().getY());
		}

		display.render(screen);
		screen.clear();
	}

	protected static void enemyThread(){
		if(!transitionScreen.isActive){
			moveEnemies();
		}
		
	}
	
	private static void moveEnemies(){
		for(Enemy enemy : level.getEnemies()){
			enemy.move();
		}
	}
	
}
