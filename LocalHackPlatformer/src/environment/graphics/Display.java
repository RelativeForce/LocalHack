package environment.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import environment.Constants;
import resources.KeyBoardListener;;

/**
 * A JFrame that displays the game to the user.
 * 
 * @author John_Berg, Joshua_Eddy
 *
 */
public class Display extends Canvas {

	private int[] pixels;
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private BufferedImage image;
	private KeyBoardListener kbl;

	/**
	 * Creates a new Display object which displays the game to the user.
	 * 
	 * @param width
	 *            The width in pixels of the Display object.
	 * @param height
	 *            The height of pixels of the Display object.
	 * @param title
	 *            The text that will me displayed at the top of the Display
	 *            object.
	 */
	public Display(int width, int height, String title) {

		super.setMinimumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setMaximumSize(new Dimension(width, height));
		kbl = new KeyBoardListener(Constants.KEYBOARD_ROLLOVER);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		// Create frame
		frame = new JFrame(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		this.addKeyListener(kbl);
	}

	/**
	 * Renders the Screen object on the Display that is passed as a parameter.
	 * 
	 * @param s
	 *            The Screen object to render on the Display.
	 */
	public void render(Screen s) {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(3);

		} else {

			int[] temp = s.getPixels();

			if (temp.length == pixels.length) {

				for (int i = 0; i < temp.length; i++) {

					pixels[i] = temp[i];
				}

				Graphics g = bs.getDrawGraphics();
				g.fillRect(0, 0, getWidth(), getHeight());
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				g.dispose();
				bs.show();
			}
		}
	}

	/**
	 * Performs all the actions that are queued in the Keyboard Listener.
	 */
	public void handleKeys() {

		kbl.handleEnvents();
	}
}