package photoalbumapp.controller;

import java.util.List;

import photoalbumapp.controller.fileparser.commands.ICommand;

/**
 * The interface Photo album controller.
 */
public interface IPhotoAlbumController {
  /**
   * Starts the application and connects the given model and view.
   */
  void go();
}
