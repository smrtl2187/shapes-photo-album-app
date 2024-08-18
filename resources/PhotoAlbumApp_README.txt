Description: 
This project is a model of a photo album application. The application is able to add shapes to itself, manipulate the shapes by recoloring, resizing, or moving their reference positions in cartesian space. A user can also delete the shapes.

Aside from shape manipulation, this application also supports snapshot functionality, in that a user can take a snapshot of the shapes they are manipulating in the application and can thereafter, select a snapshot that has been stored in the application. The snapshot contains a list of shapes and their corresponding information that is further described in the UML diagram.

A user can also reset the whole application by deleting all shapes and snapshots stored by the application.

Structure:
It is comprised of three interfaces: 
IPhotoAlbumApp, which contains the methods required in the application
ISnapshot, which contains the methods required in a snapshot taken by the application
IShape, which contains the methods required in each shape to be manipulated in the application

IPhotoAlbum and ISnapshot currently have one implementing concrete classes each, though other types of PhotoAlbums and Snapshots can be created if needed using these interfaces.

The AbstractShape class is compromised of a Color class object for an RGB color value, a Point2D object for a reference position in cartesian space, as well as primitive type attributes that is further explained in the UML.

Currently two types of shapes (Rectangle and Oval) have been implemented but other shapes can be added by extending the abstract class. Should other objects have other dimensions, they can be implemented in their corresponding concrete classes. However, I decided to create an Abstract Class as all shapes were required to be resized, so all shapes would need a horizontal and vertical dimensions to enable resizing of a shape.

Changes from previous submission:
Model for photo album application was modified in the following ways:
LinkedHashMap was used instead of previous data structure in order to maintain the insertion order of both the snapshots in the model as well as the shapes in the editor.

A new class called Editor is used to hold the list of shapes in the model and the methods used for shape manipulation. The model is then composed of an instance of this new class. This was done in order to separate the shape functions and the snapshot-related methods.

ShapeFactory was also created to manage instantiated different shapes. When new shape classes are possibly added, the ShapeFactory class will need to add a case for creating that shape.

In summary: 
1. Shapes are handled in the Editor class.
2. PhotoAlbumModel consists of the Editor and can take a snapshot of the list of shapes currently in the editor. Snapshots are stored in the model.

Controller:
1. Controller is composed of a file parser to parse an input file of commands to execute in the model. The go() method in the controller executes the commands taken from the file parser method into the model. Then the model stores the snapshots created. This is then passed into a view that then generates either a web or graphical view of the photo album.

View:
1. HTMLView concatenates a string of HTML code and outputs it to an outfile.
2. Graphical view is generated using Java Swing. All snapshots in the photo album are generated on a custom JPanel class called SnapshotPanel.
Each SnapshotPanel contains a method that overrides paintComponent in order to draw, color, and set the list of shapes contained in each snapshot.
The first panel consisting of the first snapshot is shown. Then when moving through the album, the corresponding panels are made visible or invisible.
The SnapshotPanels are contained in a JPanel that uses a card layout, placed in the center of the JFrame that uses a border layout.

