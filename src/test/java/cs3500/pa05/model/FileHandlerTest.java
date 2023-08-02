package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the class File Handler and methods in the class
 */
public class FileHandlerTest {
  private Week week;

  /**
   * Set up a Week and other examples for the class FileHandler
   */
  @BeforeEach
  public void initWeek() {
    Day monday = new Day(DayOfWeek.MONDAY);
    monday.addEvent(new Event("hey",
        "hello", new Time(2, 3),
        new Time(4, 5)));
    Task t = new Task("hey", "hello", true);
    monday.addTask(t);
    List<Day> days = new ArrayList<>(Arrays.asList(
        new Day(DayOfWeek.SUNDAY), monday, new Day(DayOfWeek.TUESDAY),
        new Day(DayOfWeek.WEDNESDAY), new Day(DayOfWeek.THURSDAY),
        new Day(DayOfWeek.FRIDAY), new Day(DayOfWeek.SATURDAY)));
    for (Day day : days) {
      day.setMaxEvents(3);
      day.setMaxTasks(4);
    }
    this.week = new Week("x", days,
        new ArrayList<>(List.of(t)), "quotesss hey", "");
  }

  /**
   * Checks whether the class reads from files correctly
   *
   * @throws IOException if an I/O error occurs
   */
  @Test
  public void checkReadFromFiles() throws IOException {
    assertEquals(FileHandler.readFromFiles("src/test/bujos/x.bujo").toString(),
        this.week.toString());
  }

  /**
   * Tests that the class writes to files correctly
   *
   * @throws IOException if an I/O error occurs
   */
  @Test
  public void checkWriteToFile() throws IOException {
    //FileHandler f = new FileHandler();
    FileHandler.writeToFile("src/test/bujos/out.bujo", this.week.toString());
    assertEquals(FileHandler.readFromFiles("src/test/bujos/out.bujo").toString(),
        "{\"name\":\"x\",\"days\":[{\"day\":\"SUNDAY\",\"listOfEvents\":[],\"listOfTasks\":"
            + "[],\"maxEvents\":3,\"maxTasks\":4},"
            + "{\"day\":\"MONDAY\",\"listOfEvents\":[{\"name\":\""
            + "hey\",\"description\":\"hello\",\"time\":{\"hour\":2,\"minute\":3},"
            + "\"duration\":{\"ho"
            + "ur\":4,\"minute\":5}}],\"listOfTasks\":"
            + "[{\"name\":\"hey\",\"description\":\"hello\","
            + "\"complete\":true}],\"maxEvents\":3,\"maxTasks\":4},"
            + "{\"day\":\"TUESDAY\",\"listOfEv"
            + "ents\":[],\"listOfTasks\":[],\"maxEvents\":3,"
            + "\"maxTasks\":4},{\"day\":\"WEDNESDAY"
            + "\",\"listOfEvents\":[],\"listOfTasks\":[],\"maxEvents\""
            + ":3,\"maxTasks\":4},{\"day\":"
            + "\"THURSDAY\",\"listOfEvents\":[],"
            + "\"listOfTasks\":[],\"maxEvents\":3,\"maxTasks\":4},"
            + "{\"day\":\"FRIDAY\",\"listOfEvents\":[],\"listOfTasks\":[],"
            + "\"maxEvents\":3,\"maxTask"
            + "s\":4},{\"day\":\"SATURDAY\",\"listOfEvents\":[],"
            + "\"listOfTasks\":[],\"maxEvents\":3"
            + ",\"maxTasks\":4}],\"quotes\":\"quotesss hey\",\""
            + "password\":\"\",\"queue\":[{\"name\":\"hey\",\"descri"
            + "ption\":\"hello\",\"complete\":true}]}");
  }
}
