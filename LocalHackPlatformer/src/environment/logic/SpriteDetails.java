package environment.logic;

import java.io.File;

public class SpriteDetails {
	
	private String path;
	private int width;
	private int height;
	
	public SpriteDetails(String relativePath, int width, int height){
		
		path = new File(System.getProperty("user.dir")).getPath() + File.separatorChar + relativePath;
		this.height = height;
		this.width = width;
	}

	public String getPath() {
		return path;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
