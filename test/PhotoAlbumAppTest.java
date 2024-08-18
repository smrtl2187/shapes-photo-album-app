import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import photoalbumapp.model.PhotoAlbumModel;
import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Rectangle;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.model.snapshot.Snapshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the PhotoAlbum model that implements the IPhotoAlbumApp interface.
 * Also tests the Editor class that implements the IEditor interface
 *      as it is an attribute of the PhotoAlbum model.
*/
public class PhotoAlbumAppTest {
  private PhotoAlbumModel model;

  @Before
  public void setUp() {
    model = new PhotoAlbumModel();
    model.addShape("rectangle",10.0, 20.0,
            "R", 4, 7, 10,
            50, 40);
  }

  /**
   * The following tests for shape manipulation test the methods
   *     found in both the Editor and PhotoAlbumModel classes.
   */


  @Test
  public void testAddShapeAndRemoveShape() {
    //test add shape
    model.addShape("oval",10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    assertTrue(model.getEditor().getShapes().containsKey("O"));

    IShape shape = model.getEditor().getShapes().get("O");
    assertEquals("oval", shape.getShapeType());
    assertEquals(10.0, shape.getPosition().getX(), 0.001);
    assertEquals(20.0, shape.getPosition().getY(), 0.001);
    assertEquals(4, shape.getColor().getRed(), 0.001);
    assertEquals(7, shape.getColor().getGreen(), 0.001);
    assertEquals(10, shape.getColor().getBlue(), 0.001);
    assertEquals(50, shape.getHorizontalDimension(), 0.001);
    assertEquals(40, shape.getVerticalDimension(), 0.001);
    //System.out.println(model.getShapeInfo());

    //test remove shape
    model.removeShape("O");
    //number of key-value pairs should be 1, not 2
    assertEquals(1, model.getEditor().getShapes().size());
    //"O" was successfully removed, "R" remains in editor
    assertTrue(model.getEditor().getShapes().containsKey("R"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeSameNameThrowsIllegalArgumentException() {
    //editor should test for unique name
    model.addShape("oval",10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    model.addShape("oval",10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveShapeNullNameThrowsNullPointerException() {
    model.removeShape(null);
  }

  @Test
  public void testRecolorShape() {
    //System.out.println(model.getShapeInfo());
    model.recolorShape("R", 100, 110, 120);
    IShape shape = model.getEditor().getShapes().get("R");
    assertEquals(100, shape.getColor().getRed(), 0.001);
    assertEquals(110, shape.getColor().getGreen(), 0.001);
    assertEquals(120, shape.getColor().getBlue(), 0.001);
  }

  @Test(expected = NullPointerException.class)
  public void testRecolorShapeNullNameThrowsNullPointerException() {
    model.recolorShape(null, 0, 0, 0);
  }

  @Test
  public void testResizeShape() {
    model.resizeShape("R", 300,600);
    IShape shape = model.getEditor().getShapes().get("R");
    assertEquals(300, shape.getVerticalDimension(), 0.001);
    assertEquals(600, shape.getHorizontalDimension(), 0.001);
  }

  @Test(expected = NullPointerException.class)
  public void testResizeShapeNullNameThrowsNullPointerException() {
    model.resizeShape(null, 10, 10);
  }

  @Test
  public void testMoveShape() {
    model.moveShape("R", 300, 400);
    IShape shape = model.getEditor().getShapes().get("R");
    assertEquals(300, shape.getPosition().getX(), 0.001);
    assertEquals(400, shape.getPosition().getY(), 0.001);
  }

  @Test(expected = NullPointerException.class)
  public void testMoveShapeNullNameThrowsNullPointerException() {
    model.moveShape(null, 10, 10);
  }

  @Test
  public void testGetShapesInfo() {
    //System.out.println(model.getEditor().toString());
    assertEquals(model.getShapesInfo(),
            "\nName: R"
                    + "\nType: rectangle"
                    + "\nColor: (4.0, 7.0, 10.0)"
                    + "\nMinimum Corner: (10.0, 20.0), Height: 40.0, Width: 50.0");
  }

  @Test
  public void testClearEditor() { //also tests getEditor()
    model.clearEditor();
    //number of key-value pairs should be 0
    assertEquals(0, model.getEditor().getShapes().size());
  }


  /**
   * The following tests are for the snapshot-related methods.
   * Tests takeSnapshot, getSnapshotsMap, getSnapshotsList, toString, getSnapshotIDs, and getSnapshot.
   * Also tests invalid snapshotID and invalid index for getSnapshot.
   */
  @Test
  public void testSnapshotMethods() {
    //System.out.println(model.getEditor().toString());
    model.addShape("oval",10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);

    //test takeSnapshot
    model.takeSnapshot(""); //description can be empty string
    model.takeSnapshot("snapshot2");
    //also tests getSnapshotsMap
    assertEquals(2, model.getSnapshotsMap().size()); //2 snapshots successfully taken
    //System.out.println(model.toString());

    //test toString & getSnapshot(int num), also test getSnapshotsList as it is used in toString
    ISnapshot snapshot1 = model.getSnapshot(1); // get snapshot at index 0
    ISnapshot snapshot2 = model.getSnapshot(2); // get snapshot at index 1
    assertEquals(model.toString(), "Printing Snapshots\n"
            + snapshot1.toString()
            + snapshot2.toString());

    //test getSnapshotIDs
    assertEquals(Arrays.asList(snapshot1.getSnapshotID(), snapshot2.getSnapshotID()),
            model.getSnapshotIDs());

    // test getSnapshot(String snapshotID)
    String snapshotID1 = snapshot1.getSnapshotID();
    assertEquals(model.getSnapshot(snapshotID1), snapshot1);

    //test invalid snapshotID and invalid index for getSnapshot
    try {
      model.getSnapshot("2023-04-08T18:28:41.870728");
      fail("invalid snapshotID, snapshot not found");
    } catch (NoSuchElementException nse) {
      assertTrue(nse.getMessage().length() > 0);
    }

    try {
      model.getSnapshot(3);
      fail("index out of bounds");
    } catch (IndexOutOfBoundsException oob) {
      assertTrue(oob.getMessage().length() > 0);
    }

    try {
      model.getSnapshot(0);
      fail("index out of bounds");
    } catch (IndexOutOfBoundsException oob) {
      assertTrue(oob.getMessage().length() > 0);
    }

  }

  @Test(expected = NullPointerException.class)
  public void testTakeSnapshotNullDescriptionThrowsNullPointerException() {
    model.takeSnapshot(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSnapshotNullStringThrowsIllegalArgumentException() {
    model.getSnapshot(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSnapshotEmptyStringThrowsIllegalArgumentException() {
    model.getSnapshot("");
  }

}


