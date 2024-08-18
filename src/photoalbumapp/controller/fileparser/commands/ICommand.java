package photoalbumapp.controller.fileparser.commands;

import photoalbumapp.model.IPhotoAlbumModel;
import photoalbumapp.model.PhotoAlbumModel;

/**
 * The interface Command.
 */
public interface ICommand {
  /**
   * Execute.
   *
   * @param model the model
   */
  void execute(IPhotoAlbumModel model);
}
