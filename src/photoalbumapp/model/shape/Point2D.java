package photoalbumapp.model.shape;

import java.util.Objects;

/**
 * This class represents a Point in 2D cartesian space.
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Instantiates a new Point 2 d.
   *
   * @param x the x
   * @param y the y
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Instantiates a new Point 2 d.
   *
   * @param other the other
   */
  public Point2D(Point2D other) {
    this.x = other.x;
    this.y = other.y;
  }

  /**
   * Sets x.
   *
   * @param x the x
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Sets y.
   *
   * @param y the y
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public double getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) { //if same object instance,
      return true; //return true
    }
    if (!(other instanceof Point2D)) {
      return false; //if null or not same class, return false
    } //else
    Point2D that = (Point2D) other; //cast to compare class attributes
    return Double.compare(this.getY(), that.getY()) == 0
            && Double.compare(this.getX(), that.getX()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public String toString() {
    return "("
            + String.format("%.1f", this.getX()) + ", "
            + String.format("%.1f", this.getY()) + ")";
  }
}