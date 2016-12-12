package Graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import Logic.KeyBoardListener;;

/**
 * 
 * @author John_Berg
 *
 */
public class Display extends Canvas {

	private int[] pixels;
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private BufferedImage image;
	private KeyBoardListener kbl;

	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public Display(int width, int height, String title) {

		super.setMinimumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setMaximumSize(new Dimension(width, height));
		kbl =  new KeyBoardListener(5);
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
	public void handleKeys(){
		
		kbl.handleEnvents();
	}
}