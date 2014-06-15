package com.opennaru.khan.counter;

/**
 * A {@code long} based statistic.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public interface Statistic {
  /**
   * Get the current value.
   */
  public long getValue();

  /**
   * Get the current and then reset this statistic.
   */
  public long getAndReset();
}
