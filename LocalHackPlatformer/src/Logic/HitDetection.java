package Logic;

import java.awt.Rectangle;
import Directions.*;
import Graphics.*;

public class HitDetection {
	

	public static boolean hitDetection(Rectangle object1, Rectangle nextObject1, Rectangle object2) {

		int initalX;
		int finalX;
		int initalY;
		int finalY;

		if (object1.x > nextObject1.x) {
			initalX = nextObject1.x;
			finalX = object1.x;
		} else {
			initalX = object1.x;
			finalX = nextObject1.x;
		}
		if (object1.y > nextObject1.y) {
			initalY = nextObject1.y;
			finalY = object1.y;
		} else {
			initalY = object1.y;
			finalY = nextObject1.y;
		}


		if (checkX(initalX, finalX, object1, object2) != -1 && checkY(initalY, finalY, object1, object2) != -1) {
			return true;
		}

		return false;
	}

	public static Direction getDirectionOfRebound(Rectangle object1, Rectangle nextObject1, Rectangle object2) {

		int initalX;
		int finalX;
		int initalY;
		int finalY;
		int x;
		int y;

		if (object1.x > nextObject1.x) {
			initalX = nextObject1.x;
			finalX = object1.x;
			x = checkX(initalX, finalX, object1, object2) + 1;
		} else {
			initalX = object1.x;
			finalX = nextObject1.x;
			x = checkX(initalX, finalX, object1, object2) - 1;
		}
		if (object1.y > nextObject1.y) {
			initalY = nextObject1.y;
			finalY = object1.y;
			y = checkY(initalY, finalY, object1, object2) + 1;
		} else {
			initalY = object1.y;
			finalY = nextObject1.y;
			y = checkY(initalY, finalY, object1, object2) - 1;
		}
		
		boolean toLeft = x + object1.getWidth() < object2.x;
		boolean toRight = x > object2.x + object2.getWidth();
		//boolean withinY = y <= object2.y + object2.getHeight()
		//		&& y + object1.getHeight() >= object2.y;

		boolean above = y - object1.getHeight() < object2.y;
		boolean bellow = y > object2.y + object2.getHeight();
		//boolean withinX = x <= object2.x + object2.getWidth()
		//		&& x + object1.getWidth() >= object2.x;

		if (toLeft) {
			return new Left();
		} else if (toRight) {
			return new Right();
		}else if (above) {
			return new Up();
		} else if (bellow) {
			return new Down();
		}
		
		return new UpRight();
		
		/*
		if (toLeft) {
			if (above) {
				return new DownRight();
			} else if (bellow) {
				return new UpRight();
			}
		} else if (toRight) {
			if (above) {
				return new DownLeft();
			} else if (bellow) {
				return new UpLeft();
			}
		}
		 */
		//return null;

	}

	private static int checkY(int initalY, int finalY, Rectangle object1, Rectangle object2){
		
		boolean condition3;
		boolean condition4;
		
		for (int y = initalY; y <= finalY; y++) {

			condition3 = y <= object2.y + object2.getHeight();
			condition4 = y + object1.getHeight() >= object2.y;

			if (condition3 && condition4) {
				
				return y;
			}

		}
		return -1;
	}

	private static int checkX(int initalX, int finalX, Rectangle object1, Rectangle object2){
		
		boolean condition1;
		boolean condition2;
		
		for (int x = initalX; x <= finalX; x++) {

			condition1 = x + object1.getWidth() >= object2.x;
			condition2 = x <= object2.x + object2.getWidth();

			if (condition1 && condition2) {
				return x;
			}

		}
		
		return -1;
	}
	/*
	private static int check(int[][] grid){
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; i < grid[0].length; i++){
				boolean perimeter = false;
				if (grid[i][j] != 0) {
					if (i == 0) {
						perimeter = true;
					}
					else if (j == 0) {
						perimeter = true;
					}
					else if (i == (grid.length - 1)) {
						perimeter = true;
					}
					else if (j == grid[0].length - 1) {
						perimeter = true;
					}
				}
			}
		}
		return 0;
	}
		*/
}
