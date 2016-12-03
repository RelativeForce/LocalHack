package Environment;

import entities.*;
import Graphics.*;
import Logic.*;
import java.util.ArrayList;

public class Main {

	private static Thread logicThread;
	private static Thread displayThread;
	private static boolean run;
	private static Display display;
	private static Screen screen;
	public static Player player;
	public static ArrayList<Entity> components;
	public static ArrayList<Entity> enemies;

	public static void main(String[] args) {

		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		run = true;
		display = new Display(1000, 500, "Platformer");
		screen = new Screen(1000, 500);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		Rectangle floor = new Rectangle(0, 450, 1000, 20);
		components.add(floor);
		player = new Player(20, 20, 20, 20);

		// Thread initialisation
		initaliseLogicThread();
		initaliseDisplayThread();
		logicThread.start();
		displayThread.start();

	}

	private static void initaliseLogicThread() {

		logicThread = new Thread() {

			@Override
			public void run() {

				while (run) {
					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					player.gravity();
				}
			}
		};

	}

	private static void initaliseDisplayThread() {

		displayThread = new Thread() {

			@Override
			public void run() {

				while (run) {

					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}

					ArrayList<Entity> entities = new ArrayList<Entity>();
					entities.addAll(components);
					entities.addAll(enemies);
					entities.add(player.getEntity());

					for (Entity entity : entities) {
						screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
					}
					display.render(screen);
					screen.clear();
				}

			}
		};

	}

}
