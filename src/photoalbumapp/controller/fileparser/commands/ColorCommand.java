package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Color command.
 */
public class ColorCommand implements ICommand {
  private final String name;
  private final double red;
  private final double green;
  private final double blue;

  /**
   * Instantiates a new Color command.
   *
   * @param name  the name
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  public ColorCommand(String name, double red, double green, double blue) {
    this.name = name;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute color command in model
    model.recolorShape(this.name, this.red, this.green, this.blue);
  }
}