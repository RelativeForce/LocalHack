package Logic;

import entities.Entity;

public class HitDetection {

	public static boolean detectHit(Entity obj1, Entity nextObj1, Entity obj2) {

		if (checkX(obj1, nextObj1, obj2) && checkY(obj1, nextObj1, obj2)) {
			return true;
		}

		return false;
	}

	private static Integer[][] getXPerimeter(Entity object) {

		Integer[][] perimeter = new Integer[object.getGraphicalObject().height][2];
		Integer[][] pixels = object.getGraphicalObject().getPixels();
		int width = object.getGraphicalObject().width;
		int height = object.getGraphicalObject().height;
		boolean isLeftPerimeter = true;

		for (int rowNum = 0; rowNum < height; rowNum++) {
			for (int x = 0; x < width; x++) {

				if (isLeftPerimeter) {
					if ((pixels[x][rowNum] != null && x == 0)) {
						perimeter[rowNum][0] = x;
						isLeftPerimeter = false;
					}
					if (x > 0) {
						if (pixels[x - 1][rowNum] == null) {
							perimeter[rowNum][0] = x;
							isLeftPerimeter = false;
						}
					}
				} else if (!isLeftPerimeter) {
					if (pixels[x][rowNum] != null && x == (width - 1)) {
						perimeter[rowNum][1] = x;
						isLeftPerimeter = true;
					}
					if (x < width - 1) {
						if (pixels[x + 1][rowNum] == null) {
							perimeter[rowNum][1] = x;
							isLeftPerimeter = true;
						}
					}
				}

			}
		}

		return perimeter;
	}

	private static Integer[][] getYPerimeter(Entity object) {

		Integer[][] perimeter = new Integer[object.getGraphicalObject().width][2];
		boolean isTopPerimeter = true;
		int width = object.getGraphicalObject().width;
		int height = object.getGraphicalObject().height;
		Integer[][] pixels = object.getGraphicalObject().getPixels();

		for (int colNum = 0; colNum < width; colNum++) {
			for (int y = 0; y < height; y++) {
				if (isTopPerimeter) {
					if ((pixels != null && y == 0)) {
						perimeter[colNum][0] = y;
						isTopPerimeter = false;
					}
					if (y > 0) {
						if (pixels[colNum][y + 1] == null) {
							perimeter[colNum][0] = y;
							isTopPerimeter = false;
						}
					}
				} else if (!isTopPerimeter) {
					if (pixels[colNum][y] != null && y == (height - 1)) {
						perimeter[colNum][1] = y;
						isTopPerimeter = true;
					}
					if (y < height - 1) {
						if (pixels[colNum][y + 1] == null) {
							perimeter[colNum][1] = y;
							isTopPerimeter = true;
						}
					}
				}
			}
		}

		return perimeter;
	}

	private static boolean checkX(Entity obj1, Entity nextObj1, Entity obj2) {

		Integer[][] obj1XPerimeter = getXPerimeter(obj1);
		Integer[][] obj2XPerimeter = getXPerimeter(obj2);

		int initalX;
		int finalX;

		if (obj1.getX() > nextObj1.getX()) {
			initalX = nextObj1.getX();
			finalX = obj1.getX();
		} else {
			initalX = obj1.getX();
			finalX = nextObj1.getX();
		}

		int obj2x = obj2.getX();

		for (int x = initalX; x <= finalX; x++) {
			for (int rowObj1 = 0; rowObj1 < obj1.getGraphicalObject().height; rowObj1++) {
				for (int rowObj2 = 0; rowObj2 < obj2.getGraphicalObject().height; rowObj2++) {
					boolean condition1 = (x + obj1XPerimeter[rowObj1][0]) <= (obj2x + obj2XPerimeter[rowObj2][1]);
					boolean condition2 = (x + obj1XPerimeter[rowObj1][1]) >= (obj2x + obj2XPerimeter[rowObj2][0]);
					if (condition1 && condition2) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static boolean checkY(Entity obj1, Entity nextObj1, Entity obj2) {

		Integer[][] obj1YPerimeter = getYPerimeter(obj1);
		Integer[][] obj2YPerimeter = getYPerimeter(obj2);

		int initalY;
		int finalY;
		if (obj1.getY() > nextObj1.getY()) {
			initalY = nextObj1.getY();
			finalY = obj1.getY();
		} else {
			initalY = obj1.getY();
			finalY = nextObj1.getY();
		}

		int obj2Y = obj2.getY();

		for (int y = initalY; y <= finalY; y++) {
			for (int colObj1 = 0; colObj1 < obj1.getGraphicalObject().width; colObj1++) {
				for (int colObj2 = 0; colObj2 < obj2.getGraphicalObject().width; colObj2++) {
					boolean condition1 = (y + obj1YPerimeter[colObj1][0]) <= (obj2Y + obj2YPerimeter[colObj2][1]);
					boolean condition2 = (y + obj1YPerimeter[colObj1][1]) >= (obj2Y + obj2YPerimeter[colObj2][0]);
					
					if (condition1 && condition2) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
