package environment;

/**
 * Handles all the threads required to run the game.
 * 
 * @author Joshua_Eddy
 *
 */
public class ThreadHandler {

	private static boolean run;
	private static Thread playerThread;
	private static Thread displayThread;
	private static Thread enemyThread;
	private static boolean isActive;

	/**
	 * Constructs a new ThreadHandler that will create the threads required to
	 * run the game. This will not start the threads.
	 */
	public ThreadHandler() {

		isActive = false;
		run = true;
		startPlayerThread();
		startDisplayThread();
		startEnemyThread();

	}

	/**
	 * Starts the threads required to run the game.
	 */
	public void startThreads() {
		if (!isActive) {
			playerThread.start();
			displayThread.start();
			enemyThread.start();
			isActive = true;
		}
	}

	/**
	 * Stops the threads required to run the game causing the game to stop. The
	 * ThreadHandler must be declared as a new object in order to restart the
	 * game.
	 */
	public void stopThreads() {
		run = false;
	}

	private void startPlayerThread() {

		playerThread = new Thread() {

			@Override
			public void run() {

				while (run) {
					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Main.playerThread();
				}
			}
		};
	}

	private void startDisplayThread() {

		displayThread = new Thread() {

			@Override
			public void run() {

				while (run) {

					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Main.displayThread();
				}
			}
		};
	}

	private void startEnemyThread() {

		enemyThread = new Thread() {

			@Override
			public void run() {

				while (run) {

					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Main.enemyThread();
				}
			}
		};

	}

}
