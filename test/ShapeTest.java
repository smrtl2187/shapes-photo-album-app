import org.junit.Test;

import photoalbumapp.model.shape.Color;
import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Point2D;
import photoalbumapp.model.shape.Rectangle;
import photoalbumapp.model.shape.ShapeFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for all classes and the IShape interface
 *     found in the photoalbum.snapshot.shape package
 * Also tests the validateString method in the Utility class, used in ShapeFactory.
 */
public class ShapeTest {

  @Test
  public void testColorClass() {
    //test constructor & copy constructor
    Color original = new Color(1.0, 2.0 ,3.0);
    Color copy = new Color(original);
    assertEquals(original, copy);

    //test setColor & getters for RGB values
    original.setColor(4.0,5.0,6.0);
    assertEquals(original.getRed(), 4.0, 0.001);
    assertEquals(original.getGreen(), 5.0, 0.001);
    assertEquals(original.getBlue(), 6.0, 0.001);

    //test equals:

    //same object, return true
    assertTrue(original.equals(original));

    //different class, return false
    assertFalse(original.equals("String"));

    //null, return false
    assertFalse(original.equals(null));

    //same attributes, return true
    Color copy2 = new Color(copy);
    assertTrue(copy.equals(copy2));

    //diff attributes, return false
    copy.setColor(2.0, 2.0, 3.0);
    assertFalse(copy.equals(copy2)); //diff red
    copy.setColor(1.0, 3.0, 3.0);
    assertFalse(copy.equals(copy2)); //diff green
    copy.setColor(1.0, 2.0, 4.0);
    assertFalse(copy.equals(copy2)); //diff blue

    //test toString
    assertEquals(copy2.toString(), "\nColor: " + "("
            + "1.0" + ", "
            + "2.0" + ", "
            + "3.0" + ")" );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperBoundsRedValueThrowsIllegalArgumentException() {
    //greater than 255
    new Color(255.1,2.0, 3.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperBoundsGreenValueThrowsIllegalArgumentException() {
    //greater than 255
    new Color(1.0,256.0, 3.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperBoundsBlueValueThrowsIllegalArgumentException() {
    //greater than 255
    new Color(1.0,2.0, 255.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowerBoundsRedValueThrowsIllegalArgumentException() {
    //less than zero
    new Color(-1.0,2.0, 255.0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowerBoundsGreenValueThrowsIllegalArgumentException() {
    //less than zero
    new Color(1.0,-2.0, 255.0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowerBoundsBlueValueThrowsIllegalArgumentException() {
    //less than zero
    new Color(1.0,2.0, -1.0);

  }

  @Test
  public void testPoint2DClass() {
    //test constructor & copy constructor
    Point2D original = new Point2D(1.0, 2.0);
    Point2D copy = new Point2D(original);
    assertEquals(original, copy);

    //test setters & getters
    original.setX(2.0);
    assertEquals(original.getX(), 2.0, 0.001);
    original.setY(1.0);
    assertEquals(original.getY(), 1.0, 0.001);

    //test equals
    //same object, return true
    assertTrue(original.equals(original));

    //different class, return false
    assertFalse(original.equals("String"));

    //null, return false
    assertFalse(original.equals(null));

    //same attributes, return true
    Point2D copy2 = new Point2D(copy);
    assertTrue(copy.equals(copy2));

    //diff attributes, return false
    copy.setX(5.0);
    assertFalse(copy.equals(copy2)); //diff X
    copy.setX(1.0);
    copy.setY(5.0);
    assertFalse(copy.equals(copy2)); //diff Y

    //test toString
    assertEquals(copy2.toString(), "("
            + "1.0" + ", "
            + "2.0" + ")");
  }

  @Test
  public void testOvalClass() {
    //test constructor & copy constructor
    Oval o1 = new Oval(-1.56, -4.678,
            "O", 4, 7, 10,
            50.5, 40.0);
    Oval copy = new Oval(o1);
    assertEquals(o1, copy);

    //test set & get vertical & horizontal dimensions
    assertEquals(o1.getHorizontalDimension(),50.5, 0.001);
    o1.setHorizontalDimension(50.0);
    assertEquals(o1.getHorizontalDimension(), 50.0, 0.001);
    assertEquals(o1.getVerticalDimension(), 40.0, 0.001);
    o1.setVerticalDimension(50.0);
    assertEquals(o1.getVerticalDimension(), 50.0, 0.001);

    //test getters for color, name, shapeType, position
    Color colorCopy = new Color(4, 7, 10);
    assertTrue(o1.getColor().equals(colorCopy));
    assertEquals(o1.getName(), "O");
    assertEquals(o1.getShapeType(), "oval");
    Point2D positionCopy = new Point2D(-1.56, -4.678);
    assertTrue(o1.getPosition().equals(positionCopy));

    //test equals
    //same object, return true
    assertTrue(o1.equals(o1));

    //different class, return false
    assertFalse(o1.equals("String"));

    //null, return false
    assertFalse(o1.equals(null));

    //same attributes, return true
    Oval copy2 = new Oval(copy);
    assertTrue(copy.equals(copy2));

    //diff attributes, return false
    //note: name and shapeType are immutable
    copy.getPosition().setX(5.0);
    copy.getPosition().setY(5.0);
    assertFalse(copy.equals(copy2)); //diff position
    copy.getPosition().setX(-1.56);
    copy.getPosition().setY(-4.678);
    copy.getColor().setColor(1.0,1.0,1.0);
    assertFalse(copy.equals(copy2)); //diff color
    copy.getColor().setColor(4,7,10);
    copy.setHorizontalDimension(50.0);
    assertFalse(copy.equals(copy2)); //diff horizontal dimension
    copy.setHorizontalDimension(50.5);
    copy.setVerticalDimension(50);
    assertFalse(copy.equals(copy2)); //diff vertical dimension

    //test toString
    assertEquals(copy2.toString(), "\nName: " + "O"
            + "\nType: " + "oval"
            + copy2.getColor().toString()
            + "\nCenter: " + copy2.getPosition().toString()
            + ", Y Radius " + String.format("%.1f", 20.0)
            + ", X Radius: " + String.format("%.1f", 25.3));
  }

  @Test
  public void testRectangleClass() {
    //test constructor & copy constructor
    Rectangle r1 = new Rectangle(0.678, 5.0,
            "R", 2.0, 0.088, 0.0,
            10.0,20.66);
    Rectangle copy = new Rectangle(r1);
    assertEquals(r1, copy);

    //test set & get vertical & horizontal dimensions
    assertEquals(r1.getHorizontalDimension(),10.0, 0.001);
    r1.setHorizontalDimension(50.0);
    assertEquals(r1.getHorizontalDimension(), 50.0, 0.001);
    assertEquals(r1.getVerticalDimension(), 20.66, 0.001);
    r1.setVerticalDimension(50.0);
    assertEquals(r1.getVerticalDimension(), 50.0, 0.001);

    //test getters for color, name, shapeType, position
    Color colorCopy = new Color(2.0,0.088,0.0);
    assertTrue(r1.getColor().equals(colorCopy));
    assertEquals(r1.getName(), "R");
    assertEquals(r1.getShapeType(), "rectangle");
    Point2D positionCopy = new Point2D(0.678,5.0);
    assertTrue(r1.getPosition().equals(positionCopy));

    //test equals
    //same object, return true
    assertTrue(r1.equals(r1));

    //different class, return false
    assertFalse(r1.equals("String"));

    //null, return false
    assertFalse(r1.equals(null));

    //same attributes, return true
    Rectangle copy2 = new Rectangle(copy);
    assertTrue(copy.equals(copy2));

    //diff attributes, return false
    //note: name and shapeType are immutable
    copy.getPosition().setX(5.0);
    copy.getPosition().setY(5.0);
    assertFalse(copy.equals(copy2)); //diff position
    copy.getPosition().setX(0.678);
    copy.getPosition().setY(5.0);
    copy.getColor().setColor(1.0,1.0,1.0);
    assertFalse(copy.equals(copy2)); //diff color
    copy.getColor().setColor(2.0,0.088,0.0);
    copy.setHorizontalDimension(50.0);
    assertFalse(copy.equals(copy2)); //diff horizontal dimension
    copy.setHorizontalDimension(10.0);
    copy.setVerticalDimension(50);
    assertFalse(copy.equals(copy2)); //diff vertical dimension

    //test toString
    assertEquals(copy2.toString(), "\nName: " + "R"
            + "\nType: " + "rectangle"
            + copy2.getColor().toString()
            + "\nMinimum Corner: " + copy2.getPosition().toString()
            + ", Height: " + String.format("%.1f", 20.66)
            + ", Width: " + String.format("%.1f", 10.0));

  }

  /**
   * The following tests for exceptions test the super constructor in AbstractShape,
   *     so these exceptions are being tested for all inheriting classes such as oval or rectangle.
   */


  /*@Test(expected = IllegalArgumentException.class)
  public void testNullPositionThrowsIllegalArgumentException() {
    Oval o1 = new Oval(null,
            "O", new Color(4, 7, 10),
            50.5, 40.0);
  }*/

  /*@Test(expected = IllegalArgumentException.class)
  public void testNullColorThrowsIllegalArgumentException() {
    Oval o1 = new Oval(new Point2D(-1.56, -4.678),
            "O", null,
            50.5, 40.0);
  }*/

  @Test(expected = IllegalArgumentException.class)
  public void testNullStringNameThrowsIllegalArgumentException() {
    Oval o1 = new Oval(-1.56, -4.678,
            null, 4, 7, 10,
            50.5, 40.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyStringNameThrowsIllegalArgumentException() {
    Oval o1 = new Oval(-1.56, -4.678,
            "", 4, 7, 10,
            50.5, 40.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeVerticalDimensionThrowsIllegalArgumentException() {
    Oval o1 = new Oval(-1.56, -4.678,
            "O", 4, 7, 10,
            50.5, -40.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHorizontalDimensionThrowsIllegalArgumentException() {
    Oval o1 = new Oval(-1.56, -4.678,
            "O", 4, 7, 10,
            -50.5, 40.0);
  }

  @Test
  public void testShapeFactory() {
    //test all lowercase shapeType
    IShape oval = ShapeFactory.createShape("oval", 10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    assertTrue(oval instanceof Oval);
    IShape rectangle = ShapeFactory.createShape("rectangle", 30.0, 30.0,
            "R", 0.0, 1.0, 0.0,
            40.0, 30.0);
    assertTrue(rectangle instanceof Rectangle);

    //test for case-insensitivity
    IShape oval2 = ShapeFactory.createShape("OvAl", 10.0, 20.0,
            "O", 4, 7, 10,
            50, 40);
    assertTrue(oval2 instanceof Oval);
    IShape rectangle2 = ShapeFactory.createShape("RECTANGLE", 30.0, 30.0,
            "R", 0.0, 1.0, 0.0,
            40.0, 30.0);
    assertTrue(rectangle2 instanceof Rectangle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeWithInvalidShapeType() {
    ShapeFactory.createShape("circle", 10.0, 10.0,
            "C", 1.0, 0.0, 0.0,
            20.0, 20.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeWithNullShapeType() {
    ShapeFactory.createShape(null, 10.0, 10.0,
            "C", 1.0, 0.0, 0.0,
            20.0, 20.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeWithEmptyStringShapeType() {
    ShapeFactory.createShape("", 10.0, 10.0,
            "C", 1.0, 0.0, 0.0,
            20.0, 20.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeWithNullName() {
    ShapeFactory.createShape("circle", 10.0, 10.0,
            null, 1.0, 0.0, 0.0,
            20.0, 20.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeWithEmptyStringName() {
    ShapeFactory.createShape("circle", 10.0, 10.0,
            "", 1.0, 0.0, 0.0,
            20.0, 20.0);
  }

}