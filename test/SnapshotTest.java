import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import photoalbumapp.model.snapshot.Snapshot;
import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the Snapshot class
 *     that implements the ISnapshot interface.
 */
public class SnapshotTest {
  private List<IShape> shapes = new ArrayList<>();
  private Oval o1;
  private Rectangle r1;
  private Snapshot original;
  private Snapshot copy;

  @Before
  public void setUp() {
    o1 = new Oval(10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    r1 = new Rectangle(10.0, 20.0,
            "R", 4, 7, 10,
            50, 40);
    shapes.add(o1);
    shapes.add(r1);
    this.original = new Snapshot("original", shapes);
    this.copy = new Snapshot(original);
  }

  @Test
  public void testCopyConstructor() {
    assertEquals(copy, original);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullShapesListThrowsIllegalArgumentException() {
    new Snapshot("throwsException", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCopyConstructorNullSnapshotThrowsIllegalArgumentException() {
    new Snapshot(null);
  }

  @Test
  public void testGetSnapshotIDAndTimestamp() {
    //IDs should be unique
    assertNotEquals(this.copy.getSnapshotID(), this.original.getSnapshotID());

    //ID in correct format
    System.out.println(this.original.getSnapshotID());
    assertTrue(this.original.getSnapshotID().matches(
            "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+"));

    //timestamp in correct format
    System.out.println(this.original.getTimestamp());
    //System.out.println(this.copy.getTimestamp());
    assertTrue(this.original.getTimestamp().matches(
            "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
  }

  @Test
  public void testGetDescription() {
    assertEquals("original", this.original.getDescription());
  }

  @Test
  public void testGetShapes() {
    //test the shapes are the same
    for (int i = 0; i < this.original.getShapes().size(); i++) {
      assertEquals(this.original.getShapes().get(i), this.copy.getShapes().get(i));
    }

    //test shape lists are same size
    assertEquals(this.original.getShapes().size(), this.copy.getShapes().size());
  }

  @Test
  public void testEquals() {
    //same object, return true
    assertTrue(this.original.equals(this.original));

    //different class, return false
    assertFalse(this.original.equals("String"));

    //null, return false
    assertFalse(this.original.equals(null));

    //same attributes, return true
    assertTrue(this.original.equals(this.copy));

    //diff attributes, return false:

    //set up snapshot w/identical shape list
    List<IShape> shapes2 = new ArrayList<>();
    Oval o2 = new Oval(10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    Rectangle r2 = new Rectangle(10.0, 20.0,
            "R", 4, 7, 10,
            50, 40);
    shapes2.add(o2);
    shapes2.add(r2);
    Snapshot sameShapesSnapshot = new Snapshot("sameShapes", shapes2);

    assertFalse(this.original.equals(sameShapesSnapshot)); //same shapes, diff description

    //set up snapshot w/diff shape list
    List<IShape> shapes3 = new ArrayList<>();
    Oval o3 = new Oval(10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    shapes3.add(o3);
    Snapshot diffShapesSnapshot = new Snapshot("original", shapes3);

    assertFalse(this.original.equals(diffShapesSnapshot)); //diff shapes, same description
  }

  @Test
  public void testToString() {
    //System.out.println(this.original.toString());
    assertEquals(this.original.toString(),
            "\nSnapshotID: " + this.original.getSnapshotID()
            + "\nTimestamp: " + this.original.getTimestamp()
            + "\nDescription: " + this.original.getDescription()
            + "\nShape Information:\n"
            + o1.toString() + "\n" + r1.toString() + "\n\n");
  }

}
