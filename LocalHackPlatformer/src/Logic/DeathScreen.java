package Logic;

import java.awt.Color;

import Environment.Constants;
import entities.Entity;
import entities.Rectangle;

public class DeathScreen {

	private Rectangle deathScreen;
	private int height;

	public DeathScreen() {
		height = 0;
		deathScreen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, Color.RED.getRGB());
	}

	public void increment() {
		height += Constants.WINDOW_HEIGHT / 100;
		deathScreen = new Rectangle(0, 0, Constants.WINDOW_WIDTH, height, Color.RED.getRGB());
	}

	public Entity getEntity() {
		return deathScreen;
	}

}
