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
	public void addGraphicalObject(GraphicalObject go, int x, int y){
		
		for(int i = 0; i < go.height; i++){
			
			if(0 <= y + i + go.height && y + i + go.height < height){
				
				for(int j = 0; j < go.width; j++){
					
					if(0 <= x + j + go.width && x + j + go.width < width){
					
						pixels[j + x][i + y] = go.getPixels()[i][j] != null ?
								go.getPixels()[i][j] : pixels[j + x][i + y];
					}
				}
			}
		}
	}
	public void clear(){
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				pixels[j][i] = 0;
			}
		}
	}
}