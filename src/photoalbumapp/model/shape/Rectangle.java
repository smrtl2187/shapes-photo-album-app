package photoalbumapp.model.shape;

import java.awt.*;
import java.awt.Color;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {

  /*public Rectangle(Point2D position, String name, Color color,
                   double horizontalDimension, double verticalDimension) {
    super(position, name, color, horizontalDimension, verticalDimension);
    this.shapeType = "rectangle";
  }*/

  /**
   * Instantiates a new Rectangle.
   *
   * @param x                   the x
   * @param y                   the y
   * @param name                the name
   * @param r                   the r
   * @param g                   the g
   * @param b                   the b
   * @param horizontalDimension the horizontal dimension
   * @param verticalDimension   the vertical dimension
   */
  public Rectangle(double x, double y, String name, double r, double g, double b,
                   double horizontalDimension, double verticalDimension) {
    super(x, y, name, r, g, b, horizontalDimension, verticalDimension);
    this.shapeType = "rectangle";
  }

  /**
   * Instantiates a new Rectangle.
   *
   * @param original the original
   */
  public Rectangle(Rectangle original) {
    super(original);
  }

  @Override
  public String toString() {
    return super.toString()
            + "\nMinimum Corner: " + "("
            + String.format("%.1f", this.position.getX()) + ", "
            + String.format("%.1f", this.position.getY()) + ")"
            + ", Height: " + String.format("%.1f", this.getVerticalDimension())
            + ", Width: " + String.format("%.1f", this.getHorizontalDimension());
  }

  public void drawShape(Graphics g) {
    g.setColor(new Color((int) this.getColor().getRed(),
            ((int) this.getColor().getGreen()), ((int) this.getColor().getBlue())));
    g.fillRect((int) this.getPosition().getX(),
            (int) this.getPosition().getY(),
            (int) this.getHorizontalDimension(),
            (int) this.verticalDimension);
  }

}
