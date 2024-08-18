package photoalbumapp;

public class Utility {
  /**
   * Validates and returns non-null string.
   * @param string string
   * @return validated string
   * @throws IllegalArgumentException if null string passed in
   */
  public static String validateString(String string) {
    if (string == null || string.equals("")) {
      throw new IllegalArgumentException("cannot be null or empty string");
    } else {
      return string;
    }
  }
}
