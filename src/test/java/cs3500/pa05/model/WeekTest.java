package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {

  private String name;
  private List<Day> days;
  private Day monday;

  private List<Task> taskQueue;
  private String quotes;
  private Event sampleEvent1;
  private Task sampleTask1;
  private Task sampleTask2;
  private Week week;

  @BeforeEach
  public void initFields() {
    quotes = "quotes :)";
    name = "something";
    monday = new Day(DayOfWeek.MONDAY);
    this.monday = new Day(DayOfWeek.MONDAY, new ArrayList<>(), new ArrayList<>(), 4, 4);
    this.sampleEvent1 = new Event("sample event 1", "",
        new Time(1, 30), new Time(1, 0));
    this.sampleTask1 = new Task("sample task 1", "", true);
    this.sampleTask2 = new Task("sample task 2", "", false);
    Day sunday = new Day(DayOfWeek.SUNDAY);
    Event sampleEvent2 = new Event("sample event 2", "",
        new Time(1, 30), new Time(1, 0));
    monday.addEvent(sampleEvent1);
    sunday.addEvent(sampleEvent2);
    monday.addTask(sampleTask1);
    sunday.addTask(sampleTask2);
    Day tuesday = new Day(DayOfWeek.TUESDAY);
    Day wednesday = new Day(DayOfWeek.WEDNESDAY);
    Day thursday = new Day(DayOfWeek.THURSDAY);
    Day friday = new Day(DayOfWeek.FRIDAY);
    Day saturday = new Day(DayOfWeek.SATURDAY);
    this.days = new ArrayList<>(Arrays.asList(
        sunday, monday, tuesday, wednesday, thursday, friday, saturday
    ));
    this.taskQueue = new ArrayList<>(Arrays.asList(sampleTask1, sampleTask2));
    this.week = new Week(name, this.days, taskQueue, quotes, "");
  }

  @Test
  public void testGetQuotes() {
    assertEquals(this.quotes, week.getQuotes());
  }

  @Test
  public void testGetName() {
    assertEquals(this.name, week.getName());
  }

  @Test
  public void testGetDays() {
    assertEquals(this.days, week.getDays());
  }

  @Test
  public void testGetQueue() {
    assertEquals(this.taskQueue, week.getQueue());
  }

  @Test
  public void testSetName() {
    week.setName("new name!");
    assertEquals("new name!", week.getName());
  }

  @Test
  public void testAddEvent() {
    Event e = new Event("sample", "",
        new Time(2, 30), new Time(0, 20));
    this.week.addEvent(DayOfWeek.MONDAY, e);
    assertEquals(monday.getListOfEvents(),
        new ArrayList<>(Arrays.asList(sampleEvent1,
            e)));
  }

  @Test
  public void testAddTask() {
    Task t = new Task("sample", "",
        false);
    this.week.addTask(DayOfWeek.MONDAY, t);
    assertEquals(monday.getListOfTasks(),
        new ArrayList<>(Arrays.asList(sampleTask1,
            t)));
    assertEquals(this.week.getQueue(),
        new ArrayList<>(Arrays.asList(sampleTask1,
            sampleTask2, t)));
  }

  @Test
  public void testToString() {
    assertEquals(this.week.toString(),
        "{\"name\":\"something\",\"days\":[{\"day\":\"SUNDAY\",\"listOfEvents\":[{\"name\":"
            + "\"sample event 2\",\"description\":\"\",\"time\":"
            + "{\"hour\":1,\"minute\":30},\"duratio"
            + "n\":{\"hour\":1,\"minute\":0}}],\"listOfTasks\":[{\"name\":\"sample task 2\",\"descr"
            + "iption\":\"\",\"complete\":false}],\"maxEvents\":0,\"maxTasks\":0},{\"day\":\"MONDA"
            + "Y\",\"listOfEvents\":[{\"name\":\"sample event 1\",\"description\":\"\",\"time\""
            + ":{\"hour\":1,\"minute\":30},\"duration\":{\"hour\":1,\"minute\":0}}],\"listOfTasks"
            + "\":[{\"name\":\"sample task 1\",\"description\":\"\",\"complete\":true}],\"maxEvent"
            + "s\":4,\"maxTasks\":4},{\"day\":\"TUESDAY\",\"listOfEvents\":[],\"listOfTasks\":[],"
            + "\"maxEvents\":0,\"maxTasks\":0},{\"day\":\"WEDNESDAY\",\"listOfEvents\":[],\"lis"
            + "tOfTasks\":[],\"maxEvents\":0,\"maxTasks\":0},{\"day\":\"THURSDAY\",\"listOf"
            + "Events\":[],\"listOfTasks\":[],\"maxEvents\":0,\"maxTasks\":0},{\"day\":\"FRI"
            + "DAY\",\"listOfEvents\":[],\"listOfTasks\":[],\"maxEvents\":0,\"maxTasks\":0},"
            + "{\"day\":\"SATURDAY\",\"listOfEvents\":[],\"listOfTasks\":[],\"maxEvents\":0,\""
            + "maxTasks\":0}],\"quotes\":\"quotes :)\",\"password\":\"\",\"queue\":"
            + "[{\"name\":\"sample task 1\",\""
            + "description\":\"\",\"complete\":true},{\"name\":\"sample task 2\",\"description"
            + "\":\"\",\"complete\":false}]}");
  }

  @Test
  public void testSetMaxCommitments() {
    this.week.setMaxCommitments(2, 3);
    for (Day d : days) {
      assertEquals(d.getMaxEvents(), 3);
      assertEquals(d.getMaxTasks(), 2);
    }
  }

  @Test
  public void testIfOverMaxCommitments() {
    assertEquals(this.week.checkIfOverMaxCommitments(), "Warning: SUNDAY has over 0 events! "
        + "SUNDAY has over 0 tasks! ");
    this.week.setMaxCommitments(3, 3);
    assertEquals(this.week.checkIfOverMaxCommitments(), "Your commitments look good for"
        + " the week!");
  }


  @Test
  public void testSetDays() {
    this.week.setDays(new ArrayList<>());
    assertEquals(this.week.getDays(),
        new ArrayList<>());
  }

  @Test
  public void testSetQuotes() {
    this.week.setQuotes("diff quotes");
    assertEquals(this.week.getQuotes(), "diff quotes");
  }

  @Test
  public void testCountTotalEvents() {
    assertEquals(this.week.countTotalEvents(),
        2);
  }

  @Test
  public void testCountPercentCompleteTasks() {
    assertEquals(this.week.countPercentCompleteTasks(),
        "50");
  }

  @Test
  public void testSetVerifyPassword() {
    assertTrue(this.week.verifyPassword(""));
    this.week.setPassword("Test");
    assertThrows(IllegalStateException.class,
        () -> this.week.verifyPassword("alksjdlksad"));
    assertTrue(this.week.verifyPassword("Test"));
  }

  @Test
  public void testFirstDayOfWeek() {
    assertEquals(DayOfWeek.SUNDAY, this.week.firstDayOfWeek());
    this.week.shiftLeft();
    this.week.shiftLeft();
    assertEquals(DayOfWeek.TUESDAY, this.week.firstDayOfWeek());
    this.week.shiftRight();
    this.week.shiftRight();
    this.week.shiftRight();
    this.week.shiftRight();
    assertEquals(DayOfWeek.FRIDAY, this.week.firstDayOfWeek());
  }

}