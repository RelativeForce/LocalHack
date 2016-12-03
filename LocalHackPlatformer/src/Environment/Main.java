package Environment;

import entities.*;
import Graphics.*;
import Logic.*;

import java.awt.Color;
import java.util.ArrayList;

public class Main {

	// Static
	public static boolean run;
	public static Player player;
	public static ArrayList<Entity> components;
	public static ArrayList<Entity> enemies;
	
	// Private
	private static Thread logicThread;
	private static Thread displayThread;
	private static Display display;
	private static Screen screen;
	
	public static void main(String[] args) {

		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		run = true;
		display = new Display(1000, 500, "Platformer");
		screen = new Screen(1000, 500);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}
		
		player = new Player(20, 20, 20, 20);

		addComponents();
		addEnemies();
		
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
					player.checkForDeath();
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

	private static void addComponents(){
		
		Rectangle floor = new Rectangle(0, 450, 1000, 50, Color.BLUE.getRGB());
		components.add(floor);
		
		Rectangle lv1 = new Rectangle(200, 350, 500, 50, Color.BLUE.getRGB());
		components.add(lv1);
		
		Rectangle lv1a = new Rectangle(200, 350, 50, 100, Color.BLUE.getRGB());
		components.add(lv1a);
		
		Rectangle lv2 = new Rectangle(325, 250, 250, 50, Color.BLUE.getRGB());
		components.add(lv2);
		
		Rectangle lv3 = new Rectangle(450, 100, 50, 50, Color.CYAN.getRGB());
		components.add(lv3);
	}
	
	private static void addEnemies(){
		
		Rectangle em1 = new Rectangle(100, 300, 50, 50, Color.green.getRGB());
		enemies.add(em1);
		
	}
}
