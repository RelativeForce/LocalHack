package environment.logic;

import java.util.LinkedList;

import environment.logic.constructs.Construct;

public class Section {

	private final static int maxWidth = 500;
	// private final static int maxHeight = 500;

	private Section a;
	private Section b;
	private LinkedList<Construct> constructs;
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Constructs a new Section while also generating its corresponding
	 * sub-sections based on the pre-defined maximum width and height of a
	 * section. Each section has a fixed size which is immutable.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Section</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Section</code>.
	 * @param width
	 *            The <code>int</code> width of the <code>Section</code>.
	 * @param height
	 *            THe <code>int</code> height of the <code>Section</code>.
	 */
	public Section(int x, int y, int width, int height) {

		// Initialises all of the fields of this Section.
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// numberOfsections stores the number of sections that can be fitted
		// into the current section.
		int numberOfSectionsX = (int) Math.ceil(width / maxWidth);

		// int numberOfSectionsY = (int) Math.ceil(height / maxHeight);

		// if the number of sections is greater than 1 then the current section
		// is NOT a leaf node on the section tree.
		if (numberOfSectionsX > 1) {

			// As this section is not a leaf node of the section tree it does
			// not have any constructs inside it.
			constructs = null;

			// Constructs the first child section of this section. The width of
			// this section is half the parents width and encompasses the left
			// half of the parents horizontal space.
			this.a = new Section(x, y, width / 2, height);

			// If the width of the parent is an odd number then the width of the
			// second child section will be half of the parents width +1 to
			// account for the pixel lost in integer division. This section will
			// encompass the right half of the parent section.
			if (width % 2 == 0) {

				this.b = new Section(x + (width / 2), y, width / 2, height);

			} else {

				this.b = new Section(x + (width / 2) + 1, y, (width / 2) + 1, height);

			}

		} else {
			// This is only executed if this section is a leaf nod of the
			// current section tree.
			constructs = new LinkedList<Construct>();
			this.a = null;
			this.b = null;

		}

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void addConstruct(Construct con1) {

		if (isLeafNode()) {

			addToConstructList(con1);

		} else {

			int con1X = con1.getX();
			int con1Y = con1.getY();
			int con1Width = con1.getSprite().getEntity().getGraphicalObject().getWidth();
			int con1Height = con1.getSprite().getEntity().getGraphicalObject().getHeight();

			boolean insideA = con1X + con1Width >= a.x && con1X <= a.x + a.width && con1Y + con1Height >= a.y
					&& con1Y <= a.y + a.height;

			boolean insideB = con1X + con1Width >= b.x && con1X <= b.x + b.width && con1Y + con1Height >= b.y
					&& con1Y <= b.y + b.height;

			if (insideA) {
				a.addConstruct(con1);
			}

			if (insideB) {
				b.addConstruct(con1);
			}

		}

	}

	private void addToConstructList(Construct con1) {

		if (con1 != null) {

			boolean alreadyInList = false;

			for (Construct con2 : constructs) {

				if (con1.equals(con2)) {

					alreadyInList = true;
				}
			}
			if (!alreadyInList) {

				constructs.add(con1);

			}
		}
	}

	public LinkedList<Construct> getSectionConstructs(Construct con1) {

		if (isLeafNode()) {
			return constructs;
		} else {

			int con1X = con1.getX();
			int con1Y = con1.getY();
			int con1Width = con1.getSprite().getEntity().getGraphicalObject().getWidth();
			int con1Height = con1.getSprite().getEntity().getGraphicalObject().getHeight();

			boolean insideA = con1X + con1Width >= a.x && con1X <= a.x + a.width && con1Y + con1Height >= a.y
					&& con1Y <= a.y + a.height;

			boolean insideB = con1X + con1Width >= b.x && con1X <= b.x + b.width && con1Y + con1Height >= b.y
					&& con1Y <= b.y + b.height;

			LinkedList<Construct> childConstructs = new LinkedList<Construct>();

			if (insideA) {
				
				childConstructs.addAll(a.getAllConstructs());
			
			}

			if (insideB) {

				childConstructs.addAll(b.getAllConstructs());
			}
			return childConstructs;
		}
	}

	private boolean isLeafNode() {

		return a == null && b == null;

	}

	public LinkedList<Construct> getAllConstructs() {

		if (isLeafNode()) {

			return constructs;

		} else {

			LinkedList<Construct> childConstructs = new LinkedList<Construct>();

			childConstructs.addAll(a.getAllConstructs());
			childConstructs.addAll(b.getAllConstructs());

			return childConstructs;
		}

	}

	public void move(int changeInX, int changeInY) {

		x += changeInX;
		y += changeInY;

		if (!isLeafNode()) {
			a.move(changeInX, changeInY);
			b.move(changeInX, changeInY);
		} else {
			for (Construct con : constructs) {
				con.move(changeInX, changeInY);
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof Section) {

		}

		return false;
	}

}
