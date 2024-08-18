package photoalbumapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import photoalbumapp.Utility;
import photoalbumapp.model.editor.Editor;
import photoalbumapp.model.editor.IEditor;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.model.snapshot.Snapshot;
import photoalbumapp.model.shape.IShape;

/**
 * The type Photo album model.
 */
public class PhotoAlbumModel implements IPhotoAlbumModel {
  private IEditor editor;
  private Map<String,ISnapshot> snapshotsMap; //ID mapped to snapshot

  /**
   * Instantiates a new Photo album model.
   */
  public PhotoAlbumModel() {
    this.editor = new Editor();
    this.snapshotsMap = new LinkedHashMap<>();
  }

  @Override
  public void addShape(String shapeType, double x, double y, String name,
                       double r, double g, double b,
                       double horizontalDimension, double verticalDimension) {

    this.editor.addShape(shapeType, x, y, name, r, g, b,
            horizontalDimension, verticalDimension);
  }

  @Override
  public void removeShape(String name) {
    Objects.requireNonNull(name, "cannot be null");
    this.editor.removeShape(name);
  }

  @Override
  public void recolorShape(String name, double r, double g, double b) {
    Objects.requireNonNull(name, "cannot be null");
    this.editor.recolorShape(name, r, g, b);
  }

  @Override
  public void resizeShape(String name, double verticalDimension, double horizontalDimension) {
    Objects.requireNonNull(name, "cannot be null");
    this.editor.resizeShape(name, verticalDimension, horizontalDimension);
  }

  @Override
  public void moveShape(String name, double x, double y) {
    Objects.requireNonNull(name, "cannot be null");
    this.editor.moveShape(name, x, y);
  }

  @Override
  public String getShapesInfo() { //returns shape info of shapes currently in editor
    return this.editor.toString();
  }

  @Override
  public void clearEditor() {
    this.editor.clear();
  }

  @Override
  public IEditor getEditor() {
    return this.editor;
  }

  @Override
  public void takeSnapshot(String description) { //snapshot description is optional
    Objects.requireNonNull(description, "cannot be null"); //but can be empty string
    //Collection<IShape> shapes = this.editor.getShapes().values();
    //List<IShape> list = new ArrayList<>(shapes);

    //List<IShape> shapesList = new ArrayList<>(this.editor.getShapes().values());
    //List<IShape> unmodifiableShapesList = Collections.unmodifiableList(shapesList);

    ISnapshot snapshot = new Snapshot(description, this.editor.getShapesList());
    snapshotsMap.put(snapshot.getSnapshotID(), snapshot);
  }

  @Override
  public Map<String,ISnapshot> getSnapshotsMap() {
    return this.snapshotsMap;
  }

  @Override
  public List<ISnapshot> getSnapshotsList() {
    return new ArrayList<>(this.getSnapshotsMap().values());
  }

  @Override
  public List<String> getSnapshotIDs() {
    return new ArrayList<>(snapshotsMap.keySet());
  }

  @Override
  public ISnapshot getSnapshot(String snapshotID) {
    Utility.validateString(snapshotID);
    if (!snapshotsMap.containsKey(snapshotID)) {
      throw new NoSuchElementException("Snapshot not found");
    }
    return snapshotsMap.get(snapshotID);
  }

  @Override
  public ISnapshot getSnapshot(int num) {
    //calculate index to search in arraylist; ex: snapshot 1 is at index 0
    int index = num - 1;
    //check if valid index
    if (index < this.getSnapshotsList().size()) {
      //returns element at that index
      return getSnapshotsList().get(index);
    } else throw new IndexOutOfBoundsException("index out of bounds");
  }

  @Override
  public String toString() {
    //create string for list of snapshots info
    String snapshotsString = "";
    for (ISnapshot each: this.getSnapshotsList()) {
      snapshotsString += each.toString();
    }
    //return string representation of photo album
    return "Printing Snapshots\n"
            + snapshotsString;
  }
}
