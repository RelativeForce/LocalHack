package Graphics;

public class GraphicalObject {
	
	public final int width;
	public final int height;
	private Integer[][] pixels;
	
	public GraphicalObject(int width, int height){
		
		pixels = new Integer[width][height];
		
		this.width = width;
		this.height = height;
	}
	public Integer[][] getPixels(){
		
		return pixels;
	}
}
