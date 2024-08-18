package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Snapshot command.
 */
public class SnapshotCommand implements ICommand {
  private final String description;

  /**
   * Instantiates a new Snapshot command.
   *
   * @param description the description
   */
  public SnapshotCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute snapshot command in model
    model.takeSnapshot(this.description);
  }
}
