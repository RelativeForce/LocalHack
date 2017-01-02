package entities;

/**
 * The Floor object is type of Entity. It consists of a set of squares bordered by a single colour.
 * @author Joshua_Eddy
 *
 */
public class Floor extends Entity{
	
	/**
	 * Initialises a Floor object Entity.
	 * @param x The X coordinate of the Floor object.
	 * @param y The Y coordinate of the Floor object.
	 * @param width The width in pixels of the Floor object.
	 * @param height The height in pixels of the Floor object.
	 * @param borderColor The RGB colour of the background box. 
	 * @param boxWidth The width of the little boxes inside the border box. width / boxWidth MUST BE AN INTEGER VLAUE.
	 * @param boxHeight The height of the little boxes inside the border box. height / boxHeight MUST BE AN INTEGER VLAUE.
	 * @param boxColor The RGB colour of the little boxes inside the border box.
	 */
	public Floor(int x, int y, int width, int height, Integer borderColor, int boxWidth, int boxHeight ,Integer boxColor){
		
		if (x >= 0) {
			setX(x);
		} else {
			setX(0);
		}
		if (y >= 0) {
			setY(y);
		} else {
			setY(0);
		}
		
		int xLayers = width / boxWidth;
		int yLayers = height / boxHeight;
		
		Rectangle border = new Rectangle(x, y, width, height, borderColor);
		setColor(border.getColor());
		Rectangle[][] boxes = new Rectangle[xLayers][yLayers];
		
		for(int i = 0; i < yLayers; i++){
			for(int j = 0; j < xLayers; j++){
				int boxX = (j * 25) + 1;
				int boxY = (i * 25) + 1;
				boxes[j][i] = new Rectangle(boxX, boxY, 23, 23, boxColor);
			}
		}
		
		Entity[] entities = new Entity[(xLayers * yLayers) + 1];
		entities[0] = border;
		int c = 1;
		for(int i = 0; i < yLayers; i++){
			for(int j = 0; j < xLayers; j++){
				entities[c] = boxes[j][i];
				c++;
			}
		}
		
		setGraphicalObject(width, height, entities);
		
	}

}
