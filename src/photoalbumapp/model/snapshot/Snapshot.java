package photoalbumapp.model.snapshot;

import photoalbumapp.model.shape.IShape;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The type Snapshot.
 */
public class Snapshot implements ISnapshot {
  //private final String snapshotID;
  private final LocalDateTime timestamp;
  //private final String formattedTimestamp;
  private final String description;
  private List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description
   * @param shapes      the shapes
   */
  public Snapshot(String description, List<IShape> shapes) {
    checkNull(shapes);

    this.description = description; //optional description, can be null or empty string
    this.shapes = shapes;

    LocalDateTime timestamp = LocalDateTime.now(); //generate timestamp object
    this.timestamp = timestamp;
    //this.snapshotID = String.valueOf(timestamp); //store snapshotID string
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //this.formattedTimestamp = timestamp.format(formatter); //format & store timestamp string
  }

  /**
   * Instantiates a new Snapshot.
   *
   * @param original the original
   */
  public Snapshot(Snapshot original) {
    checkNull(original);

    LocalDateTime timestamp = LocalDateTime.now(); //generate timestamp object
    this.timestamp = timestamp;
    //this.snapshotID = String.valueOf(timestamp); //store snapshotID string
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //this.timestamp = timestamp.format(formatter); //format & store timestamp string
    this.description = original.description;
    this.shapes = new ArrayList<>(original.shapes);
  }

 /* public static class Main {
    public static void main(String[] args) {
      System.out.println(new Snapshot("hello", null).timestamp);
      //08-04-2023 18:36:23
      System.out.println(new Snapshot("hello", null).snapshotID);
      //2023-04-08T18:28:41.870728
    }
  }*/

  /**
   * Checks null object parameter.
   * @param obj object
   * @throws IllegalArgumentException if null object passed in
   */
  private void checkNull(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("cannot be null");
    }
  }

  /*private String validateString(String string) {
    if (string == null || string.equals("")) {
      throw new IllegalArgumentException("cannot be null or empty string");
    } else return string;
  }*/

  @Override
  public String getSnapshotID() {
    return String.valueOf(this.timestamp);
  }

  @Override
  public String getTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return this.timestamp.format(formatter);
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public List<IShape> getShapes() {

    return Collections.unmodifiableList(this.shapes);
  }

  @Override
  public String toString() {
    //create string for list of shapes info
    String shapesString = "";
    for (IShape each: shapes) {
      shapesString += each.toString() + "\n";
    }
    //return full string representation of snapshot
    return "\nSnapshotID: " + this.getSnapshotID()
            + "\nTimestamp: " + this.getTimestamp()
            + "\nDescription: " + this.getDescription()
            + "\nShape Information:\n" + shapesString
            + "\n";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) { //if same object instance,
      return true; //return true
    }
    if (!(other instanceof Snapshot)) {
      return false; //if null or not same class, return false
    } //else
    Snapshot that = (Snapshot) other; //cast to compare class attributes
    return //Objects.equals(this.snapshotID, that.snapshotID)
            //&& Objects.equals(this.timestamp, that.timestamp)
            /*&&*/ Objects.equals(this.description, that.description)
            && Objects.equals(this.shapes, that.shapes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.description, this.shapes);
  }
}
