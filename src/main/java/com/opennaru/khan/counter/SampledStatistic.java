package com.opennaru.khan.counter;

/**
 * A sampled statistic value.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public interface SampledStatistic {

  /**
   * Returns the most recent sampled from this statistic.
   */
  TimeStampedStatisticValue getMostRecentSample();

  /**
   * Returns as full a history from this statistic as possible.
   */
  TimeStampedStatisticValue[] getAllSampleValues();
}
