package Environment;

import entities.*;
import Graphics.*;
import Logic.*;
import java.util.ArrayList;

public class Main {

	// Static
	public static boolean run;
	public static Player player;
	public static Level level;

	// Private
	private static Thread logicThread;
	private static Thread displayThread;
	private static Display display;
	private static Screen screen;

	public static void main(String[] args) {

		run = true;
		display = new Display(1000, 500, "Platformer");
		screen = new Screen(1000, 500);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		player = new Player(20, 20, 20, 20);
		level = new Level();

		// Thread initialisation
		logicThread();
		displayThread();
		logicThread.start();
		displayThread.start();

	}

	private static void logicThread() {

		logicThread = new Thread() {

			@Override
			public void run() {

				while (run) {
					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					logic();
				}
			}
		};
	}

	private static void displayThread() {

		displayThread = new Thread() {

			@Override
			public void run() {

				while (run) {

					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					display();
				}
			}
		};
	}

	private static void logic() {
		player.gravity();
		player.checkForDeath();
	}

	private static void display() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		entities.addAll(Level.components);
		entities.addAll(Level.enemies);
		entities.add(player.getEntity());

		for (Entity entity : entities) {
			screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
		}
		display.render(screen);
		screen.clear();
	}
}
