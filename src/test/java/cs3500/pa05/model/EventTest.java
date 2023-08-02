package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

  Event event1;
  Event event2;

  @BeforeEach
  void initialize() {
    this.event1 = new Event("sample event 1", "",
        new Time(1, 30), new Time(1, 30));
    this.event2 = new Event("sample event 2", "this is a description",
        new Time(1, 30), new Time(1, 30));
  }

  @Test
  void testGetName() {
    assertEquals("sample event 1", this.event1.getName());
    assertEquals("sample event 2", this.event2.getName());
    this.event1.setName("sample event 1 revised");
    assertEquals("sample event 1 revised", this.event1.getName());
  }

  @Test
  void testGetDescription() {
    assertEquals("", this.event1.getDescription());
    assertEquals("this is a description", this.event2.getDescription());
    this.event1.setDescription("description!");
    assertEquals("description!", this.event1.getDescription());
  }

  @Test
  void testGetTime() {
    assertEquals(1, this.event1.getTime().getHour());
    assertEquals(30, this.event1.getTime().getMinute());
    this.event1.setTime(new Time(2, 30));
    assertEquals(2, this.event1.getTime().getHour());
    assertEquals(30, this.event1.getTime().getMinute());
  }

  @Test
  void testGetDuration() {
    assertEquals(1, this.event1.getDuration().getHour());
    assertEquals(30, this.event1.getDuration().getMinute());
    this.event1.setDuration(new Time(2, 30));
    assertEquals(2, this.event1.getDuration().getHour());
    assertEquals(30, this.event1.getDuration().getMinute());
  }

  @Test
  void testToString() {
    String event1 = """
        sample event 1

         Start Time: 1:30
         Duration: 1:30""";
    assertEquals(event1, this.event1.toString());
  }

}