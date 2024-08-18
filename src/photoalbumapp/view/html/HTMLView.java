package photoalbumapp.view.html;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Rectangle;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.view.IPhotoAlbumView;

/**
 * The type Html view.
 */
public class HTMLView implements IPhotoAlbumView {
  private final String outFileName;
  private List<ISnapshot> snapshots;

  /**
   * Instantiates a new Html view.
   *
   * @param outFileName the out file name
   */
  public HTMLView(String outFileName) {
    //this.outFileName = fileName.replace(".txt", "Out.html");
    this.outFileName = outFileName;
  }

  @Override
  public void generateView(Map<String,ISnapshot> snapshotsMap) {
    this.snapshots = new ArrayList<>(snapshotsMap.values());

    StringBuilder htmlBuilder = new StringBuilder();

    // write the HTML header/setup
    htmlBuilder.append("""
                <!DOCTYPE html>
                <html>
                <head>
                <style>
                .snapshot {
                    border: 5px outset red;
                    background-color: lightblue;
                }
                </style>
                </head>
                <body>
                <h1>CS5004 Shapes Photo Album Viewer</h1>
                """);

    // write SVG for each snapshot
    for (ISnapshot each : this.snapshots) {
      // write the snapshot header
      htmlBuilder.append(
              "<div class=\"snapshot\">\n"
                      + "<h2>" + each.getSnapshotID() + "</h2>\n"
                      + "<h3>" + "Description: " + each.getDescription() + "</h3>\n"
                      + "<p>" + "</p>\n"
                      + "<svg width=\"1000\" height=\"1000\">\n"
      );

      // write shapes for the snapshot
      for (IShape shape : each.getShapes()) {
        if (shape instanceof Rectangle) {
          Rectangle r = (Rectangle) shape;
          htmlBuilder.append("<rect id=\"" + shape.getName()
                  + "\" x=\"" + r.getPosition().getX()
                  + "\" y=\"" + r.getPosition().getY()
                  + "\" width=\"" + r.getHorizontalDimension()
                  + "\" height=\"" + r.getVerticalDimension()
                  + "\" fill=\"rgb(" + r.getColor().getRed() + ","
                  + r.getColor().getGreen() + "," + r.getColor().getBlue() + ")"
                  + "\" visibility=\"visible\">\n</rect>\n");
        } else if (shape instanceof Oval) {
          Oval o = (Oval) shape;
          htmlBuilder.append("<ellipse id=\"" + shape.getName() + "\" cx=\""
                  + o.getPosition().getX() + "\" cy=\"" + o.getPosition().getY() + "\" rx=\""
                  + o.getXRadius() + "\" ry=\"" + o.getYRadius()
                  + "\" fill=\"rgb(" + o.getColor().getRed() + ","
                  + o.getColor().getGreen() + "," + o.getColor().getBlue() + ")"
                  + "\" visibility=\"visible\">\n</ellipse>\n");
        }
      }

      // write the snapshot footer
      htmlBuilder.append("</svg>\n");
      htmlBuilder.append("</div>\n");
    }

    // write the HTML footer
    htmlBuilder.append("</body>\n");
    htmlBuilder.append("</html>\n");

    //try (PrintWriter writer = new PrintWriter(outFileName)) {
    try (FileWriter writer = new FileWriter(outFileName)) {
      writer.write(htmlBuilder.toString());
      System.out.println("HTML file created");
    } catch (IOException e) {
      System.out.println("Error writing HTML file: " + e.getMessage());
    }
  }
}
