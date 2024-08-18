package photoalbumapp.model.shape;

import java.awt.*;
import java.awt.Color;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {

  /*public Oval(Point2D position, String name, Color color,
              double horizontalDimension, double verticalDimension) {
    super(position, name, color, horizontalDimension, verticalDimension);
    this.shapeType = "oval";
  }*/
  private double xRadius;
  private double yRadius;


  /**
   * Instantiates a new Oval.
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
  public Oval(double x, double y, String name, double r, double g, double b,
              double horizontalDimension, double verticalDimension) {
    super(x, y, name, r, g, b, horizontalDimension, verticalDimension);

    this.shapeType = "oval";
    this.xRadius = (this.horizontalDimension / 2);
    this.yRadius = (this.verticalDimension / 2);
  }

  /**
   * Instantiates a new Oval.
   *
   * @param original the original
   */
  public Oval(Oval original) {
    super(original);
    this.xRadius = original.getXRadius();
    this.yRadius = original.getYRadius();
  }

  @Override
  public String toString() {
    return super.toString()
            + "\nCenter: " /*+ "("
            + String.format("%.1f", this.position.getX()) + ", "
            + String.format("%.1f", this.position.getY()) + ")"*/
            + position.toString()
            + ", Y Radius " + String.format("%.1f", this.getYRadius())
            + ", X Radius: " + String.format("%.1f", this.getXRadius());
  }

  /**
   * Gets x radius.
   *
   * @return the x radius
   */
  public double getXRadius() {
    return this.xRadius;
  }

  /**
   * Gets y radius.
   *
   * @return the y radius
   */
  public double getYRadius() {
    return this.yRadius;
  }

  public void drawShape(Graphics g) {
    g.setColor(new Color((int) this.getColor().getRed(),
            ((int) this.getColor().getGreen()), ((int) this.getColor().getBlue())));
    g.fillOval((int) this.getPosition().getX(),
            (int) this.getPosition().getY(),
            (int) this.getHorizontalDimension(),
            (int) this.verticalDimension);
  }

}
