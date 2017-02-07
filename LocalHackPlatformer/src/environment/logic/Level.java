package environment.logic;

import java.io.File;
import java.util.ArrayList;
import environment.Constants;
import environment.logic.constructs.Construct;
import environment.logic.constructs.enemies.Enemy;
import environment.logic.constructs.objectives.Objective;
import environment.logic.constructs.terrains.Terrain;
import environment.logic.entities.Entity;
import resources.LevelLoader;

/**
 * Stores all the objects and details associated with a level.
 * 
 * @author Joshua_Eddy
 *
 */
public class Level {

	/**
	 * Stores the maximum length of the current level.
	 */
	public static int Length;

	/**
	 * Stores the X position of the start of the current level in relation to
	 * the left side of the screen.
	 */
	public static int StartX;

	/**
	 * Stores the position the player will start at when the level is played.
	 */
	public static Point StartPosition;

	/**
	 * Stores the current level number.
	 */
	public static int currentLevel = 1;

	private ArrayList<Construct> constructs;

	/**
	 * Constructs a new Level object.
	 * 
	 * @param levelNumber
	 *            The number of the level that is to be loaded from computer
	 *            memory.
	 */
	public Level() {

		constructs = new ArrayList<Construct>();
		StartPosition = Constants.DEFAULT_POSITION;
		loadLevel();

	}

	/**
	 * Moves the level by a specified to give the illusion of the level
	 * scrolling.
	 * 
	 * @param changeInX
	 *            The amount the level should scroll in pixels.
	 */
	public void moveLevel(int changeInX) {

		for (Construct construct : constructs) {
			construct.move(changeInX, 0);
		}
		StartX += changeInX;

	}

	public void changeLevel(int newLevelNumber) {
		currentLevel = newLevelNumber;
		loadLevel();
	}

	/**
	 * Returns an ArrayList of all the Entities in the Level.
	 * 
	 * @return ArrayList of all the Entities in the Level.
	 */
	public ArrayList<Entity> getEntities() {

		ArrayList<Entity> entities = new ArrayList<Entity>();

		for (int i = 0; i < constructs.size(); i++) {
			entities.add(constructs.get(i).getSprite().getEntity());
		}

		return entities;
	}

	/**
	 * Returns an ArrayList of all the Enemy Entities in the Level.
	 * 
	 * @return ArrayList of all the Enemy Entities in the Level.
	 */
	public ArrayList<Construct> getConstructs() {
		return constructs;
	}

	public void loadLevel() {

		constructs.clear();
		StartX = 0;
		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		levelloader.getLevelDetails(currentLevel);

		constructs.addAll(levelloader.getComponents(currentLevel));
		constructs.addAll(levelloader.getObjectives(currentLevel));
		constructs.addAll(levelloader.getEnemies(currentLevel));
	}

	/**
	 * Checks if a specified <code>Construct</code> has collided with another
	 * <code>Construct</code> in the level of type
	 * <code>Terrain</code>. If the <code>Construct</code> does not collide with
	 * any elements of the terrain the function will return </code>null</code>.
	 * Otherwise the function will return the <strong>first</strong> element in
	 * the terrain that the specified <code>Construct</code> collides with.
	 * 
	 * @param otherConstruct
	 *            A <code>Construct</code> that may collide with an element of
	 *            the terrain.
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position that
	 *            <code>Construct</code> will have.
	 * @return The <strong>first</strong> element from the terrain that the
	 *         specified specified <code>Construct</code> collided with, if any.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see environment.logic.constructs.terrains.Terrain
	 * @see environment.logic.Point
	 */
	public Construct getLevelCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Terrain) {

				if (checkHit(element, otherConstruct, finalPosition)) {

					return element;

				}
			}

		}
		return null;
	}

	/**
	 * Checks if a specified <code>Construct</code> has collided with another
	 * <code>Construct</code> in the level of type
	 * <code>Enemy</code>. If the <code>Construct</code> does not collide with
	 * any of the enemies in the level the function will return </code>null</code>.
	 * Otherwise the function will return the <strong>first</strong> enemy in
	 * the level that the specified <code>Construct</code> collides with.
	 * 
	 * @param otherConstruct
	 *            A <code>Construct</code> that may collide with a
	 *            <code>Enemy</code> in the level.
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position that
	 *            <code>Construct</code> will have.
	 * @return The <strong>first</strong> element from the set of enemies in the
	 *         level that the specified specified <code>Construct</code>
	 *         collided with, if any.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see environment.logic.constructs.enemies.Enemy
	 * @see environment.logic.Point
	 */
	public Construct checkForEnemyCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Enemy) {

				if (checkHit(element, otherConstruct, finalPosition)) {

					return element;
				}

			}

		}
		return null;
	}

	/**
	 * Checks if a specified <code>Construct</code> has collided with another
	 * <code>Construct</code> in the <code>Level</code> of type
	 * <code>Objective</code>. If the <code>Construct</code> does not collide with
	 * any of the objectives in the level the function will return </code>null</code>.
	 * Otherwise the function will return the <strong>first</strong> enemy in
	 * the level that the specified <code>Construct</code> collides with.
	 * 
	 * @param otherConstruct
	 *            A <code>Construct</code> that may collide with a
	 *            <code>Enemy</code> in the level.
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position that
	 *            <code>Construct</code> will have.
	 * @return The <strong>first</strong> element from the set of enemies in the
	 *         level that the specified specified <code>Construct</code>
	 *         collided with, if any.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see environment.logic.constructs.objectives.Objective
	 * @see environment.logic.Point
	 */
	public Construct checkForObjectiveCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Objective) {

				if (checkHit(element, otherConstruct, finalPosition)) {

					return element;

				}
			}

		}
		return null;
	}

	private boolean checkHit(Construct element, Construct otherConstruct, Point finalPosition) {

		if (!(element.equals(otherConstruct))) {
			if (Entity.detectHit(otherConstruct.getSprite().getEntity(), finalPosition,
					element.getSprite().getEntity())) {

				return true;

			}

		}
		return false;
	}
}
