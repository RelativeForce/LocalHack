package logic;

/**
 * Represents a coordinate point with an X and Y respectively.
 * @author Joshua_Eddy
 */
public class Point {
	
	/**
	 * The X coordinate of the Point.
	 */
	public int x;
	
	/**
	 * The Y coordinate of the Point.
	 */
	public int y;

	/**
	 * Constructs a Point object.
	 * @param x The X coordinate of the Point.
	 * @param y The Y coordinate of the Point.
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a Point object.
	 * @param point A Point to clone.
	 */
	public Point(Point point){
		this.x = point.x;
		this.y = point.y;
	}

}
