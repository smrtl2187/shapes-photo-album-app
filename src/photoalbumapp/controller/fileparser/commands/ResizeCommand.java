package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Resize command.
 */
public class ResizeCommand implements ICommand {
  private final String name;
  private final double horizontalDimension;
  private final double verticalDimension;

  /**
   * Instantiates a new Resize command.
   *
   * @param name                the name
   * @param horizontalDimension the horizontal dimension
   * @param verticalDimension   the vertical dimension
   */
  public ResizeCommand(String name, double horizontalDimension, double verticalDimension) {
    this.name = name;
    this.horizontalDimension = horizontalDimension;
    this.verticalDimension = verticalDimension;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute resize command in model
    model.resizeShape(this.name, this.verticalDimension, this.horizontalDimension);
  }
}
