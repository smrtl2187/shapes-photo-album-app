package photoalbumapp.model.snapshot;

import photoalbumapp.model.shape.IShape;

import java.util.List;

/**
 * This interface contains all operations that all types of Snapshots should support.
 */
public interface ISnapshot {
  /**
   * Gets snapshot id.
   *
   * @return the snapshot id
   */
  String getSnapshotID();

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  String getTimestamp();

  /**
   * Gets description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  List<IShape> getShapes();

  /**
   * Returns a string representation of the snapshot that includes the following as an example.
   *     Snapshot ID:
   *     Timestamp:
   *     Description:
   *     Shape Information:
   *     *prints toString of each Shape in Snapshot*
   * @return String representation of a snapshot
   */
  String toString();
}
