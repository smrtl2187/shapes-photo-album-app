package photoalbumapp.model.shape;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface IShape {
  /**
   * Sets x coordinate.
   *
   * @param x the x
   *//*
  void setXCoordinate(double x);

  *//**
   * Sets y coordinate.
   *
   * @param y the y
   *//*
  void setYCoordinate(double y);*/

  /**
   * Sets color.
   *
   * @param r the red value of a color
   * @param g the green value of a color
   * @param b the blue value of a color
   *//*
  void setColor(double r, double g, double b);*/

  /**
   * Sets vertical dimension.
   *
   * @param verticalDimension the vertical dimension
   */
  void setVerticalDimension(double verticalDimension);

  /**
   * Sets horizontal dimension.
   *
   * @param horizontalDimension the horizontal dimension
   */
  void setHorizontalDimension(double horizontalDimension);

  /**
   * Gets x coordinate.
   *
   * @return the x coordinate
   *//*
  double getX();

  *//**
   * Gets y coordinate.
   *
   * @return the y coordinate
   *//*
  double getY();*/

  /**
   * Gets color.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Gets vertical dimension.
   *
   * @return the vertical dimension
   */
  double getVerticalDimension();

  /**
   * Gets horizontal dimension.
   *
   * @return the horizontal dimension
   */
  double getHorizontalDimension();

  /**
   * Gets name of shape.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets shape type.
   *
   * @return the shape type
   */
  String getShapeType();

  /**
   * Gets position.
   *
   * @return the position
   */
  Point2D getPosition();

  /**
   * Returns a string representation of the shape that includes the following as an example.
   *     Name:
   *     Type:
   *     Color: (0.0, 0.0, 0.0)
   *     Reference Position (0.0, 0.0)
   *     Vertical Dimension: 0.0
   *     Horizontal Dimension: 0.0
   * @return String representation of a shape
   */
  String toString();
}