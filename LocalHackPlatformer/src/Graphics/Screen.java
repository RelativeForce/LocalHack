package Graphics;

public class Screen {
	
	private int width;
	private int height;
	private final int[][] pixels;
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Screen(int width, int height){
		
		pixels = new int[width][height];
		
		this.width = width;
		this.height = height;
	}
	/**
	 * 
	 */
	public int[] getPixels(){
		
		int[] p = new int[width*height];
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				p[j + i*width] = pixels[j][i];
			}
		}
		
		return p;
	}
	public void clear(){
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				pixels[j][i] = 0;
			}
		}
	}
}