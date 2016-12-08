package Graphics;

import entities.Entity;

public class GraphicalObject {

	public final int width;
	public final int height;
	private Integer[][] pixels;

	public GraphicalObject(int width, int height, Integer color) {

		pixels = new Integer[width][height];

		this.width = width;
		this.height = height;
		setColor(color);
	}

	public GraphicalObject(int width, int height, Entity[] entitys) {

		pixels = new Integer[width][height];
		this.width = width;
		this.height = height;
		complexObject(entitys);

	}

	private void complexObject(Entity[] entities) {

		boolean first = true;
		for (Entity entity : entities) {
			if (first) {
				setColor(entity.getColor());
				first = false;
			} else {
				setColor(entity);
			}
		}

	}

	private void setColor(Entity entity) {
		
		for (int i = 0; i < entity.getGraphicalObject().height; i++) {
			for (int j = 0; j < entity.getGraphicalObject().width; j++) {
				pixels[entity.getX() + j][entity.getY() + i] = entity.getColor();
			}
		}
	}

	public void setColor(Integer color) {

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				pixels[j][i] = color;
			}
		}
	}

	public void setColor(Integer color, int x, int y) {

		pixels[x][y] = color;
	}

	public Integer[][] getPixels() {

		return pixels;
	}
}
