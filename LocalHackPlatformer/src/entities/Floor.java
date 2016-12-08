package entities;

public class Floor extends Entity{
	
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
