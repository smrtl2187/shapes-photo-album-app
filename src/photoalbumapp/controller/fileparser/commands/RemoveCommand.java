package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;

/**
 * The type Remove command.
 */
public class RemoveCommand implements ICommand {
  private final String name;

  /**
   * Instantiates a new Remove command.
   *
   * @param name the name
   */
  public RemoveCommand(String name) {
    this.name = name;
  }

  @Override
  public void execute(IPhotoAlbumModel model) {
    // Code to execute remove command in model
    model.removeShape(this.name);
  }
}
