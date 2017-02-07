package environment.graphics.objects;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestRectangle {

	Rectangle rectangle;
	final int color = 30;
	
	@Before
	public void setup(){
		
	rectangle  = new Rectangle(2,2,color);
	}
	
	
	@Test
	public void testWidth(){
		
		assertEquals(rectangle.width, 2);
		
	}
	
	@Test
	public void testHeight(){
		
		assertEquals(rectangle.height, 2);
		
	}
	
	@Test
	public void testPixels(){
		
		for(int heightCounter = 0; heightCounter < rectangle.height; heightCounter++){
			
			for(int widthCounter = 0; widthCounter< rectangle.width; widthCounter++){
				
				assertTrue(rectangle.pixels[widthCounter][heightCounter] == color);
				
			}
		}
	}

}
