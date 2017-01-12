package graphics;

import java.io.File;
import java.io.FileReader;

public class SpriteSheetReader {
	
	private int width;
	private int height;
	private final File file;
	
	public SpriteSheetReader(String path, int width, int height) {
		
		this.width = width;
		this.height = height;
		file = new File(path);
	}
	
}