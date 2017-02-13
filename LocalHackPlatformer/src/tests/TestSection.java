package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.logic.Section;
import environment.logic.constructs.Construct;
import environment.logic.constructs.terrains.DefaultTerrain;
import environment.logic.entities.Entity;
import environment.logic.entities.Sprite;

public class TestSection {

	private Section mainSection;

	@Test
	public void testConstruct() {

		mainSection = new Section(0, 0, 5000, 500);

		assertTrue(mainSection.getConstructs().isEmpty());

		int x = 10;
		int y = 10;
		int width = 20;
		int height = 20;
		Integer color = 40;

		Construct rectangle1 = new DefaultTerrain(x, y,
				new Sprite(Entity.newRectangle(x, y, width, height, color), x, y));

		mainSection.addConstruct(rectangle1);

		assertTrue(mainSection.getSectionConstructs(rectangle1).contains(rectangle1));

		Construct rectangle2 = new DefaultTerrain(x + 400, y,
				new Sprite(Entity.newRectangle(x + 400, y, width, height, color), x + 400, y));

		assertTrue(mainSection.getSectionConstructs(rectangle2).isEmpty());

	}

}
