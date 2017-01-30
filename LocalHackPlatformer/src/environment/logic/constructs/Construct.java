package environment.logic.constructs;

import java.util.ArrayList;

import environment.logic.entities.Entity;
import environment.logic.entities.Sprite;

public class Construct {

	private int x;
	private int y;
	private Sprite sprite;

	public Construct(int x, int y, Sprite sprite) {

		this.setX(x);
		this.setY(y);
		this.sprite = sprite;

	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getSprite() {
		sprite.setX(getX());
		sprite.setY(getY());
		return sprite;
	}

	public void move(int changeInX, int changeInY) {
		setX(getX() + changeInX);
		setY(getY() + changeInY);
	}

	public static <E> Entity[] filterConstructs(E type, ArrayList<Construct> collection) {
		ArrayList<Entity> entities =  new ArrayList<Entity>();
		
		if(type instanceof Construct){
			for(Construct con : collection){
				if(!type.getClass().equals(con.getClass())){
					entities.add(con.getSprite().getEntity());
				}
			}			
		}
		
		return entities.toArray(new Entity[entities.size()]);
	}

}
