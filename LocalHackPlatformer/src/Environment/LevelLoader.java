package Environment;

import java.util.Scanner;

import Logic.Level;
import entities.Entity;
import entities.Floor;
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

				try {
					scnr = new Scanner(level);
				} catch (Exception e) {

				}
			}
		}
		return scnr;

	}

	public ArrayList<Entity> getLevel(int levelNum, String entityType) {

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
			String shape = details[1];
			
			
			if(type.equals("levelDetails") && entityType.equals("levelDetails")){
				Level.Length = Integer.parseInt(details[1]);
			}
			else if (type.equals("component") && entityType.equals("component")) {
				if (shape.equals("rectangle")) {
					entities.add(addRectangle(details, 2));
				}
				if (shape.equals("floor")) {
					entities.add(addFloor(details, 2));
				}
			}else if(type.equals("enemy") && entityType.equals("enemy")){
				if (shape.equals("rectangle")) {
					entities.add(addRectangle(details, 2));
				}
			}
		}
		return entities;
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

	private Entity addFloor(String[] details, int firstDetail){
		
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
	
	private Color getColor(String colorStr){
		
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
	

}
