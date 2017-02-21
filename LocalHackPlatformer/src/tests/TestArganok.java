package tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import environment.logic.Point;
import environment.logic.constructs.enemies.Arganok;

public class TestArganok {

	// Main tests
	// -----------------------------------------------------------------------------

	/**
	 * Tests the distanceToPlayer method in
	 * {@link environment.logic.constructs.enemies.Arganok}}
	 */
	@Test
	public void distanceToPlayer() {

		distanceToPlayer_test1();

		distanceToPlayer_test2();

		distanceToPlayer_test3();

	}

	// distanceToPlayer sub-tests
	// -----------------------------------------------------------------------------

	/**
	 * Tests to see if the distance between two points on a vertical line is
	 * correct. <br>
	 * <strong>Example:</strong> (0,0) to (0,10) == 10
	 */
	private void distanceToPlayer_test1() {

		Point playerPosition;
		Point argPosition;
		double distanceToPlayer = -1;

		playerPosition = new Point(0, 10);
		argPosition = new Point(0, 0);

		// Runs the method that is to be tested.
		try {
			distanceToPlayer = distanceToPlayer(playerPosition, argPosition);
		} catch (Exception e) {
			fail();
		}

		assertTrue(distanceToPlayer == 10);

	}

	/**
	 * Tests to see if the distance between two identical points is correct.
	 * <br>
	 * <strong>Example:</strong> (0,0) to (0,0) == 0
	 */
	private void distanceToPlayer_test2() {

		Point playerPosition;
		Point argPosition;
		double distanceToPlayer = -1;

		playerPosition = new Point(0, 0);
		argPosition = new Point(0, 0);

		try {
			distanceToPlayer = distanceToPlayer(playerPosition, argPosition);
		} catch (Exception e) {
			fail();
		}

		assertTrue(distanceToPlayer == 0);
	}

	/**
	 * Tests to see if the distance between two points on a diagonal line is
	 * correct. <br>
	 * <strong>Example:</strong> (0,0) to (3,4) == 5
	 */
	private void distanceToPlayer_test3() {

		Point playerPosition;
		Point argPosition;
		double distanceToPlayer = -1;

		argPosition = new Point(0, 0);
		playerPosition = new Point(3, 4);

		try {
			distanceToPlayer = distanceToPlayer(playerPosition, argPosition);
		} catch (Exception e) {
			fail();
		}

		assertTrue(distanceToPlayer == 5);

	}

	/**
	 * Fills the role of the distanceToPlayer method in {@link environment.logic.constructs.enemies.Arganok}
	 * @param playerPosition <code>Point</code> player position to be tested.
	 * @param arganokPosition <code>Point</code> arganok position to be tested.
	 * @return <code>int</code> the distance between the to points.
	 * @throws NoSuchMethodException unused.
	 * @throws SecurityException unused.
	 * @throws IllegalAccessException unused.
	 * @throws IllegalArgumentException unused.
	 * @throws InvocationTargetException unused.
	 */
	private double distanceToPlayer(Point playerPosition, Point arganokPosition) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// Create a new instance of Arganok
		Arganok arganokObject = new Arganok(new Point(0, 0), 50);

		Method method_distanceToPlayer;

		// Retrieve "distanceToPlayer() from Arganok.java"
		// This method is then set as public so that it may be invoked.
		method_distanceToPlayer = arganokObject.getClass().getDeclaredMethod("distanceToPlayer",
				new Class[] { Point.class, Point.class });
		method_distanceToPlayer.setAccessible(true);

		// Run methods and return the result of that method.
		return (double) method_distanceToPlayer.invoke(arganokObject, new Object[] { playerPosition, arganokPosition });

	}

}
