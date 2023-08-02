package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles bujo reading/writing
 */
public class FileHandler {

  /**
   * reads the week information from the given file
   *
   * @param file Files that the method reads
   * @return the contents of the files already made into a week
   * @throws IOException if the file cannot be read and parsed
   */
  public static Week readFromFiles(String file) throws IOException {
    BufferedReader reader;
    StringBuilder x = new StringBuilder();
    reader = new BufferedReader(new FileReader(file));
    String line = reader.readLine();
    while (line != null) {
      x.append(line);
      line = reader.readLine();
    }
    reader.close();
    return toWeek(x.toString());
  }

  /**
   * Converts the given string to a week
   *
   * @param str string to be converted
   * @return the week
   * @throws IOException an I/O exception
   */
  private static Week toWeek(String str) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(str);
    return mapper.convertValue(node, Week.class);
  }

  /**
   * writes the contents to the given file
   *
   * @param file     path to the file that the content will be written to
   * @param contents string that will be written into the file
   * @throws IOException if an I/O error occurs
   */
  public static void writeToFile(String file, String contents) throws IOException {
    createNewFile(file);
    FileWriter w = new FileWriter(file);
    w.write(contents);
    w.close();
  }

  /**
   * Creates a new file at the given path
   *
   * @param s path to the file that will be created
   * @throws IOException if an I/O error occurs
   */
  private static void createNewFile(String s) throws IOException {
    File f = new File(s);
    f.createNewFile();
  }
}
