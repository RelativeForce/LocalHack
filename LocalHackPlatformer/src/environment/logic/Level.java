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
		StartX = 0;
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

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		levelloader.getLevelDetails(currentLevel);

		constructs.addAll(levelloader.getComponents(currentLevel));
		constructs.addAll(levelloader.getEnemies(currentLevel));
		constructs.addAll(levelloader.getObjectives(currentLevel));

	}

	/**
	 * Retrieves the element from a collection of type <code>Entity</code> that
	 * a specified <code>Entity</code> will collide with on its path to a final
	 * position.
	 * 
	 * If entity does not collide with any elements in entities the function
	 * will return </code>null</code>.
	 * 
	 * Otherwise the function will return the <strong>first</strong> element in
	 * the collection that the specified <code>Entity</code> collides with.
	 * 
	 * @param entity
	 *            A <code>Entity</code> that will collide with an element from
	 *            entities .
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position that
	 *            entity will have.
	 * @param entities
	 *            The collection of entities that entity may collide with.
	 * @return The <strong>first</strong> element from a collection of type
	 *         <code>Entity</code> that the specified specified
	 *         <code>Entity</code> collided with, if any.
	 */
	public Construct getLevelCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Terrain) {

				if (!(element.equals(otherConstruct))) {

					if (Entity.detectHit(otherConstruct.getSprite().getEntity(), finalPosition,
							element.getSprite().getEntity())) {

						return element;

					}
				}
			}

		}
		return null;
	}

	public Construct checkForEnemyCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Enemy) {

				if (!(element.equals(otherConstruct))) {
					if (Entity.detectHit(otherConstruct.getSprite().getEntity(), finalPosition,
							element.getSprite().getEntity())) {

						return element;

					}

				}
			}

		}
		return null;
	}

	public Construct checkForObjectiveCollision(Construct otherConstruct, Point finalPosition) {

		for (Construct element : constructs) {

			if (element instanceof Objective) {

				if (!(element.equals(otherConstruct))) {
					if (Entity.detectHit(otherConstruct.getSprite().getEntity(), finalPosition,
							element.getSprite().getEntity())) {

						return element;

					}

				}
			}

		}
		return null;
	}

}
