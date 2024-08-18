package photoalbumapp.view.graphical;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import photoalbumapp.model.shape.IShape;
import photoalbumapp.model.shape.Oval;
import photoalbumapp.model.snapshot.ISnapshot;
import photoalbumapp.view.IPhotoAlbumView;
//import photoalbumapp.view.graphical.shapespanels.OvalPanel;
//import photoalbumapp.view.graphical.shapespanels.RectanglePanel;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame implements IPhotoAlbumView {
  //window dimensions
  private static int width;
  private static int height;

  //buttons in button panel
  private JButton selectButton;
  private JButton previousButton;
  private JButton nextButton;
  private JButton quitButton;

  //snapshot-related attributes
  private JPanel cardsPanel; //container for snapshots panels on frame
  private JPanel infoPanel; //container for snapshot info
  private JLabel snapshotLabel;
  /**
   * The Snapshot panels list.
   */
  java.util.List<JPanel> snapshotPanelsList = new ArrayList<>(); //list of JPanels containing snapshots
  //java.util.List<JLabel> snapshotLabelsList = new ArrayList<>(); //list of JLabels w/snapshot info
  private Map<String, ISnapshot> snapshotsMap; //snapshots mapped to their snapshotID
  private int currentSnapshotIndex; // current index in the map of the snapshot being displayed

  /**
   * Instantiates a new Graphical view.
   *
   * @param width  the width
   * @param height the height
   */
  public GraphicalView(int width, int height) { //instantiates the GUI window
    this.width = width;
    this.height = height;

    //set up frame
    setTitle("CS5004 Shapes Photo Album Viewer"); //set title bar text
    //setPreferredSize(new Dimension(1000, 1000)); //maybe these should be input param?
    setSize(new Dimension(width, height));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window when exit clicked

    //set up buttons to be placed in buttonPanel
    selectButton = new JButton("^^ Select ^^");
    previousButton = new JButton("<< Previous <<");
    nextButton = new JButton(">> Next >>");
    quitButton = new JButton("xx Quit xx");

    //add buttons to buttonPanel
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(previousButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(quitButton);

    //create BorderLayout for frame and place button panel at bottom of frame
    setLayout(new BorderLayout());
    add(buttonPanel, BorderLayout.SOUTH);

    //create JPanel w/card layout to contain snapshot panels
    cardsPanel = new JPanel(new CardLayout());
    add(cardsPanel, BorderLayout.CENTER);
    cardsPanel.setBackground(Color.lightGray);

    //create JPanel to contain snapshot info label
    infoPanel = new JPanel();
    add(infoPanel, BorderLayout.NORTH);
    infoPanel.add(snapshotLabel = new JLabel("Snapshot info test"));
    infoPanel.setPreferredSize(new Dimension(width, 40));
    infoPanel.setBackground(Color.PINK);
    snapshotLabel.setBounds(0, 0, width, 35);
    snapshotLabel.setForeground(Color.BLACK);

    //set frame visible and resize appropriately
    setVisible(true);
    //pack();
  }

  private void createSnapshotPanel(ISnapshot snapshot, int index) {
    //set up snapshotPanel in center of frame & add JLabel for snapshot info
    SnapshotPanel snapshotPanel = new SnapshotPanel(snapshot);
    //snapshotPanel.setBackground(Color.BLACK);
    snapshotPanel.setLayout(null); //to use absolute coordinates
    snapshotPanel.setPreferredSize(new Dimension(width, height));
    snapshotPanel.setOpaque(true);
    snapshotPanel.setVisible(false);

    snapshotPanel.revalidate();
    snapshotPanel.repaint();

    //add panel&label to corresponding class attributes
    snapshotPanelsList.add(snapshotPanel);
    //snapshotLabelsList.add(snapshotLabel);

    // add snapshot panel as a card to the cards panel
    cardsPanel.add(snapshotPanel, index);
  }

  private void displaySnapshot(int index) {
    hideSnapshot(currentSnapshotIndex); // hide the currently displayed snapshot
    currentSnapshotIndex = index; //set new current index

    //for snapshot display
    snapshotPanelsList.get(index).setVisible(true);

    String key = snapshotsMap.keySet().toArray()[index].toString();
    ISnapshot snapshot = snapshotsMap.get(key);
    snapshotLabel.setText("<html>" + snapshot.getSnapshotID()
            + "<br>" + snapshot.getDescription() + "</html>");
  }

  private void hideSnapshot(int index) {
    //snapshotLabelsList.get(index).setVisible(false);
    snapshotPanelsList.get(index).setVisible(false);
  }

  @Override
  public void generateView(Map<String, ISnapshot> snapshotsMap) {
    // Save the snapshots map to the instance variable
    this.snapshotsMap = snapshotsMap;

    //create JPanels for each snapshot
    for (int i = 0; i < snapshotsMap.size(); i++) {
      String key = snapshotsMap.keySet().toArray()[i].toString();
      //System.out.println(key); //key at index i
      createSnapshotPanel(snapshotsMap.get(key), i);
    }

    // set default snapshot to the first snapshot in the map
    displaySnapshot(0);

    //System.out.println(snapshotPanelsList); //successfully created

    //add action listeners to buttons
    selectButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object[] options = snapshotsMap.keySet().toArray();

        String selectedOption = (String) JOptionPane.showInputDialog(
                null, "Choose", "Menu",
                JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (selectedOption != null) {
          ISnapshot selectedSnapshot = snapshotsMap.get(selectedOption);
          displaySnapshot(new ArrayList<>(snapshotsMap.values()).indexOf(selectedSnapshot));
        }
      }
    });

    previousButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if ((currentSnapshotIndex - 1) < 0) { //index out of bounds
          JOptionPane.showMessageDialog(null,
                  "No snapshots to show beyond this point.",
                  "Message", JOptionPane.INFORMATION_MESSAGE);
        } else displaySnapshot(currentSnapshotIndex - 1);
      }
    });

    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if ((currentSnapshotIndex + 1) > snapshotsMap.size() - 1) { //index out of bounds
          JOptionPane.showMessageDialog(null,
                  "No snapshots to show beyond this point.",
                  "Message", JOptionPane.INFORMATION_MESSAGE);
        } else displaySnapshot(currentSnapshotIndex + 1);
      }
    });

    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0); //quits application
      }
    });
  }

}
