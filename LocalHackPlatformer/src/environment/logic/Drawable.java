package environment.logic;

import environment.graphics.objects.GraphicalObject;

/**
 * Denotes objects that may be displayed on screen.
 * @author Joshua_Eddy
 *
 * @see environment.graphics.objects.GraphicalObject
 */
public interface Drawable {
	
	/**
	 * Retrieves the visual representation of the object on screen.
	 * @return <code>GraphicalObject</code> that is to be displayed on screen.
	 * 
	 * @see environment.graphics.objects.GraphicalObject
	 */
	GraphicalObject getGraphicalObject();
	
	/**
	 * Retrieves the x position of the object on screen.
	 * @return <code>int</code> x coordinate of the object on screen.
	 */
	int getX();
	
	/**
	 * Retrieves the y position of the object on screen.
	 * @return <code>int</code> y coordinate of the object on screen.
	 */
	int getY();

}
