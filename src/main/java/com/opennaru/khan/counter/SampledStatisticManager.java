package com.opennaru.khan.counter;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * A non-static factory for sampled statistics.
 * <p>
 * Each instance of the factory creates an independent timer thread that must be shutdown when the statistic manager is
 * no longer needed.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public class SampledStatisticManager {
  private final Timer timer      = new Timer("SampledStatisticsManager Timer", true);
  private boolean     isShutdown = false;

  /**
   * Shutdown this statistic manager.
   * <p>
   * This will trigger the shutdown of the sampling thread used by this statistic manager.
   */
  public synchronized void shutdown() {
    if (isShutdown) { return; }
    timer.cancel();
    isShutdown = true;
  }

  /**
   * Creates a sampled statistic sampling the supplied {@code Statistic} object.
   * 
   * @param statistic the statistic value to be sampled
   * @param period the period in seconds between samplings
   * @param samples the number of samples to store in the history
   * @param resetOnSample @{code true} if the statistic should be reset after sampling
   * @return a new sampled statistic
   */
  public synchronized SampledStatistic createSampler(Statistic statistic, int period, int samples, boolean resetOnSample) {
    SampledStatisticImpl sampler = new SampledStatisticImpl(statistic, samples, resetOnSample);
    timer.schedule(sampler.getTimerTask(), TimeUnit.SECONDS.toMillis(period), TimeUnit.SECONDS.toMillis(period));
    return sampler;
  }

  /**
   * Destroys a previously created sampled statistic.
   * <p>
   * This stops all future sampling for this statistic, and releases all internal references to the statistic.
   * 
   * @param statistic statistic to be destroyed
   */
  public synchronized void destroySampler(SampledStatistic statistic) {
    if (statistic instanceof SampledStatisticImpl) {
      ((SampledStatisticImpl) statistic).getTimerTask().cancel();
    }
  }
}
