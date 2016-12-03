package Logic;

import entities.Entity;
import entities.Rectangle;

public class Player {

	Entity playerEntity;

	public Player(int x, int y, int width, int height) {

		playerEntity = new Rectangle(x, y, width, height);

	}

	public void play() {

		Entity wall = new Rectangle(50, 20, 20, 20);
		Entity nextPlayerEntity = new Rectangle(playerEntity.getX() + 11, playerEntity.getY(),
				playerEntity.getGraphicalObject().width, playerEntity.getGraphicalObject().height);

		boolean hitDetected = HitDetection.detectHit(playerEntity, nextPlayerEntity, wall);

		System.out.print(hitDetected);

	}

}
