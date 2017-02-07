package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.graphics.objects.GraphicalObject;
import environment.graphics.objects.Rectangle;
import environment.logic.entities.Entity;

public class TestEntity {

	Entity testEntity1;

	@Before
	public void setup() {
		testEntity1 = Entity.Rectangle(20, 20, 20, 20, 40);
	}

	@Test
	public void testEquals() {

		Entity testEntity2;

		// Tests if two identical objects are evaluated as equal.
		testEntity2 = Entity.Rectangle(20, 20, 20, 20, 40);
		assertTrue(testEntity1.equals(testEntity2));

		// Tests if different x positions are detected.
		testEntity2 = Entity.Rectangle(21, 20, 20, 20, 40);
		assertTrue(!testEntity1.equals(testEntity2));

		// Test if different y positions are detected.
		testEntity2 = Entity.Rectangle(20, 21, 20, 20, 40);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different widths are detected.
		testEntity2 = Entity.Rectangle(20, 20, 21, 20, 40);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different heights are detected.
		testEntity2 = Entity.Rectangle(20, 20, 20, 21, 40);
		assertTrue(!testEntity1.equals(testEntity2));

		// Tests if different colours are detected.
		testEntity2 = Entity.Rectangle(20, 20, 20, 20, 41);
		assertTrue(!testEntity1.equals(testEntity2));
	}

	public void testGetGraphicalObject() {

		GraphicalObject rect = new Rectangle(20, 20, 40);

	}
}
