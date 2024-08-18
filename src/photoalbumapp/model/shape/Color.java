package photoalbumapp.model.shape;

import java.util.Objects;

/**
 * This class represents a Color using RGB values.
 */
public class Color {
  private double red; //amount of red light
  private double green; //amount of green light
  private double blue; //amount of blue light

  /**
   * The constant COLOR_MAX_VALUE.
   */
  public static final double COLOR_MAX_VALUE = 255;
  /**
   * The constant COLOR_MIN_VALUE.
   */
  public static final double COLOR_MIN_VALUE = 0;

  /**
   * Instantiates a new Color.
   *
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  public Color(double red, double green, double blue) {
    this.red = validateColorValue(red);
    this.green = validateColorValue(green);
    this.blue = validateColorValue(blue);
  }

  /**
   * Instantiates a new Color.
   *
   * @param other the other
   */
  public Color(Color other) {
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
  }

  /**
   * Returns a validated single RGB value (valid range: 0-255).
   * @param value a single RBB value
   * @return the validated single RGB value
   */
  private double validateColorValue(double value) {
    if (value < COLOR_MIN_VALUE || value > COLOR_MAX_VALUE) {
      throw new IllegalArgumentException("invalid RGB value");
    }
    return value;
  }

  /**
   * Sets color.
   *
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  public void setColor(double red, double green, double blue) {
    this.red = validateColorValue(red);
    this.green = validateColorValue(green);
    this.blue = validateColorValue(blue);
  }

  /**
   * Gets red.
   *
   * @return the red
   */
  public double getRed() {
    return red;
  }

  /**
   * Gets green.
   *
   * @return the green
   */
  public double getGreen() {
    return green;
  }

  /**
   * Gets blue.
   *
   * @return the blue
   */
  public double getBlue() {
    return blue;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) { //if same object instance,
      return true; //return true
    }
    if (!(other instanceof Color)) {
      return false; //if null or not same class, return false
    } //else
    Color that = (Color) other; //cast to compare class attributes
    return Double.compare(this.getBlue(), that.getBlue()) == 0
            && Double.compare(this.getGreen(), that.getGreen()) == 0
            && Double.compare(this.getRed(), that.getRed()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.blue, this.green, this.red);
  }

  @Override
  public String toString() {
    return "\nColor: " + "("
            + String.format("%.1f", this.getRed()) + ", "
            + String.format("%.1f",this.getGreen()) + ", "
            + String.format("%.1f",this.getBlue()) + ")";
  }
}

