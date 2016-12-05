package Environment;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Joshua_Eddy
 * 
 */

public class LevelLoader {

	private ArrayList<Scanner> scnr;

	public LevelLoader(String directory) {

		try {
			scnr = new ArrayList<Scanner>();
			File folder = new File(directory);
			File[] levels = folder.listFiles();

			for(File level : levels){
				if(level.getPath().contains("LH_Level")){
					scnr.add(new Scanner(level.getPath()));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Failed to load level");
		}
	}

}
