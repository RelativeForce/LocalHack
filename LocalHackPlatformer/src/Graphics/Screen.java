package Graphics;

public class Screen {
	
	private int width;
	private int height;
	private int[][] pixels;
	
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
		
		//setColor(0xff0000ff);
		
		int c = 0;
		int[] p = new int[width*height];
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				p[c++] = pixels[j][i];
			}
		}
		
		return p;
	}
	public void addGraphicalObject(GraphicalObject go, int x, int y){
		
		for(int i = 0; i < go.height; i++){
			
			if(0 <= y + i && y + i < height){
				
				for(int j = 0; j < go.width; j++){
					
					if(0 <= x + j && x + j < width){
					
						pixels[j + x][i + y] = go.getPixels()[j][i] != null ?
								go.getPixels()[j][i] : pixels[j + x][i + y];
						
					}
				}
			}
		}
	}
	public void setColor(int color){
		
		for(int i = 0; i < height; i++){
			
			for(int j = 0; j < width; j++){
				
				pixels[j][i] = color;
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