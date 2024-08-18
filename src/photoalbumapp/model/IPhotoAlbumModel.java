package photoalbumapp.model;

import photoalbumapp.model.editor.IEditor;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.model.shape.IShape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This interface contains all operations that a Photo Album application should support.
 */
public interface IPhotoAlbumModel {
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
   * Gets editor.
   *
   * @return the editor
   */
  IEditor getEditor();

  /**
   * Gets shapes info.
   *
   * @return the shapes info
   */
  String getShapesInfo();

  /**
   * Clear editor.
   */
  void clearEditor();

  /**
   * Take snapshot.
   *
   * @param description the description
   */
  void takeSnapshot(String description);

  /**
   * Gets snapshots map.
   *
   * @return the snapshots map
   */
  Map<String,ISnapshot> getSnapshotsMap();

  /**
   * Gets snapshots list.
   *
   * @return the snapshots list
   */
  List<ISnapshot> getSnapshotsList();

  /**
   * Gets snapshot.
   *
   * @param num the num
   * @return the snapshot
   */
  ISnapshot getSnapshot(int num);

  /**
   * Gets snapshot i ds.
   *
   * @return the snapshot i ds
   */
  List<String> getSnapshotIDs();

  /**
   * Gets snapshot.
   *
   * @param snapshotID the snapshot id
   * @return the snapshot
   */
  ISnapshot getSnapshot(String snapshotID);

  /**
   * Returns a string representation of the photo album in the following format as an example.
   *    Printing Snapshots
   *    *prints toString of each Snapshot in PhotoAlbum*
   * @return a string representation of the photo album
   */
  String toString();
}
