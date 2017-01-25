package resources;

import java.io.File;
import java.util.Scanner;

/**
 * Contains the basic file IO processes used by the resource managers.
 * @author Joshua_Eddy
 *
 */
public class TextFileReader {

	/**
	 * Gets a scanner which contains each line of the file specified in the file name located in the directory.
	 * @param fileName The filename of the file.
	 * @param directory The path of the parent directory to the file.
	 * @return A Scanner of the file.
	 */
	public static Scanner getScannedFile(String fileName, File[] directory) {

		Scanner scnr = null;

		try {
			scnr = new Scanner(getFile(fileName, directory));
		} catch (Exception e) {
		}

		return scnr;

	}

	/**
	 * Retrieves a file from a directory.
	 * @param fileName The filename of the file.
	 * @param directory The path of the parent directory to the file.
	 * @return A raw File.
	 */
	public static File getFile(String fileName, File[] directory) {

		for (File level : directory) {
			if (level.getPath().contains(fileName)) {

				try {
					return level;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	/**
	 * Gets the files in a specified directory.
	 * @param directoryPath The specified directory.
	 * @return
	 */
	public static File[] getDirectory(String directoryPath) {
		File[] directory = null;
		try {
			File folder = new File(directoryPath);
			directory = folder.listFiles();
		} catch (Exception e) {
			System.out.println("Failed to load directory");
		}
		return directory;
	}

}
