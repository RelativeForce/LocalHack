package environment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import environment.graphics.*;
import environment.logic.*;
import environment.logic.constructs.Construct;
import environment.logic.constructs.TransitionScreen;
import environment.logic.constructs.enemies.Enemy;
import environment.logic.constructs.players.DefaultPlayer;
import environment.logic.constructs.players.Player;
import environment.logic.entities.*;

/**
 * Initialises game play state.
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

	// Private
	private static ThreadHandler threadHandler;
	private static Display display;
	private static Screen screen;

	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            Unused.
	 */
	public static void main(String[] args) {

		display = new Display(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, "Platformer");
		screen = new Screen(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		level = new Level();
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

		level.loadLevel();
		player = new DefaultPlayer(Level.StartPosition.x, Level.StartPosition.y, 20, 20);

	}

	protected static void playerThread() {

		if (!transitionScreen.isActive) {
			level.refreshLevel();
			display.handleKeys();
			player.gravity();
			player.checkForDeath();
			player.checkObjectives();
		}

	}

	protected static void displayThread() {

		ArrayList<Entity> entities = new ArrayList<Entity>();

		entities.addAll(level.getEntities());
		entities.add(player.getEntity());

		for (Entity entity : entities) {
			screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
		}

		if (transitionScreen.isActive) {
			if (!transitionScreen.isFullScreen && transitionScreen.willIncrement) {
				transitionScreen.increment();
			} else if (!transitionScreen.willIncrement) {
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

	protected static void enemyThread() {
		if (!transitionScreen.isActive) {
			moveEnemies();
		}
	}

	private static void moveEnemies() {
		LinkedList<Construct> moved = new LinkedList<Construct>();

		for (Construct construct : level.getConstructs()) {
			if (construct instanceof Enemy && !(moved.contains(construct))) {
				((Enemy) construct).act();
				moved.add(construct);
			}
		}
	}
}
