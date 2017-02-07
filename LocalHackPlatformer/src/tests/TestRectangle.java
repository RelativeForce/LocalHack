package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import environment.graphics.objects.Rectangle;

public class TestRectangle {

	Rectangle rectangle;
	final int color = 30;
	
	@Before
	public void setup(){
		
	rectangle  = new Rectangle(2,2,color);
	}
	
	
	@Test
	public void testWidth(){
		
		assertEquals(rectangle.getWidth(), 2);
		
	}
	
	@Test
	public void testHeight(){
		
		assertEquals(rectangle.getHeight(), 2);
		
	}
	
	@Test
	public void testPixels(){
		
		for(int heightCounter = 0; heightCounter < rectangle.getHeight(); heightCounter++){
			
			for(int widthCounter = 0; widthCounter< rectangle.getWidth(); widthCounter++){
				
				assertTrue(rectangle.getPixels()[widthCounter][heightCounter] == color);
				
			}
		}
	}

}
