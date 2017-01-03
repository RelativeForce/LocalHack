package environment;

import java.util.Scanner;

import entities.*;
import logic.Level;
import logic.Point;
import logic.enemy.Enemy;
import logic.enemy.Grunt;
import logic.objective.LevelChange;
import logic.objective.Objective;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/**
 * Loads the level from system memory.
 * 
 * @author Joshua_Eddy
 * 
 */
public class LevelLoader {

	private File[] directory;

	/**
	 * Constructs a new LevelLoader object.
	 * 
	 * @param directory
	 *            The path of the parent directory which contains the levels
	 */
	public LevelLoader(String directory) {
		try {
			File folder = new File(directory);
			this.directory = folder.listFiles();
		} catch (Exception e) {
			System.out.println("Failed to load directory");
		}
	}

	/**
	 * Reads the details of the current level from the level file.
	 * @param levelNum The number of the level to be loaded.
	 */
	public void getLevelDetails(int levelNum) {

		Scanner scnr = getFile("LH_Level" + levelNum);

		if (scnr == null) {
			System.out.println("Failed to find level" + levelNum);
			return;
		}

		while (scnr.hasNextLine()) {

			String line = scnr.nextLine();
			String[] details = line.split(",");

			String type = details[0];

			if (type.equals("levelDetails")) {
				initialiseLevel(details, 1);
			}

		}

	}

	/**
	 * Returns an ArrayList of all the Objectives read from the level file.
	 * 
	 * @param levelNum
	 *            The number of the level to be loaded.
	 * @return ArrayList of entities that have been read from the level file.
	 */
	public ArrayList<Entity> getComponents(int levelNum) {

		ArrayList<Entity> entities = new ArrayList<Entity>();

		Scanner scnr = getFile("LH_Level" + levelNum);

		if (scnr == null) {
			System.out.println("Failed to find level" + levelNum);
			return null;
		}

		while (scnr.hasNextLine()) {

			String line = scnr.nextLine();
			String[] details = line.split(",");

			String type = details[0];
			String detail1 = details[1];

			if (type.equals("component")) {
				if (detail1.equals("rectangle")) {
					entities.add(addRectangle(details, 2));
				}
				if (detail1.equals("floor")) {
					entities.add(addFloor(details, 2));
				}
			}

		}
		return entities;
	}

	/**
	 * Returns an ArrayList of all the Objectives read from the level file.
	 * 
	 * @param levelNum
	 *            The number of the level to be loaded.
	 * @return An ArrayList of all the objectives in the level file.
	 */
	public ArrayList<Objective> getObjectives(int levelNum) {

		ArrayList<Objective> objectives = new ArrayList<Objective>();

		Scanner scnr = getFile("LH_Level" + levelNum);

		if (scnr == null) {
			System.out.println("Failed to find level" + levelNum);
			return null;
		}

		while (scnr.hasNextLine()) {

			String line = scnr.nextLine();
			String[] details = line.split(",");

			String type = details[0];
			String detail1 = details[1];

			if (type.equals("objective")) {
				if (detail1.equals("door")) {
					objectives.add(addDoor(details, 2));
				}
			}

		}

		return objectives;
	}

	/**
	 * Returns an ArrayList of all the Enemies read from the level file.
	 * 
	 * @param levelNum
	 *            The number of the level to be loaded.
	 * @return An ArrayList of all the enemies in the level file.
	 */
	public ArrayList<Enemy> getEnemies(int levelNum) {

		ArrayList<Enemy> enemies = new ArrayList<Enemy>();

		Scanner scnr = getFile("LH_Level" + levelNum);

		if (scnr == null) {
			System.out.println("Failed to find level" + levelNum);
			return null;
		}

		while (scnr.hasNextLine()) {

			String line = scnr.nextLine();
			String[] details = line.split(",");

			String type = details[0];
			String detail1 = details[1];

			if (type.equals("enemy")) {
				if (detail1.equals("grunt")) {
					enemies.add(addGrunt(details, 2));
				}
			}

		}

		return enemies;

	}

	private Objective addDoor(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color color = getColor(details[firstDetail + 4]);
		int levelLink = Integer.parseInt(details[firstDetail + 5]);

		Door door = new Door(x, y, width, height, color.getRGB());
		LevelChange levelChange = new LevelChange(door, levelLink);

		return levelChange;
	}

	private Enemy addGrunt(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		Point initial = new Point(x,y);
		Grunt grunt = new Grunt(initial);
		
		return grunt;
	}

	private Entity addRectangle(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color color = getColor(details[firstDetail + 4]);

		Rectangle rect = new Rectangle(x, y, width, height, color.getRGB());
		return rect;
	}

	private Entity addFloor(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color borderColor = getColor(details[firstDetail + 4]);
		Color boxColor = getColor(details[firstDetail + 5]);
		int boxWidth = Integer.parseInt(details[firstDetail + 6]);
		int boxHeight = Integer.parseInt(details[firstDetail + 7]);

		Floor floor = new Floor(x, y, width, height, borderColor.getRGB(), boxWidth, boxHeight, boxColor.getRGB());

		return floor;
	}

	private Color getColor(String colorStr) {

		Color color;
		switch (colorStr) {
		case "yellow":
			color = Color.yellow;
			break;
		case "green":
			color = Color.green;
			break;
		case "red":
			color = Color.red;
			break;
		case "blue":
			color = Color.blue;
			break;
		case "orange":
			color = Color.orange;
			break;
		case "brown":
			color = Color.decode("#F4A460");
			break;
		default:
			color = Color.black;
			break;

		}
		return color;

	}

	private Scanner getFile(String fileName) {

		Scanner scnr = null;
		for (File level : directory) {
			if (level.getPath().contains(fileName)) {

				try {
					scnr = new Scanner(level);
				} catch (Exception e) {

				}
			}
		}
		return scnr;

	}
	
	private void initialiseLevel(String[] details, int firstDetail) {
		Level.Length = Integer.parseInt(details[firstDetail]);
		Point start = new Point(Integer.parseInt(details[firstDetail + 1]), Integer.parseInt(details[firstDetail + 2]));
		Level.StartPosition = start;
	}
}
