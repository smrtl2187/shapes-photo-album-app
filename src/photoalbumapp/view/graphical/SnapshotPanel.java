package photoalbumapp.view.graphical;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.shape.Rectangle;
import photoalbumapp.model.snapshot.ISnapshot;

/**
 * The type Snapshot panel.
 */
public class SnapshotPanel extends JPanel {
  private List<IShape> shapes;

  /**
   * Instantiates a new Snapshot panel.
   *
   * @param snapshot the snapshot
   */
  public SnapshotPanel(ISnapshot snapshot) {
    this.shapes = snapshot.getShapes();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    //draw shapes on snapshot panel
    if (shapes != null) {
      for (IShape shape : shapes) {
        if (shape instanceof Rectangle) {
          ((Rectangle) shape).drawShape(g);
        } else if (shape instanceof Oval) {
          ((Oval) shape).drawShape(g);
        }
      }
    }
  }
}