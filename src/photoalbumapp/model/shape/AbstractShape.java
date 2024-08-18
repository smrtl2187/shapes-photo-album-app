package photoalbumapp.model.shape;

import java.util.Objects;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  /**
   * The Position.
   */
  protected Point2D position;
  /**
   * The Color.
   */
  protected Color color;
  /**
   * The Name.
   */
  protected String name;
  /**
   * The Shape type.
   */
  protected String shapeType;
  /**
   * The Horizontal dimension.
   */
  protected double horizontalDimension;
  /**
   * The Vertical dimension.
   */
  protected double verticalDimension;

  /**
   * Instantiates a new Abstract shape.
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
  protected AbstractShape(double x, double y,
                       String name, double r, double g, double b,
                       double horizontalDimension, double verticalDimension) {
    //checkNull(position);
    this.position = new Point2D(x, y);

    //checkNull(color);
    this.color = new Color(r, g, b);
    this.name = validateString(name);

    this.shapeType = ""; //initialize to empty string
    this.verticalDimension = isNonNegative(verticalDimension);
    this.horizontalDimension = isNonNegative(horizontalDimension);
  }

  /**
   * Instantiates a new Abstract shape.
   *
   * @param original the original
   */
  protected AbstractShape(IShape original) {
    this.position = new Point2D(original.getPosition());
    this.color = new Color(original.getColor());
    this.name = original.getName();
    this.shapeType = original.getShapeType();
    this.horizontalDimension = original.getHorizontalDimension();
    this.verticalDimension = original.getVerticalDimension();
  }

  /**
   * Checks null object parameter.
   *
   * @param obj object
   * @throws IllegalArgumentException if null object passed in
   */
  protected void checkNull(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("cannot be null");
    }
  }

  /**
   * Validates and returns non-null string.
   *
   * @param string string
   * @return validated string
   * @throws IllegalArgumentException if null string passed in
   */
  protected String validateString(String string) {
    if (string == null || string.equals("")) {
      throw new IllegalArgumentException("cannot be null or empty string");
    } else return string;
  }

  /**
   * Is non negative double.
   *
   * @param value the value
   * @return the double
   */
  protected double isNonNegative(double value) {
    if (value < 0) throw new IllegalArgumentException("cannot be negative");
    return value;
  }

  @Override
  public void setVerticalDimension(double verticalDimension) {
    this.verticalDimension = isNonNegative(verticalDimension);
  }

  @Override
  public void setHorizontalDimension(double horizontalDimension) {
    this.horizontalDimension = isNonNegative(horizontalDimension);
  }

  @Override
  public double getVerticalDimension() {
    return this.verticalDimension;
  }

  @Override
  public double getHorizontalDimension() {
    return this.horizontalDimension;
  }

  /*@Override
  public void setXCoordinate(double x) {
    this.position.setX(x);
  }

  @Override
  public void setYCoordinate(double y) {
    this.position.setY(y);
  }*/

  /*@Override
  public void setColor(double r, double g, double b) {
    this.color.setColor(r, g, b);
  }*/

  /*@Override
  public double getX() {
    return this.position.getX();
  }

  @Override
  public double getY() {
    return this.position.getY();
  }*/

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public Point2D getPosition() {
    return this.position;
  }

  @Override
  public String toString() {
    return "\nName: " + this.getName()
            + "\nType: " + this.getShapeType()
            + color.toString()
            /*+ "\nColor: " + "("
            + String.format("%.1f", this.getColor().getRed()) + ", "
            + String.format("%.1f",this.getColor().getGreen()) + ", "
            + String.format("%.1f",this.getColor().getBlue()) + ")"*/;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) { //if same object instance,
      return true; //return true
    }
    if (!(other instanceof IShape)) {
      return false; //if null or not same class, return false
    } //else
    IShape that = (IShape) other; //cast to compare class attributes
    return this.color.equals(that.getColor())
            && this.position.equals(that.getPosition())
            && Objects.equals(this.name, that.getName())
            && Objects.equals(this.shapeType, that.getShapeType())
            && Double.compare(this.verticalDimension, that.getVerticalDimension()) == 0
            && Double.compare(this.horizontalDimension, that.getHorizontalDimension()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.color, this.position, this.name, this.shapeType,
    this.verticalDimension, this.horizontalDimension);
  }
}
