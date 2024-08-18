package photoalbumapp.view;

import java.util.List;
import java.util.Map;

import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.model.snapshot.Snapshot;

/**
 * The interface Photo album view.
 */
public interface IPhotoAlbumView {
  /**
   * Generate view.
   * @param snapshotsMap the snapshots map
   */
  void generateView(Map<String,ISnapshot> snapshotsMap);
}
