package Environment;

import Logic.*;
import Graphics.*;

public class Main {
	
	private static Thread logicThread;
	private static Thread displayThread;

	public static void main(String[] args) {
		
		
		
		
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
				
				
				
			}
		};
		
	}

}
