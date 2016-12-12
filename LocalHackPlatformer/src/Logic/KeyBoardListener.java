package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Environment.Constants;
import Environment.Main;

public class KeyBoardListener implements KeyListener {

	private KeyEvent[] activeKeys;

	public KeyBoardListener(int rollOver) {
		activeKeys = new KeyEvent[rollOver];
	}

	public boolean isActive(KeyEvent key) {

		for (KeyEvent k : activeKeys) {

			if (k != null) {
				if (k.getKeyCode() == key.getKeyCode()) {
					// System.out.println("active");
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void keyPressed(KeyEvent k) {

		if (!isActive(k) && isValid(k)) {

			for (int i = 0; i < activeKeys.length; i++) {
				KeyEvent currentKey = activeKeys[i];

				if (currentKey == null) {

					activeKeys[i] = k;
					break;
				}
			}
		}
	}

	@Override
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

	@Override
	public void keyTyped(KeyEvent k) {

	}

	public void handleEnvents() {
		for (int i = 0; i < activeKeys.length; i++) {
			KeyEvent currentKey = activeKeys[i];
			if (currentKey != null) {

				if (!Main.player.isDead) {
					if (currentKey.getKeyCode() == KeyEvent.VK_SPACE) {
						Main.player.jump();

					} else if (currentKey.getKeyCode() == KeyEvent.VK_RIGHT) {
						Main.player.move(Constants.MOVE_DISTANCE);

					} else if (currentKey.getKeyCode() == KeyEvent.VK_LEFT) {
						Main.player.move(-Constants.MOVE_DISTANCE);
					}
				} else {

					if (currentKey.getKeyCode() == KeyEvent.VK_ENTER && Main.deathScreen.isFullScreen) {
						Main.start();
					}
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
		case KeyEvent.VK_ENTER:
			break;
		default:
			return false;
		}
		return true;
	}
}