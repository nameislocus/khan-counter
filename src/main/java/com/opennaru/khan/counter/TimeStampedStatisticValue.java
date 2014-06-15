package com.opennaru.khan.counter;

import java.io.Serializable;

/**
 * A time-stamped retrieved statistic value.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public interface TimeStampedStatisticValue extends Serializable {

  /**
   * Get the value of this statistic
   */
  long getValue();

  /**
   * Get the Unix-timestamp for when the value was retrieved.
   */
  long getTimestamp();
}