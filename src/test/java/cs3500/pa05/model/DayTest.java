package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {

  private Day sunday;
  private Day monday;
  private Event sampleEvent1;
  private Event sampleEvent2;
  private Event sampleEvent3;
  private Event[] sampleEvents123;
  private Task sampleTask1;
  private Task sampleTask2;
  private Task sampleTask3;
  private Task[] sampleTasks321;

  @BeforeEach
  void initialize() {
    this.sunday = new Day(DayOfWeek.SUNDAY);
    this.monday = new Day(DayOfWeek.MONDAY, new ArrayList<>(), new ArrayList<>(), 4, 4);
    this.sampleEvent1 = new Event("sample event 1", "",
        new Time(1, 30), new Time(1, 0));
    this.sampleEvent2 = new Event("sample event 2", "",
        new Time(1, 30), new Time(1, 0));
    this.sampleEvent3 = new Event("sample event 3", "",
        new Time(1, 30), new Time(1, 0));
    this.sampleEvents123 = new Event[3];
    this.sampleEvents123[0] = sampleEvent1;
    this.sampleEvents123[1] = sampleEvent2;
    this.sampleEvents123[2] = sampleEvent3;
    this.sampleTask1 = new Task("sample task 1", "");
    this.sampleTask2 = new Task("sample task 2", "");
    this.sampleTask3 = new Task("sample task 3", "");
    this.sampleTasks321 = new Task[3];
    this.sampleTasks321[0] = sampleTask1;
    this.sampleTasks321[1] = sampleTask2;
    this.sampleTasks321[2] = sampleTask3;
  }

  @Test
  void testGetListOfEvents() {
    assertArrayEquals(new Event[0], this.sunday.getListOfEvents().toArray());
    assertArrayEquals(new Event[0], this.monday.getListOfEvents().toArray());
    this.sunday.addEvent(this.sampleEvent1);
    this.sunday.addEvent(this.sampleEvent2);
    this.sunday.addEvent(this.sampleEvent3);
    assertArrayEquals(this.sampleEvents123, this.sunday.getListOfEvents().toArray());
    ArrayList<Event> sampleEvents13 = new ArrayList<>();
    sampleEvents13.add(sampleEvent1);
    sampleEvents13.add(sampleEvent3);
    this.sunday.setListOfEvents(sampleEvents13);
    assertArrayEquals(sampleEvents13.toArray(), this.sunday.getListOfEvents().toArray());
  }

  @Test
  void testGetListOfTasks() {
    assertArrayEquals(new Task[0], this.sunday.getListOfTasks().toArray());
    assertArrayEquals(new Task[0], this.monday.getListOfTasks().toArray());
    this.sunday.addTask(this.sampleTask1);
    this.sunday.addTask(this.sampleTask2);
    this.sunday.addTask(this.sampleTask3);
    assertArrayEquals(this.sampleTasks321, this.sunday.getListOfTasks().toArray());
    ArrayList<Task> sampleTasks32 = new ArrayList<>();
    sampleTasks32.add(sampleTask3);
    sampleTasks32.add(sampleTask2);
    this.sunday.setListOfTasks(sampleTasks32);
    assertArrayEquals(sampleTasks32.toArray(), this.sunday.getListOfTasks().toArray());
  }

  @Test
  void testGetSetMaxEvents() {
    assertEquals(0, this.sunday.getMaxEvents());
    assertEquals(4, this.monday.getMaxEvents());
    this.monday.setMaxEvents(5);
    assertEquals(5, this.monday.getMaxEvents());
  }

  @Test
  void testGetSetMaxTasks() {
    assertEquals(0, this.sunday.getMaxTasks());
    assertEquals(4, this.monday.getMaxTasks());
    this.sunday.setMaxTasks(5);
    assertEquals(5, this.sunday.getMaxTasks());
  }

  @Test
  void testGetSetDay() {
    assertEquals(DayOfWeek.SUNDAY, this.sunday.getDay());
    assertEquals(DayOfWeek.MONDAY, this.monday.getDay());
    this.sunday.setDay(DayOfWeek.MONDAY);
    assertEquals(DayOfWeek.MONDAY, this.sunday.getDay());
  }

  @Test
  void checkNumOfEvents() {
    assertArrayEquals(new Event[0], this.sunday.getListOfEvents().toArray());
    assertArrayEquals(new Event[0], this.monday.getListOfEvents().toArray());
    this.sunday.addEvent(this.sampleEvent1);
    this.sunday.addEvent(this.sampleEvent2);
    this.sunday.addEvent(this.sampleEvent3);
    assertArrayEquals(this.sampleEvents123, this.sunday.getListOfEvents().toArray());

    this.sunday.setMaxEvents(2);
    assertTrue(this.sunday.checkNumOfEvents());
    this.sunday.setMaxEvents(3);
    assertFalse(this.sunday.checkNumOfEvents());
    this.sunday.setMaxEvents(4);
    assertFalse(this.sunday.checkNumOfEvents());
  }

  @Test
  void checkNumOfTasks() {
    assertArrayEquals(new Event[0], this.sunday.getListOfTasks().toArray());
    assertArrayEquals(new Event[0], this.monday.getListOfTasks().toArray());
    this.sunday.addTask(this.sampleTask1);
    this.sunday.addTask(this.sampleTask2);
    this.sunday.addTask(this.sampleTask3);

    this.sunday.setMaxTasks(2);
    assertTrue(this.sunday.checkNumOfTasks());
    this.sunday.setMaxTasks(3);
    assertFalse(this.sunday.checkNumOfTasks());
    this.sunday.setMaxTasks(4);
    assertFalse(this.sunday.checkNumOfTasks());
  }
}