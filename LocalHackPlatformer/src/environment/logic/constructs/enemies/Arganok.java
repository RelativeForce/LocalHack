package environment.logic.constructs.enemies;

import java.io.File;

import environment.Constants;
import environment.Main;
import environment.logic.Direction;
import environment.logic.Point;
import environment.logic.constructs.Construct;
import environment.logic.entities.Sprite;

/**
 * 
 * @author Joshua_Eddy
 *
 */
public class Arganok extends Construct implements Enemy {

	private Point startHover;
	private Point endHover;
	private Direction hoverDirection;
	private int trackingRadius;
	private int movesMade;

	public Arganok(Point inital, int trackingRadius) {
		super(inital.x, inital.y,
				new Sprite(new File(System.getProperty("user.dir")).getPath() + "\\" + Constants.ARGANOK_FILENAME, 35,
						27, inital.x, inital.y));
		this.trackingRadius = trackingRadius;
		this.movesMade = 0;
		setHoverBounds();
		this.hoverDirection = Direction.RIGHT;
	}

	@Override
	public void getMove() {

		int x = getX() + (getSprite().getEntity().getGraphicalObject().getWidth() / 2);
		int y = getY() + (getSprite().getEntity().getGraphicalObject().getHeight() / 2);

		int playerX = Main.player.getEntity().getX() + (Main.player.getEntity().getGraphicalObject().getWidth() / 2);
		int playerY = Main.player.getEntity().getY() + (Main.player.getEntity().getGraphicalObject().getHeight() / 2);

		Point playerPosition = new Point(playerX, playerY);
		Point arganokPosition = new Point(x, y);

		if (distanceToPlayer(playerPosition, arganokPosition) <= trackingRadius) {
			seek(playerPosition, arganokPosition);
		} else {
			hover();
		}

		if (movesMade % 5 == 0) {
			getSprite().nextFrame();
		}

		movesMade++;
	}

	private void hover() {

		int nextX = getNextX();
		
		if(nextX >= endHover.x || nextX <= startHover.x){
			hoverDirection = hoverDirection.getOppositeDirection();
		}
		
		setX(getNextX());

	}
	
	private int getNextX(){
		return getX() + ((hoverDirection == Direction.RIGHT) ? 1 : 0)
				+ ((hoverDirection == Direction.LEFT) ? (-1) : 0);
	}

	private void setHoverBounds() {

		this.startHover = new Point(getX(), getY());
		this.endHover = new Point(getX() + 50, getY());

	}

	private void seek(Point playerPosition, Point arganokPosition) {

		seekInX(arganokPosition, arganokPosition);
		seekInY(arganokPosition, arganokPosition);

		setHoverBounds();

	}

	private void seekInX(Point playerPosition, Point arganokPosition) {

		double changeInX = ((Math.abs(playerPosition.x - arganokPosition.x)) / 100);
		if (changeInX > 0 && changeInX < 1) {
			changeInX = 1;
		} else {
			changeInX = ((int) changeInX);
		}

		if (playerPosition.x > arganokPosition.x) {
			getSprite().revert();
			setX(getX() + (int) changeInX);

		} else {
			getSprite().invert();
			setX(getX() - (int) changeInX);
		}
	}

	private void seekInY(Point playerPosition, Point arganokPosition) {

		double changeInY = ((Math.abs(playerPosition.y - arganokPosition.y)) / 100);
		if (changeInY > 0 && changeInY < 1) {
			changeInY = 1;
		} else {
			changeInY = ((int) changeInY);
		}

		if (playerPosition.y > arganokPosition.y) {
			setY(getY() + (int) changeInY);

		} else {
			setY(getY() - (int) changeInY);
		}
	}

	private double distanceToPlayer(Point playerPosition, Point arganokPosition) {

		int xDistance = Math.abs(playerPosition.x - arganokPosition.x);
		int yDistance = Math.abs(playerPosition.y - arganokPosition.y);

		double resultantDistance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

		return resultantDistance;
	}

}
