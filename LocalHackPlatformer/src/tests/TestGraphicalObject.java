package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import environment.graphics.objects.Rectangle;

public class TestGraphicalObject {

	private Rectangle rectangle;
	private int color;
	private int width;
	private int height;

	@Before
	public void setup() {

		width = 2;
		height = 2;
		color = 30;
		rectangle = new Rectangle(width, height, color);
	}

	@Test
	public void testWidth() {

		assertEquals(rectangle.getWidth(), width);

	}

	@Test
	public void testHeight() {

		assertEquals(rectangle.getHeight(), height);

	}

	@Test
	public void testPixels() {

		for (int heightCounter = 0; heightCounter < rectangle.getHeight(); heightCounter++) {

			for (int widthCounter = 0; widthCounter < rectangle.getWidth(); widthCounter++) {

				assertTrue(rectangle.getPixels()[widthCounter][heightCounter] == color);

			}
		}
	}

}
