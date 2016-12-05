package Logic;

import java.awt.Color;
import java.util.ArrayList;

import Environment.Constants;
import Environment.Main;
import entities.Entity;
import entities.Rectangle;

public class Player {

	private Entity playerEntity;
	private int ySpeed;
	private int xSpeed;
	public boolean isDead;

	public Player(int x, int y, int width, int height) {

		playerEntity = new Rectangle(x, y, width, height, Color.RED.getRGB());
		ySpeed = 0;
		xSpeed = 0;
		isDead = false;

	}

	public void gravity() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		if (!checkCollision(x + xSpeed, y + ySpeed, Main.components)) {
			playerEntity.setY(y + ySpeed);
			playerEntity.setX(x + xSpeed);
		}else{
			ySpeed = 0;
			xSpeed = 0;
		}
		

	}

	public void move(int changeInX) {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		if (!checkCollision(x + changeInX, y, Main.components)) {
			playerEntity.setX(x + changeInX);
			xSpeed = changeInX / Constants.FRICTION;
		}

	}

	public void jump(){

		gravity();
		if(ySpeed == 0){
			ySpeed = -Constants.JUMP_HEIGHT;
		}
		
		
	}
	
	public void checkForDeath(){
		
		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		if (checkCollision(x + xSpeed, y + ySpeed, Main.enemies)) {
			playerEntity = new Rectangle(x, y, playerEntity.getGraphicalObject().width, playerEntity.getGraphicalObject().height, Color.WHITE.getRGB());
			isDead = true;
		}
		
	}
	
	private boolean checkCollision(int nextX, int nextY, ArrayList<Entity> list) {

		Entity nextPlayerEntity = new Rectangle(nextX, nextY, playerEntity.getGraphicalObject().width,
				playerEntity.getGraphicalObject().height, 0xffff0000);

		for (Entity component : list) {
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
