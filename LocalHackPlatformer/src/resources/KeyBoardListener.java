package resources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import environment.Constants;
import environment.Main;

/**
 * Listens for keyboard inputs and may process multiple inputs simultaneously.
 * 
 * @author Joshua_Eddy, John_Berg
 *
 */
public class KeyBoardListener implements KeyListener {

	private KeyEvent[] activeKeys;

	/**
	 * Constructs a new KeyBoardListener.
	 * 
	 * @param rollOver
	 *            The number of input that can be processed at once.
	 */
	public KeyBoardListener(int rollOver) {
		activeKeys = new KeyEvent[rollOver];
	}

	private boolean isActive(KeyEvent key) {

		for (KeyEvent k : activeKeys) {

			if (k != null) {
				if (k.getKeyCode() == key.getKeyCode()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	/**
	 * Adds a key to the list of active keys when a new key is pressed.
	 */
	public void keyPressed(KeyEvent key) {

		if (!isActive(key) && isValid(key)) {

			for (int i = 0; i < activeKeys.length; i++) {
				KeyEvent currentKey = activeKeys[i];

				if (currentKey == null) {

					activeKeys[i] = key;
					break;
				}
			}
		}
	}

	@Override
	/**
	 * Removes a key from the list of active keys when that key is released.
	 */
	public void keyReleased(KeyEvent key) {

		for (int i = 0; i < activeKeys.length; i++) {

			KeyEvent currentKey = activeKeys[i];

			if (currentKey != null) {
				if (currentKey.getKeyCode() == key.getKeyCode()) {

					activeKeys[i] = null;
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {

	}

	/**
	 * Executes all the events for the keys that are currently active.
	 */
	public void handleEnvents() {
		for (int i = 0; i < activeKeys.length; i++) {
			KeyEvent currentKey = activeKeys[i];
			if (currentKey != null) {

				if (!Main.player.isAlive() && !Main.transitionScreen.isActive) {
					if (currentKey.getKeyCode() == KeyEvent.VK_SPACE) {
						Main.player.jump();

					} else if (currentKey.getKeyCode() == KeyEvent.VK_RIGHT) {
						Main.player.move(Constants.MOVE_DISTANCE, 0);

					} else if (currentKey.getKeyCode() == KeyEvent.VK_LEFT) {
						Main.player.move(-Constants.MOVE_DISTANCE, 0);
					}
				}

			}
		}
	}

	private boolean isValid(KeyEvent key) {

		switch (key.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_ENTER:
			break;
		default:
			return false;
		}
		return true;
	}
}