package resources;

import java.util.Scanner;
import environment.logic.Level;
import environment.logic.Point;
import environment.logic.constructs.Construct;
import environment.logic.constructs.enemies.Arganok;
import environment.logic.constructs.enemies.Grunt;
import environment.logic.constructs.objectives.LevelChange;
import environment.logic.constructs.terrains.DefaultTerrain;
import environment.logic.entities.Entity;
import environment.logic.sprites.Sprite;

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
	 * @param directoryPath
	 *            The path of the parent directory which contains the levels
	 */
	public LevelLoader(String directoryPath) {
		directory = TextFileReader.getDirectory(directoryPath);
	}

	/**
	 * Reads the details of the current level from the level file.
	 * 
	 * @param levelNum
	 *            The number of the level to be loaded.
	 */
	public void getLevelDetails(int levelNum) {

		Scanner scnr = TextFileReader.getScannedFile("LH_Level" + levelNum, directory);

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
	public ArrayList<Construct> getComponents(int levelNum) {

		ArrayList<Construct> entities = new ArrayList<Construct>();

		Scanner scnr = TextFileReader.getScannedFile("LH_Level" + levelNum, directory);

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
	public ArrayList<Construct> getObjectives(int levelNum) {

		ArrayList<Construct> objectives = new ArrayList<Construct>();

		Scanner scnr = TextFileReader.getScannedFile("LH_Level" + levelNum, directory);

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
	public ArrayList<Construct> getEnemies(int levelNum) {

		ArrayList<Construct> enemies = new ArrayList<Construct>();

		Scanner scnr = TextFileReader.getScannedFile("LH_Level" + levelNum, directory);

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
				else if(detail1.equals("arganok")){
					enemies.add(addArganok(details, 2));
				}
			}

		}

		return enemies;

	}

	private Construct addArganok(String[] details, int firstDetail) {
		
		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int trackingRadius = Integer.parseInt(details[firstDetail + 2]);
		Point inital = new Point(x, y);
		
		return new Arganok(inital, trackingRadius);
	}

	private Construct addDoor(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color color = getColor(details[firstDetail + 4]);
		int levelLink = Integer.parseInt(details[firstDetail + 5]);

		Entity door = Entity.newDoor(x, y, width, height, color.getRGB());
		LevelChange levelChange = new LevelChange(x, y, door, levelLink);

		return levelChange;
	}

	private Construct addGrunt(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		Point initial = new Point(x, y);
		Grunt grunt = new Grunt(initial);

		return grunt;
	}

	private Construct addRectangle(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color color = getColor(details[firstDetail + 4]);

		Construct rect = new DefaultTerrain(x, y, new Sprite(Entity.newRectangle(x, y, width, height, color.getRGB()), x, y));
		return rect;
	}

	private Construct addFloor(String[] details, int firstDetail) {

		int x = Integer.parseInt(details[firstDetail]);
		int y = Integer.parseInt(details[firstDetail + 1]);
		int width = Integer.parseInt(details[firstDetail + 2]);
		int height = Integer.parseInt(details[firstDetail + 3]);
		Color borderColor = getColor(details[firstDetail + 4]);
		Color boxColor = getColor(details[firstDetail + 5]);
		int boxWidth = Integer.parseInt(details[firstDetail + 6]);
		int boxHeight = Integer.parseInt(details[firstDetail + 7]);

		Construct floor = new DefaultTerrain(x, y, new Sprite(
				Entity.newFloor(x, y, width, height, borderColor.getRGB(), boxWidth, boxHeight, boxColor.getRGB()), x, y));
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

	private void initialiseLevel(String[] details, int firstDetail) {
		Level.Length = Integer.parseInt(details[firstDetail]);
		Point start = new Point(Integer.parseInt(details[firstDetail + 1]), Integer.parseInt(details[firstDetail + 2]));
		Level.StartPosition = start;
	}
}
