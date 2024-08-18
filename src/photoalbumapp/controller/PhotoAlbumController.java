package photoalbumapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import photoalbumapp.controller.fileparser.FileParser;
import photoalbumapp.controller.fileparser.commands.ICommand;
import photoalbumapp.model.IPhotoAlbumModel;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.view.IPhotoAlbumView;

/**
 * The type Photo album controller.
 */
public class PhotoAlbumController implements  IPhotoAlbumController {
  private final IPhotoAlbumModel model;
  private final IPhotoAlbumView view;
  private final FileParser parser = new FileParser();
  private final List<ICommand> commandList;
  private Map<String, ISnapshot> snapshotsMap;

  /**
   * Instantiates a new Photo album controller.
   *
   * @param model    the model
   * @param view     the view
   * @param fileName the file name
   * @throws IOException the io exception
   */
  public PhotoAlbumController(IPhotoAlbumModel model, IPhotoAlbumView view,
                              String fileName) throws IOException {
    Objects.requireNonNull(model, "model cannot be null");
    Objects.requireNonNull(view, "view cannot be null");
    Objects.requireNonNull(fileName, "fileName cannot be null");

    //parser creates & stores list of command objects from file
    this.commandList = this.parser.parseFile(fileName);

    this.model = model;
    this.snapshotsMap = model.getSnapshotsMap();
    this.view = view;
  }

  @Override
  public void go() {
    //model updated w/list of snapshots
    for (ICommand command : this.commandList) {
      command.execute(this.model);
    }

    //System.out.println(this.commandList); //definitely being added in order
    //System.out.println(this.model);

    this.view.generateView(this.snapshotsMap);
  }
}
