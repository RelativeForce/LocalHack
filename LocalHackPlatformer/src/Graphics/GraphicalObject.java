package Graphics;

public class GraphicalObject {
	
	public final int width;
	public final int height;
	private Integer[][] pixels;
	
	public GraphicalObject(int width, int height, Integer color){
		
		pixels = new Integer[width][height];
		
		this.width = width;
		this.height = height;
		setColor(color);
	}
	public void setColor(Integer color){
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				pixels[j][i] = color;
			}
		}
	}
	public void setColor(Integer color, int x, int y){
		
		pixels[x][y] = color;
	}
	public Integer[][] getPixels(){
		
		return pixels;
	}
}
