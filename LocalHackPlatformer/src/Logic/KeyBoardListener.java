package Logic;

import java.awt.event.KeyEvent;
import Environment.Constants;
import Environment.Main;

public class KeyBoardListener {

	private KeyEvent[] activeKeys;

	public KeyBoardListener(int rollOver) {
		activeKeys = new KeyEvent[rollOver];
	}

	public boolean isActive(KeyEvent key) {

		for (KeyEvent k : activeKeys) {

			if (k != null) {
				if (k.getKeyCode() == key.getKeyCode()) {
					//System.out.println("active");
					return true;
				}
			}
		}

		return false;
	}

	public void keyPressed(KeyEvent k) {

		if (!isActive(k) && isValid(k)) {

			for (int i = 0; i < activeKeys.length; i++) {
				KeyEvent currentKey = activeKeys[i];

				if (currentKey == null) {
					//System.out.println("key added");
					activeKeys[i] = k;
					break;
				}
			}
		}
	}

	public void keyReleased(KeyEvent k) {

		for (int i = 0; i < activeKeys.length; i++) {
			KeyEvent currentKey = activeKeys[i];

			if (currentKey != null) {
				if (currentKey.getKeyCode() == k.getKeyCode()) {

					activeKeys[i] = null;
					break;
				}
			}
		}

	}

	public void keyTyped(KeyEvent k) {

	}

	public void handleEnvents() {
		// System.out.println("checking keys");
		for (int i = 0; i < activeKeys.length; i++) {
			KeyEvent currentKey = activeKeys[i];
			if (!Main.player.isDead && currentKey != null) {

				if (currentKey.getKeyCode() == KeyEvent.VK_SPACE) {
					//System.out.println("jump");
					Main.player.jump();

				} else if (currentKey.getKeyCode() == KeyEvent.VK_RIGHT) {
					Main.player.move(Constants.MOVE_DISTANCE);

				} else if (currentKey.getKeyCode() == KeyEvent.VK_LEFT) {
					Main.player.move(-Constants.MOVE_DISTANCE);

				}
			}
		}
	}

	public boolean isValid(KeyEvent key) {

		switch (key.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_LEFT:
			break;
		default:
			return false;
		}
		//System.out.println("is valid");
		return true;
	}
}