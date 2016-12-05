package Environment;

import java.util.Scanner;
import entities.Entity;
import entities.Rectangle;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Joshua_Eddy
 * 
 */

public class LevelLoader {

	private File[] directory;

	public LevelLoader(String directory) {
		try {
			File folder = new File(directory);
			this.directory = folder.listFiles();
		} catch (Exception e) {
			System.out.println("Failed to load directory");
		}
	}

	private Scanner getFile(String fileName) {

		Scanner scnr = null;
		for (File level : directory) {
			if (level.getPath().contains(fileName)) {
				scnr = new Scanner(level.getPath());
			}
		}
		return scnr;

	}

	public Entity[] getLevel(int levelNum) {

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

			if (type.equals("Rectangle")) {
				entities.add(addRectangle(details));
			}

		}

		return (Entity[]) entities.toArray();
	}

	private Entity addRectangle(String[] details) {

		int x = Integer.parseInt(details[1]);
		int y = Integer.parseInt(details[2]);
		int width = Integer.parseInt(details[3]);
		int height = Integer.parseInt(details[4]);

		Color color;
		switch (details[5]) {
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
		default:
			color = Color.black;
			break;

		}

		Rectangle rect = new Rectangle(x, y, width, height, color.getRGB());
		return rect;
	}

}
