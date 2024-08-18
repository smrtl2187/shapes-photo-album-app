package photoalbumapp;

import java.io.IOException;

import photoalbumapp.controller.IPhotoAlbumController;
import photoalbumapp.controller.PhotoAlbumController;
import photoalbumapp.model.IPhotoAlbumModel;
import photoalbumapp.model.PhotoAlbumModel;
import photoalbumapp.view.IPhotoAlbumView;
import photoalbumapp.view.graphical.GraphicalView;
import photoalbumapp.view.html.HTMLView;

/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {
  /*public static void main(String[] args) throws IOException {
    //parse command args
    //or use CommandLineReader() per TA Mark

    //Two examples: *can extract the fileName and the view here
    //MyProgram -in buildings.txt -out myWeb.html  -v web
    //MyProgram -in buildings.txt -v graphical 800 800

    //-view and -v are synonymous. Your program should support both command line "switches".
    //The xmax and ymax are optional integers that specify the bounds of the "view window".
    // If these attributes are not specified, a default value of 1000 is used for both
    // Also note that the "where output should go" is only relevant for the HTML view,
    // so it is optional (and ignored) for the Graphical (Swing) view.

    //just for testing web view - temporary, delete later
    //String fileName = "buildings.txt";
    String fileName = "demo_input.txt";

    String outFileName = "JARTestOutfile.html";

    //default window dimensions
    int width = 1000;
    int height = 1000;

    //instantiate MVC objects
    IPhotoAlbumModel model = new PhotoAlbumModel();

    //IPhotoAlbumView view = //write if/else or switch for graphical or web view
    //IPhotoAlbumView view = new HTMLView(outFileName); //this works!
    IPhotoAlbumView view = new GraphicalView(width, height);

    IPhotoAlbumController controller = new PhotoAlbumController(model, view, fileName);

    //launch MVC
    controller.go();
  }*/

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws IOException the io exception
   */
  public static void main(String[] args) throws IOException {
    String fileName = null;
    String outFileName = null;
    String viewOption = null;
    int width = 1000; //default width
    int height = 1000; //default height

    //parse command-line arguments
    for (int i = 0; i < args.length; i++) {
      //identify input file name after "-in"
      if (args[i].equals("-in")) {
        fileName = args[i + 1];
        //identify selected type of view after "-view" or "-v"
      } else if (args[i].equals("-view") || args[i].equals("-v")) {
        viewOption = args[i + 1]; //can be web or graphical
        if (viewOption.equals("graphical")) { //search for optional width and height for graphical
          if (args.length > i + 2) {
            width = Integer.parseInt(args[i + 2]);
            height = Integer.parseInt(args[i + 3]);
          }
        }
      } else if (args[i].equals("-out")) {
        outFileName = args[i + 1];
      }
    }
    if (fileName == null || viewOption == null) {
      throw new IllegalArgumentException("file name and/or view option cannot be null");
    }

    //instantiate MVC objects
    IPhotoAlbumModel model = new PhotoAlbumModel();

    //instantiate view based on view option
    IPhotoAlbumView view = null;
    if (viewOption.equals("graphical")) {
      view = new GraphicalView(width, height);
    }
    else if (viewOption.equals("web")) {
      view = new HTMLView(outFileName);
    }

    IPhotoAlbumController controller = new PhotoAlbumController(model, view, fileName);

    //launch MVC
    controller.go();
  }
}
