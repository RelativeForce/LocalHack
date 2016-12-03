package Graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 
 * @author John_Berg
 *
 */
public class Display extends Canvas{
	
	private JFrame frame;
	private BufferedImage image;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public Display(int width, int height, String title){
		
		super.setMinimumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setMaximumSize(new Dimension(width, height));
		
		//Create frame
		frame = new JFrame(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/**
	 * 
	 */
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs != null){
			
			createBufferStrategy(3);
			
		}else{
			
			Graphics g = getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
		}
	}
}
