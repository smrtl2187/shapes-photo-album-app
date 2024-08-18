package photoalbumapp.model.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import photoalbumapp.model.shape.AbstractShape;
import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Rectangle;
import photoalbumapp.model.shape.ShapeFactory;
import photoalbumapp.model.snapshot.ISnapshot;

/**
 * The type Editor. Used for shape manipulation.
 */
public class Editor implements IEditor {
  private Map<String, IShape> shapes; //shapes mapped to their string names

  //Objects.requireNonNull(); use this to checkNull instead

  /**
   * Instantiates a new Editor.
   */
  public Editor() {
    this.shapes = new LinkedHashMap<>();
  }

  @Override
  public void addShape(String shapeType, double x, double y, String name,
                       double r, double g, double b,
                       double horizontalDimension, double verticalDimension) {

    //ensure unique string name when creating/adding shape
    IShape aShape = ShapeFactory.createShape(shapeType, x, y,
            validateUniqueName(name), r, g, b, horizontalDimension, verticalDimension);

    //put new shape in hashmap mapped to its unique name
    this.shapes.put(aShape.getName(), aShape);
  }

  private String validateUniqueName(String name) {
    //if name not in list, name is unique and can be used
    if (!this.shapes.containsKey(name)) {
      return name;
    } else throw new IllegalArgumentException("name should be unique");
  }

  @Override
  public void removeShape(String name) {
    Objects.requireNonNull(name, "cannot be null");
    this.shapes.remove(validateShape(name));
  }

  private String validateShape(String name) {
    //validates the shape currently exists in the editor
    if (this.shapes.containsKey(name)) {
      return name;
    } else throw new NoSuchElementException("shape not found");
  }

  @Override
  public void recolorShape(String name, double r, double g, double b) {
    Objects.requireNonNull(name, "cannot be null");
    this.shapes.get(validateShape(name)).getColor().setColor(r, g, b);
  }

  @Override
  public void resizeShape(String name, double verticalDimension, double horizontalDimension) {
    Objects.requireNonNull(name, "cannot be null");
    this.shapes.get(validateShape(name)).setVerticalDimension(verticalDimension);
    this.shapes.get(validateShape(name)).setHorizontalDimension(horizontalDimension);
  }

  @Override
  public void moveShape(String name, double x, double y) {
    Objects.requireNonNull(name, "cannot be null");
    this.shapes.get(validateShape(name)).getPosition().setX(x);
    this.shapes.get(validateShape(name)).getPosition().setY(y);
  }

  @Override
  public Map<String, IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public List<IShape> getShapesList() {
    //List<IShape> shapesList = new ArrayList<>(this.shapes.values());
    //List<IShape> unmodifiableShapesList = Collections.unmodifiableList(shapesList);

    List<IShape> copiedShapesList = new ArrayList<>();

    // Loop through the original shapes list and make a deep copy of each shape
    for (IShape shape : this.getShapes().values()) {
      if (shape instanceof Rectangle) {
        copiedShapesList.add(new Rectangle((Rectangle) shape));
      } else if (shape instanceof Oval) {
        copiedShapesList.add(new Oval((Oval) shape));
      } else throw new IllegalArgumentException("Invalid Shape");
    }
    return copiedShapesList;
  }

  @Override
  public void clear() { //clears all shapes from editor
    this.shapes.clear();
  }

  @Override
  public String toString() {
    //create string for list of shapes info
    String shapesString = "";
    for (IShape each: shapes.values()) {
      shapesString += each.toString();
    }
    //return string representation of the shapes currently in the editor
    return shapesString;
  }
}
