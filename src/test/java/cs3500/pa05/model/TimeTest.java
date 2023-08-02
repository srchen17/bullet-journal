package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeTest {
  Time time1;
  Time time2;
  Time time3;

  @BeforeEach
  void initialize() {
    this.time1 = new Time(11, 30);
    this.time2 = new Time(1, 45);
    this.time3 = new Time(8, 0);
  }

  @Test
  void testGetters() {
    assertEquals(11, this.time1.getHour());
    assertEquals(1, this.time2.getHour());
    assertEquals(8, this.time3.getHour());
    assertEquals(30, this.time1.getMinute());
    assertEquals(45, this.time2.getMinute());
    assertEquals(0, this.time3.getMinute());
  }

  @Test
  void testSetters() {
    assertEquals(11, this.time1.getHour());
    assertEquals(30, this.time1.getMinute());
    this.time1.setHour(5);
    this.time1.setMinute(20);
    assertEquals(5, this.time1.getHour());
    assertEquals(20, this.time1.getMinute());
  }

  @Test
  void testToString() {
    assertEquals("1:45", this.time2.toString());
    assertEquals("8:00", this.time3.toString());
  }

  @Test
  void testOutOfBoundsValues() {
    assertThrows(IllegalArgumentException.class,
        () -> new Time(14, 2));
    assertThrows(IllegalArgumentException.class,
        () -> new Time(-1, 2));
    assertThrows(IllegalArgumentException.class,
        () -> new Time(0, 75));
    assertThrows(IllegalArgumentException.class,
        () -> new Time(14, -5));
  }

}