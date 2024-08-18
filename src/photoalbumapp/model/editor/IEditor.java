package photoalbumapp.model.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import photoalbumapp.model.shape.IShape;

/**
 * The interface Editor.
 */
public interface IEditor {
  /**
   * Add shape.
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
   */
  void addShape(String shapeType, double x, double y, String name, double r, double g, double b,
                double horizontalDimension, double verticalDimension);

  /**
   * Remove shape.
   *
   * @param name the name
   */
  void removeShape(String name);

  /**
   * Recolor shape.
   *
   * @param name the name
   * @param r    the r
   * @param g    the g
   * @param b    the b
   */
  void recolorShape(String name, double r, double g, double b);

  /**
   * Resize shape.
   *
   * @param name                the name
   * @param verticalDimension   the vertical dimension
   * @param horizontalDimension the horizontal dimension
   */
  void resizeShape(String name, double verticalDimension, double horizontalDimension);

  /**
   * Move shape.
   *
   * @param name the name
   * @param x    the x
   * @param y    the y
   */
  void moveShape(String name, double x, double y);

  /**
   * Gets shapes list.
   *
   * @return the shapes list
   */
  List<IShape> getShapesList();

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  Map<String, IShape> getShapes();

  /**
   * Clear.
   */
  void clear();
  String toString();
}
