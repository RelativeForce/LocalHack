package Graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Environment.Main;

/**
 * 
 * @author John_Berg
 *
 */
public class Display extends Canvas implements KeyListener{
	
	/**
	 * 
	 */
	private int[] pixels;
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private BufferedImage image;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public Display(int width, int height, String title){
		
		this.addKeyListener(this);
		super.setMinimumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setMaximumSize(new Dimension(width, height));
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
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
	public void render(Screen s){
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			
			createBufferStrategy(3);
			
		}else{
			
			int[] temp = s.getPixels();
			
			if(temp.length == pixels.length){
				
				for(int i = 0; i < temp.length; i++){
					
					pixels[i] = temp[i];
				}
					
				
				Graphics g = bs.getDrawGraphics();
				g.fillRect(0, 0, getWidth(), getHeight());
				g.drawImage(image, 0 ,0 , getWidth(), getHeight(), null);
				g.dispose();
				bs.show();
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			Main.player.jump();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			Main.player.move(10);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			Main.player.move(-10);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}