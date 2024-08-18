package photoalbumapp.controller.fileparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import photoalbumapp.controller.fileparser.commands.ColorCommand;
import photoalbumapp.controller.fileparser.commands.ShapeCommand;
import photoalbumapp.controller.fileparser.commands.ICommand;
import photoalbumapp.controller.fileparser.commands.MoveCommand;
import photoalbumapp.controller.fileparser.commands.RemoveCommand;
import photoalbumapp.controller.fileparser.commands.ResizeCommand;
import photoalbumapp.controller.fileparser.commands.SnapshotCommand;

import java.io.File;
import java.util.Scanner;

/**
 * The type File parser.
 */
public class FileParser {

  private final List<ICommand> commandList;

  /**
   * Instantiates a new File parser.
   */
  public FileParser() {
    commandList = new ArrayList<>();
  }

  /**
   * Gets command list.
   *
   * @return the command list
   */
  public List<ICommand> getCommandList() {
    return commandList;
  }

  /**
   * Parse file list.
   *
   * @param fileName the file name
   * @return the list
   * @throws IOException the io exception
   */
  public List<ICommand> parseFile(String fileName) throws IOException {
    // open file
    //File file = new File("resources/" + fileName); //w/out jar file
    File file = new File(fileName); //for running w/jar file
    if (!file.exists()) throw new IOException("File not found");

    Scanner scanner = new Scanner(file);

    // loop over each file line
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      if (line.isEmpty() || line.startsWith("#")) { // Skip comment lines & empty lines
        continue;
      }
      String[] parts = line.split("\\s+"); //one or more white spaces as delimiter
      String command = parts[0];
      switch (command.toLowerCase()) {
        case "shape":
          commandList.add(parseShapeCommand(parts));
          break;
        case "move":
          commandList.add(parseMoveCommand(parts));
          break;
        case "color":
          commandList.add(parseColorCommand(parts));
          break;
        case "resize":
          commandList.add(parseResizeCommand(parts));
          break;
        case "remove":
          commandList.add(parseRemoveCommand(parts));
          break;
        case "snapshot":
          commandList.add(parseSnapshotCommand(parts));
          break;
        default:
          System.out.println("Unknown command: " + command);
          break;
      }
    }
    return this.commandList; //returns list of commands
  }

  private ShapeCommand parseShapeCommand(String[] parts) {
    String name = parts[1];
    String type = parts[2];
    double x = Double.parseDouble(parts[3]);
    double y = Double.parseDouble(parts[4]);
    double horizontalDimension = Double.parseDouble(parts[5]);
    double verticalDimension = Double.parseDouble(parts[6]);
    double red = Double.parseDouble(parts[7]);
    double green = Double.parseDouble(parts[8]);
    double blue = Double.parseDouble(parts[9]);
    return new ShapeCommand(name, type, x, y,
            horizontalDimension, verticalDimension, red, green, blue);
  }

  private MoveCommand parseMoveCommand(String[] parts) {
    String name = parts[1];
    double x = Double.parseDouble(parts[2]);
    double y = Double.parseDouble(parts[3]);
    return new MoveCommand(name, x, y);
  }

  private ColorCommand parseColorCommand(String[] parts) {
    String name = parts[1];
    double red = Double.parseDouble(parts[2]);
    double green = Double.parseDouble(parts[3]);
    double blue = Double.parseDouble(parts[4]);
    return new ColorCommand(name, red, green, blue);
  }

  private ResizeCommand parseResizeCommand(String[] parts) {
    String name = parts[1];
    double horizontalDimension = Double.parseDouble(parts[2]);
    double verticalDimension = Double.parseDouble(parts[3]);
    return new ResizeCommand(name, horizontalDimension, verticalDimension);
  }

  private RemoveCommand parseRemoveCommand(String[] parts) {
    String name = parts[1];
    return new RemoveCommand(name);
  }

  private SnapshotCommand parseSnapshotCommand(String[] parts) {
    String description = "";
    if (parts.length > 1) {
      for (int i = 1; i < parts.length; i++) {
        description += parts[i] + " ";
      }
    }
    return new SnapshotCommand(description);
  }
}
