package Directions;

public class UpRight implements Direction {

	private final int x;
	private final int y;

	public UpRight() {
		x = 1;
		y = -1;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean checkEquals(Direction direction) {
		if (direction.getX() == x && direction.getY() == y) {
			return true;
		} else {
			return false;
		}
	}
}
