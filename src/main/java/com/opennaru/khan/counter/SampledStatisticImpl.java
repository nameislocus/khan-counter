package com.opennaru.khan.counter;

import java.util.TimerTask;

/**
 * 
 * This class has been derived from TerraCotta Toolkit's Counter
 *
 */
class SampledStatisticImpl implements SampledStatistic {
  private final Statistic statistic;
  
  protected final CircularLossyQueue<TimeStampedStatisticValue> history;
  protected final boolean                                     resetOnSample;
  private final TimerTask                                     samplerTask;

  protected SampledStatisticImpl(Statistic statistic, int samples, boolean resetOnSample) {
    this.statistic = statistic;
    
    this.history = new CircularLossyQueue<TimeStampedStatisticValue>(samples);
    this.resetOnSample = resetOnSample;

    this.samplerTask = new TimerTask() {
      public void run() {
        recordSample();
      }
    };

    samplerTask.run();
  }

  public TimeStampedStatisticValue getMostRecentSample() {
    return this.history.peek();
  }

  public TimeStampedStatisticValue[] getAllSampleValues() {
    return this.history.toArray(new TimeStampedStatisticImpl[this.history.depth()]);
  }

  protected TimerTask getTimerTask() {
    return this.samplerTask;
  }

  protected void recordSample() {
    final long sample;
    if (resetOnSample) {
      sample = statistic.getAndReset();
    } else {
      sample = statistic.getValue();
    }

    final long now = System.currentTimeMillis();
    TimeStampedStatisticValue timedSample = new TimeStampedStatisticImpl(now, sample);

    history.push(timedSample);
  }
  
  static class TimeStampedStatisticImpl implements TimeStampedStatisticValue {
    private final long counterValue;
    private final long timestamp;

    TimeStampedStatisticImpl(long timestamp, long value) {
      this.timestamp = timestamp;
      this.counterValue = value;
    }

    public long getValue() {
      return this.counterValue;
    }

    public long getTimestamp() {
      return this.timestamp;
    }

    public String toString() {
      return "value: " + this.counterValue + ", timestamp: " + this.timestamp;
    }
  }  
}
