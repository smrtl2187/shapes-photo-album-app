package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Move command.
 */
public class MoveCommand implements ICommand {
  private final String name;
  private final double x;
  private final double y;

  /**
   * Instantiates a new Move command.
   *
   * @param name the name
   * @param x    the x
   * @param y    the y
   */
  public MoveCommand(String name, double x, double y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute move command in model
    model.moveShape(this.name, this.x, this.y);
  }
}
