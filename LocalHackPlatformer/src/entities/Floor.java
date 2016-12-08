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
		
		int yLayers = width / boxWidth;
		int xLayers = height / boxHeight;
		
		Rectangle border = new Rectangle(x, y, width, height, borderColor);
		Rectangle[] boxes = new Rectangle[xLayers * yLayers];
		
		for(int i = 1; i < yLayers + 1; i++){
			for(int j = 0; j < xLayers; j++){
				
			}
		}
		
		Entity[] entities = new Entity[(xLayers * yLayers) + 1];
		setGraphicalObject(width, height, entities);
		
	}

}
