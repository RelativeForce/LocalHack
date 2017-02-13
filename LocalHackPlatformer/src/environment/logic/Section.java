package environment.logic;

import java.util.LinkedList;
import environment.logic.constructs.Construct;

/**
 * A self defining data structure used to store a level in the game. The client
 * initialises the root <code>Section</code> and from this a binary tree of
 * child sections in defined. Once constructed all the elements of the level of
 * type <code>Construct</code> must be added to the root section using the
 * <code>{@link #addConstruct(Construct)}</code>.
 * 
 * @author Joshua_Eddy
 * @version 1.2
 * @see environment.logic.constructs.Construct
 * @see environment.logic.Level
 */
public class Section {

	/**
	 * Stores the maximum <code>int</code> width of a leaf <code>Section</code>
	 * of the <code>Section</code> binary tree.
	 */
	private final static int maxWidth = 500;

	/**
	 * Stores the maximum <code>int</code> height of a leaf <code>Section</code>
	 * of the <code>Section</code> binary tree.
	 */
	private final static int maxHeight = 500;

	/**
	 * Temporarily stores the <code>Construct</code>s that have been moved when
	 * the section tree is moved in a specified direction. This exists because a
	 * single <code>Construct</code> may be stored in multiple sections.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #move(int, int)
	 */
	private static LinkedList<Construct> moved;
	
	// Instance Fields
	// -------------------------------------------------------------------------------------

	/**
	 * This denotes the child <code>Section</code> that encompasses the left
	 * half of the parent section giving rise to the binary tree structure. If
	 * this is a leaf node of <code>Section</code> tree it will store the list
	 * of <code>Construct</code>s otherwise this <code>Section</code> will
	 * contain its own child <code>Section</code>s.
	 * 
	 * @see Section
	 * @see environment.logic.constructs.Construct
	 */
	private Section leftChild;

	/**
	 * This denotes the child <code>Section</code> that encompasses the right
	 * half of the parent section giving rise to the binary tree structure. If
	 * this is a leaf node of <code>Section</code> tree it will store the list
	 * of <code>Construct</code>s otherwise this <code>Section</code> will
	 * contain its own child <code>Section</code>s.
	 * 
	 * @see Section
	 * @see environment.logic.constructs.Construct
	 */
	private Section rightChild;

	/**
	 * Contains the list of <code>Construct</code>s that denote a specific
	 * section of the level. This filed will be initialised as <code>null</code>
	 * if this <code>Section</code> is not a leaf node of the
	 * <code>Section</code> binary tree.
	 * 
	 * @see Section
	 * @see environment.logic.constructs.Construct
	 * @see java.util.LinkedList
	 */
	private LinkedList<Construct> constructs;

	/**
	 * The <code>int</code> x coordinate of this <code>Section</code>. This is
	 * used to asses if a <code>Construct</code> will be contained within this
	 * section of the level.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 */
	private int x;

	/**
	 * The <code>int</code> y coordinate of this <code>Section</code>. This is
	 * used to asses if a <code>Construct</code> will be contained within this
	 * section of the level.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 */
	private int y;

	/**
	 * The <code>int</code> width of this <code>Section</code>. This is used to
	 * asses if a <code>Construct</code> will be contained within this section
	 * of the level.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 */
	private int width;

	/**
	 * The <code>int</code> height of this <code>Section</code>. This is used to
	 * asses if a <code>Construct</code> will be contained within this section
	 * of the level.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 */
	private int height;

	// Constructor
	// --------------------------------------------------------------------------------------

	/**
	 * Constructs a new Section while also generating its corresponding
	 * sub-sections based on the pre-defined maximum width and height of a
	 * section. Each section has a fixed size which is immutable. The root
	 * <code>Section</code> is constructed by the client and using the root
	 * nodes details all child <code>Section</code>s are constructed.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Section</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Section</code>.
	 * @param width
	 *            The <code>int</code> width of the <code>Section</code>. This
	 *            may not be less than or equal to zero.
	 * @param height
	 *            The <code>int</code> height of the <code>Section</code>. This
	 *            may not be less than or equal to zero.
	 * 
	 * @see Section
	 * @see environment.logic.constructs.Construct
	 */
	public Section(int x, int y, int width, int height) {

		// Initialises all of the fields of this Section.
		if (width > 0 && height > 0) {

			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;

			// numberOfsections stores the number of sections that can be fitted
			// into the current section.

			boolean hasChildren = width > maxWidth;

			// int numberOfSectionsY = (int) Math.ceil(height / maxHeight);

			// if the number of sections is greater than 1 then the current
			// section
			// is NOT a leaf node on the section tree.
			if (hasChildren) {

				// As this section is not a leaf node of the section tree it
				// does
				// not have any constructs inside it.
				constructs = null;

				// Constructs the first child section of this section. The width
				// of
				// this section is half the parents width and encompasses the
				// left
				// half of the parents horizontal space.
				this.leftChild = new Section(x, y, width / 2, height);

				// If the width of the parent is an odd number then the width of
				// the
				// second child section will be half of the parents width +1 to
				// account for the pixel lost in integer division. This section
				// will
				// encompass the right half of the parent section.
				if (width % 2 == 0) {

					this.rightChild = new Section(x + (width / 2), y, width / 2, height);

				} else {

					this.rightChild = new Section(x + (width / 2) + 1, y, (width / 2) + 1, height);

				}

			} else {
				// This is only executed if this section is a leaf nod of the
				// current section tree.
				constructs = new LinkedList<Construct>();
				this.leftChild = null;
				this.rightChild = null;

			}
		}
	}

	// Public Methods
	// --------------------------------------------------------------------------------------

	/**
	 * Adds a specified <code>Construct</code> to the level by evaluating which
	 * leaf <code>Section<code> the construct should be stored in and then
	 * storing it there.
	 * 
	 * @param construct <code>Construct</code> to be added.
	 * 
	 * @see #addToChildren(Construct)
	 * @see #addToConstructList(Construct)
	 * @see environment.logic.constructs.Construct
	 */
	public void addConstruct(Construct construct) {

		// if this section is a leaf node then add the construct to the
		// constructs list in this otherwise add the construct to this section's
		// child sections.
		if (isLeafNode()) {

			addToConstructList(construct);

		} else {

			addToChildren(construct);

		}

	}

	/**
	 * Retrieves the <code>Construct</code>s in the same section as the
	 * specified <code>Construct</code>. If this construct is not inside the
	 * current section no elements will be returned.
	 * 
	 * @param construct
	 *            <code>Construct</code> that shares the same section as the
	 *            collection of constructs that will be returned.
	 * @return <code>LinkedList</code> of <code>Construct</code>s that share the
	 *         same section as the specified construct.
	 * 
	 * @see #getFromChildren(Construct)
	 * @see java.util.LinkedList
	 * @see environment.logic.constructs.Construct
	 */
	public LinkedList<Construct> getSectionConstructs(Construct construct) {

		// If this is a leaf node then the parameter construct must be inside
		// this section meaning that this method must return the constructs in
		// this section that are stored in the linked list constructs.
		// Otherwise this section must be a parent section and therefore this
		// section's children must contain the parameter construct.
		if (isLeafNode()) {

			return constructs;

		} else {

			return getFromChildren(construct);
		}
	}

	/**
	 * Retrieves all the constructs stored in this section and all of its child
	 * sections.
	 * 
	 * @return <code>LinkedList</code> of type <code>Construct</code> that
	 *         contains all the constructs in this section and its child
	 *         sections.
	 *
	 * @see environment.logic.constructs.Construct
	 * @see #getAllFromChildren()
	 * @see java.util.LinkedList
	 */
	public LinkedList<Construct> getConstructs() {

		if (isLeafNode()) {

			return constructs;

		} else {

			return getAllFromChildren();
		}

	}

	/**
	 * Moves this section and all its child section in a specified direction.
	 * 
	 * @param changeInX
	 *            <code>int</code> the change in the x coordinate of this
	 *            section.
	 * @param changeInY
	 *            <code>int</code> the change in the y coordinate of this
	 *            section.
	 * 
	 * @see #moveSection(int, int)
	 * @see environment.logic.constructs.Construct
	 */
	public void move(int changeInX, int changeInY) {

		moved = new LinkedList<Construct>();

		// moves this section and its child sections.
		moveSection(changeInX, changeInY);

	}

	@Override
	public boolean equals(Object o) {

		// Checks to see if the parameter object is an instance of type Section.
		if (o instanceof Section) {

			// Type casts o to type Section for easy referencing.
			Section otherSection = (Section) o;

			// Checks that the fields of this are equal to the fields of the
			// otherSection.
			if (this.x == otherSection.x && this.y == otherSection.y && this.width == otherSection.width
					&& this.height == otherSection.height) {

				// If both Sections share the same leaf node status they may be
				// the same.
				if (this.isLeafNode() == otherSection.isLeafNode()) {

					// If both are leaf nodes then check the construct list of
					// both Sections for equality. Otherwise check both of the
					// sections child odes for equality.
					if (isLeafNode()) {

						// Checks if both the construct lists of both sections
						// are identical.
						if (otherSection.constructs.equals(this.constructs)) {

							return true;

						}

					} else {

						// Checks that both of the section's child nodes are
						// identical which recursively calls this method.
						if (this.leftChild.equals(otherSection.leftChild)
								&& this.rightChild.equals(otherSection.rightChild)) {

							return true;

						}
					}
				}
			}
		}

		return false;
	}
	
	// Private Methods
	// --------------------------------------------------------------------------------------

	/**
	 * Collects all the constructs from this section's child sections.
	 * 
	 * @return <code>LinkedList</code> of type <code>Construct</code> that
	 *         contains all the constructs from this section's child sections.
	 * 
	 * @see java.util.LinkedList
	 * @see environment.logic.constructs.Construct
	 */
	private LinkedList<Construct> getAllFromChildren() {

		// Declares and initialised a linked list that will contain all the
		// constructs from this sections child sections.
		LinkedList<Construct> childConstructs = new LinkedList<Construct>();

		// Retrieves all the constructs from the left and right child sections
		// and stores them inside the collection.
		childConstructs.addAll(leftChild.getConstructs());
		childConstructs.addAll(rightChild.getConstructs());

		return childConstructs;

	}

	/**
	 * Moves this section and all its child section in a specified direction.
	 * 
	 * @param changeInX
	 *            <code>int</code> the change in the x coordinate of this
	 *            section.
	 * @param changeInY
	 *            <code>int</code> the change in the y coordinate of this
	 *            section.
	 * 
	 * @see #moveChildren(int, int)
	 * @see #moveConstructs(int, int)
	 * @see environment.logic.constructs.Construct
	 */
	private void moveSection(int changeInX, int changeInY) {

		// Moves this section by changing its x and y coordinates by the
		// specified values.
		x += changeInX;
		y += changeInY;

		// Checks if this section is a child nod and is so all of the constructs
		// in this section are moved in the specified direction.
		// Otherwise both of this section's child sections are moved by the
		// specified
		// direction.
		if (!isLeafNode()) {

			moveChildren(changeInX, changeInY);

		} else {

			moveConstructs(changeInX, changeInY);

		}
	}

	/**
	 * Moves all the constructs in just this section in a specified direction.
	 * 
	 * @param changeInX
	 *            <code>int</code> the change in the x coordinate of the
	 *            constructs in this section.
	 * @param changeInY
	 *            <code>int</code> the change in the y coordinate of the
	 *            constructs in this section.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #moveSection(int, int)
	 */
	private void moveConstructs(int changeInX, int changeInY) {

		// Iterates through every element in this sections construct list.
		for (Construct con : constructs) {

			// If this construct has already been moved by another section
			// calling this method then it should not be moved again.
			if (!moved.contains(con)) {

				// Moves the current construct in the specified direction.
				con.move(changeInX, changeInY);

				// Once the construct has been moved its is added to the list of
				// moved constructs.
				moved.add(con);

			}

		}
	}

	/**
	 * Moves both of the child nodes of <code>this</code> in a specified
	 * direction.
	 * 
	 * @param changeInX
	 *            <code>int</code> the change in the x coordinate of this
	 *            section's child sections.
	 * @param changeInY
	 *            <code>int</code> the change in the y coordinate of this
	 *            section's child sections.
	 */
	private void moveChildren(int changeInX, int changeInY) {

		leftChild.moveSection(changeInX, changeInY);

		rightChild.moveSection(changeInX, changeInY);
	}

	/**
	 * Retrieves whether <code>this</code> section is a leaf node of the section
	 * tree.
	 * 
	 * @return <code>boolean</code> leaf node status.
	 * 
	 * @see Section
	 */
	private boolean isLeafNode() {

		return leftChild == null && rightChild == null;

	}

	/**
	 * Determines which child <code>Section</code> the specified
	 * <code>Construct</code> should be stored in.
	 * 
	 * @param construct
	 *            <code>Construct</code> that is to be store in on of the two
	 *            child sections of <code>this</code>.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 */
	private void addToChildren(Construct construct) {

		// Assigns the fundamental details of the parameter construct.
		int constructX = construct.getX();
		int constructY = construct.getY();
		int constructWidth = construct.getSprite().getEntity().getGraphicalObject().getWidth();
		int constructHeight = construct.getSprite().getEntity().getGraphicalObject().getHeight();

		// Checks if the parameter construct is inside or at any point enters
		// the
		// left child section.
		boolean insideLeftChild = constructX + constructWidth >= leftChild.x
				&& constructX <= leftChild.x + leftChild.width && constructY + constructHeight >= leftChild.y
				&& constructY <= leftChild.y + leftChild.height;

		// Checks if the parameter construct is inside or at any point enters
		// the
		// right child section.
		boolean insideRightChild = constructX + constructWidth >= rightChild.x
				&& constructX <= rightChild.x + rightChild.width && constructY + constructHeight >= rightChild.y
				&& constructY <= rightChild.y + rightChild.height;

		if (insideLeftChild) {
			leftChild.addConstruct(construct);
		}

		if (insideRightChild) {
			rightChild.addConstruct(construct);
		}
	}

	/**
	 * Adds a specified <code>Construct</code> to this section's constructs
	 * list.
	 * 
	 * @param constructToAdd
	 *            <code>Construct</code> to be added to this sections list of
	 *            constructs.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see #addConstruct(Construct)
	 * @see java.util.LinkedList
	 */
	private void addToConstructList(Construct constructToAdd) {

		// Checks if the construct that is to be added is not null
		if (constructToAdd != null) {

			// Checks if the construct to be added in already in the list or
			// not, if not the specified construct is added to the list of
			// constructs.

			if (!constructs.contains(constructToAdd)) {

				constructs.add(constructToAdd);

			}
		}
	}

	/**
	 * Retrieves all the constructs stored in the child sections that the
	 * specified constructs overlaps with.
	 * 
	 * @param construct
	 *            <code>Construct</code> that will be used to determine which
	 *            child section's constructs to return.
	 * @return <code>LinkedList</code> of type <code>Construct</code> that
	 *         contains all the constructs from the child sections that the
	 *         specified construct is overlapping with.
	 * 
	 * @see environment.logic.constructs.Construct
	 * @see Section
	 * @see java.util.LinkedList
	 */
	private LinkedList<Construct> getFromChildren(Construct construct) {

		// Assigns the fundamental details of the specified construct to local
		// variables to increase ease of reading.
		int constructX = construct.getX();
		int constructY = construct.getY();
		int constructWidth = construct.getSprite().getEntity().getGraphicalObject().getWidth();
		int constructHeight = construct.getSprite().getEntity().getGraphicalObject().getHeight();

		// Checks if the specified construct overlaps with the left child
		// section at any point.
		boolean insideLeftChild = constructX + constructWidth >= leftChild.x
				&& constructX <= leftChild.x + leftChild.width && constructY + constructHeight >= leftChild.y
				&& constructY <= leftChild.y + leftChild.height;

		// Checks if the specified construct overlaps with the right child
		// section at any point.
		boolean insideRightChild = constructX + constructWidth >= rightChild.x
				&& constructX <= rightChild.x + rightChild.width && constructY + constructHeight >= rightChild.y
				&& constructY <= rightChild.y + rightChild.height;

		// Initialises a linked list that will store the constructs that are
		// returned from the child sections.
		LinkedList<Construct> childConstructs = new LinkedList<Construct>();

		if (insideLeftChild) {
			
			// If the specified construct overlaps with the left child section
			// the constructs from the left child section are retrieved.
			childConstructs.addAll(leftChild.getSectionConstructs(construct));
		}

		if (insideRightChild) {

			// If the specified construct overlaps with the right child section
			// the constructs from the left child section are retrieved.
			childConstructs.addAll(rightChild.getSectionConstructs(construct));
		}
		return childConstructs;

	}

}
