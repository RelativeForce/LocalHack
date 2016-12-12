package Logic;

import java.awt.Color;

import Environment.Constants;
import entities.Entity;
import entities.Rectangle;

public class DeathScreen {

	private Rectangle deathScreen;
	private int height;
	public boolean isFullScreen;

	public DeathScreen() {
		isFullScreen = false;
		height = 0;
		deathScreen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, Color.RED.getRGB());
	}

	public void increment() {
		if (height == Constants.WINDOW_HEIGHT) {
			isFullScreen = true;
			return;
		}

		height += Constants.WINDOW_HEIGHT / 50;
		deathScreen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, Color.RED.getRGB());
	}

	public void decrement() {
		isFullScreen = false;
		if (height == 0) {
			return;
		}
		height -= Constants.WINDOW_HEIGHT / 50;
		deathScreen = new Rectangle(0, Constants.WINDOW_HEIGHT - height, Constants.WINDOW_WIDTH, height,
				Color.RED.getRGB());
	}

	public Entity getEntity() {
		return deathScreen;
	}

}
