package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.graphics.objects.GraphicalObject;
import environment.graphics.objects.Rectangle;
import environment.logic.entities.Entity;
import environment.logic.entities.HitBox;

public class TestEntity {

	private Entity testEntity1;
	private int x;
	private int y;
	private int width;
	private int height;
	private Integer color;

	@Before
	public void setup() {
		x = 20;
		y = 20;
		width = 20;
		height = 20;
		color = 40;
		testEntity1 = Entity.newRectangle(x, y, width, height, color);
	}

	@Test
	public void equals() {

		Entity testEntity2;

		// Tests if two identical objects are evaluated as equal.
		testEntity2 = Entity.newRectangle(x, y, width, height, color);
		assertTrue(testEntity1.equals(testEntity2));

		// Tests if different x positions are detected.
		testEntity2 = Entity.newRectangle(x + 1, y, width, height, color);
		assertTrue(!testEntity1.equals(testEntity2));

		// Test if different y positions are detected.
		testEntity2 = Entity.newRectangle(x, y + 1, width, height, color);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different widths are detected.
		testEntity2 = Entity.newRectangle(x, y, width + 1, height, color);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different heights are detected.
		testEntity2 = Entity.newRectangle(x, y, width, height + 1, color);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different colours are detected.
		testEntity2 = Entity.newRectangle(x, y, width, height, color + 1);
		assertTrue(!testEntity1.equals(testEntity2));
	}

	@Test
	public void getGraphicalObject() {

		GraphicalObject testRectangle = new Rectangle(width, height, color);

		assertTrue(testEntity1.getGraphicalObject().equals(testRectangle));

	}

	@Test
	public void getHitBox() {

		GraphicalObject testRectangle = new Rectangle(width, height, color);

		HitBox testHitBox = new HitBox(testRectangle);

		assertTrue(testEntity1.getHitBox().equals(testHitBox));

	}

	@Test
	public void getX() {

		Entity testEntity2 = Entity.newRectangle(x, y, width, height, color);

		assertTrue(testEntity2.getX() == x);

	}

	@Test
	public void setX() {

		Entity testEntity2 = Entity.newRectangle(x, y, width, height, color);

		testEntity2.setX(x + 1);

		assertTrue(testEntity2.getX() == x + 1);

	}

	@Test
	public void getY() {

		Entity testEntity2 = Entity.newRectangle(x, y, width, height, color);

		assertTrue(testEntity2.getY() == y);
	}

	@Test
	public void setY() {

		Entity testEntity2 = Entity.newRectangle(x, y, width, height, color);

		testEntity2.setY(y + 1);

		assertTrue(testEntity2.getY() == y + 1);

	}
	
}
