package photoalbumapp.model.shape;

import photoalbumapp.Utility;

/**
 * The type Shape factory.
 */
public class ShapeFactory {

  /**
   * Create shape shape.
   *
   * @param shapeType           the shape type
   * @param x                   the x
   * @param y                   the y
   * @param name                the name
   * @param r                   the r
   * @param g                   the g
   * @param b                   the b
   * @param horizontalDimension the horizontal dimension
   * @param verticalDimension   the vertical dimension
   * @return the shape
   */
  public static IShape createShape(String shapeType, double x, double y,
                                   String name, double r, double g, double b,
                                   double horizontalDimension, double verticalDimension) {
    switch (Utility.validateString(shapeType).toLowerCase()) {
      case "rectangle":
        return new Rectangle(x, y, Utility.validateString(name), r, g, b, horizontalDimension, verticalDimension);
      case "oval":
        return new Oval(x, y, Utility.validateString(name), r, g, b, horizontalDimension, verticalDimension);
      default:
        throw new IllegalArgumentException("Invalid shape type: " + shapeType);
    }
  }
}

