package Logic;

import Directions.*;
import Graphics.*;
import entities.Entity;

public class HitDetection {

	public static boolean hitDetection(Entity object1, Entity nextObejct1, Entity object2) {

		return false;
	}

	private static Integer[][] getXPreimiter(Entity object) {
		
		Integer[][] perimeter = new Integer[object.getGraphicalObject().height][2];
		boolean isLeftPerimeter = false;
		
		for (int y = 0; y < object.getGraphicalObject().height; y++) {
			for (int x = 0; x < object.getGraphicalObject().width; x++) {

				if (isLeftPerimeter && x != 0) {
					if (object.getGraphicalObject().getPixels()[x - 1][y] == null) {
						perimeter[y][0] = x;
						isLeftPerimeter = !isLeftPerimeter;
					}
				} else if(!isLeftPerimeter && x < object.getGraphicalObject().width){
					if (object.getGraphicalObject().getPixels()[x + 1][y] == null) {
						perimeter[y][1] = x;
						isLeftPerimeter = !isLeftPerimeter;
					}
				}

			}
		}

		return perimeter;
	}

	/*
	 * private static int checkY(int initalY, int finalY, HitBox object1, Entity
	 * object2){
	 * 
	 * boolean condition3; boolean condition4;
	 * 
	 * 
	 * for (int y = initalY; y <= finalY; y++) {
	 * 
	 * condition3 = y <= object2.y + object2.getHeight(); condition4 = y +
	 * object1.getHeight() >= object2.y;
	 * 
	 * if (condition3 && condition4) {
	 * 
	 * return y; }
	 * 
	 * } return -1; }
	 */

	/*
	 * private static int checkX(int initalX, int finalX, Rectangle object1,
	 * Rectangle object2){
	 * 
	 * boolean condition1; boolean condition2;
	 * 
	 * for (int x = initalX; x <= finalX; x++) {
	 * 
	 * condition1 = x + object1.getWidth() >= object2.x; condition2 = x <=
	 * object2.x + object2.getWidth();
	 * 
	 * if (condition1 && condition2) { return x; }
	 * 
	 * }
	 * 
	 * return -1; }
	 * 
	 */
	/*
	 * private static int check(int[][] grid){ for (int i = 0; i < grid.length;
	 * i++){ for (int j = 0; i < grid[0].length; i++){ boolean perimeter =
	 * false; if (grid[i][j] != 0) { if (i == 0) { perimeter = true; } else if
	 * (j == 0) { perimeter = true; } else if (i == (grid.length - 1)) {
	 * perimeter = true; } else if (j == grid[0].length - 1) { perimeter = true;
	 * } } } } return 0; }
	 */
}
