package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventWrapperTest {

  EventWrapper eventWrapper;
  Event event;

  @BeforeEach
  void initialize() {
    this.event = new Event("sample event", "sample description",
        new Time(1, 30), new Time(1, 30));
    this.eventWrapper = new EventWrapper(this.event, DayOfWeek.MONDAY);
  }

  @Test
  void testGetEvent() {
    assertEquals(this.event, this.eventWrapper.getCommitment());
  }

  @Test
  void testGetDay() {
    assertEquals(DayOfWeek.MONDAY, this.eventWrapper.getDay());
  }

}