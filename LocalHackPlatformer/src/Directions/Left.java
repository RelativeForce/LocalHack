package directions;

public class Left implements Direction{

	private final int x;
	private final int y;
	
	public Left(){
		x = -1;
		y = 0;
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
