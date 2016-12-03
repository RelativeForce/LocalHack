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
	private static ArrayList<Entity> entities;

	public static void main(String[] args) {
		entities = new ArrayList<Entity>();
		run = true;
		display = new Display( 100, 100, "Platformer");
		screen = new Screen(100,100);
		for(int i = 0; i < 3; i++){
			display.render(screen);
		}
		
		Rectangle wall = new Rectangle(10,10,10,10);
		entities.add(wall);
		
		
		// Thread initialisation
		initaliseLogicThread();
		initaliseDisplayThread();
		logicThread.start();
		displayThread.start();
		
	}
	
	private static void initaliseLogicThread(){
		
		logicThread = new Thread(){
			
			@Override
			public void run(){
				
				
				
			}
		};
		
	}
	
	private static void initaliseDisplayThread(){
		
		displayThread = new Thread(){
			
			@Override
			public void run(){
				
				while(run){
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					display.render(screen);
				}
				
			}
		};
		
	}

}
