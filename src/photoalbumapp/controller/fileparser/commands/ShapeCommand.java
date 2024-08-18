package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Shape command.
 */
public class ShapeCommand implements ICommand {
  private final String name;
  private final String type;
  private final double x;
  private final double y;
  private final double horizontalDimension;
  private final double verticalDimension;
  private final double red;
  private final double green;
  private final double blue;

  /**
   * Instantiates a new Shape command.
   *
   * @param name                the name
   * @param type                the type
   * @param x                   the x
   * @param y                   the y
   * @param horizontalDimension the horizontal dimension
   * @param verticalDimension   the vertical dimension
   * @param red                 the red
   * @param green               the green
   * @param blue                the blue
   */
  public ShapeCommand(String name, String type, double x, double y,
                      double horizontalDimension, double verticalDimension,
                      double red, double green, double blue) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.horizontalDimension = horizontalDimension;
    this.verticalDimension = verticalDimension;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute shape command in model
    model.addShape(this.type, this.x, this.y, this.name,
            this.red, this. green, this.blue,
            this.horizontalDimension, this.verticalDimension);
  }
}
