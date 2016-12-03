package Logic;

import Environment.Constants;
import Environment.Main;
import entities.Entity;
import entities.Rectangle;

public class Player {

	private Entity playerEntity;
	private int ySpeed;

	public Player(int x, int y, int width, int height) {

		playerEntity = new Rectangle(x, y, width, height);
		ySpeed = 0;

	}

	public void gravity() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		if (!checkCollision(x, y + ySpeed)) {
			playerEntity.setY(y + ySpeed);
		}else{
			ySpeed = 0;
		}
		

	}

	public void move(int changeInX) {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		if (!checkCollision(x + changeInX, y)) {
			playerEntity.setX(x + changeInX);
		}

	}

	public void jump(){

		gravity();
		if(ySpeed == 0){
			ySpeed = -20;
		}
		
		
	}
	
	private boolean checkCollision(int nextX, int nextY) {

		Entity nextPlayerEntity = new Rectangle(nextX, nextY, playerEntity.getGraphicalObject().width,
				playerEntity.getGraphicalObject().height);

		for (Entity component : Main.components) {
			if (HitDetection.detectHit(playerEntity, nextPlayerEntity, component)) {
				return true;
			}
		}
		return false;
	}

	public Entity getEntity() {
		return playerEntity;
	}
}
