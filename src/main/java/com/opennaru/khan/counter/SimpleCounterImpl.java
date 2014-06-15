package com.opennaru.khan.counter;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple non-clusterable atomic counter implementation.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public class SimpleCounterImpl implements Counter, Serializable {
  private AtomicLong value;

  /**
   * Creates a new counter with an initial value of 0.
   */
  public SimpleCounterImpl() {
    this(0L);
  }

  /**
   * Creates a new counter with the given initial value.
   * 
   * @param initialValue initial value for the counter
   */
  public SimpleCounterImpl(long initialValue) {
    this.value = new AtomicLong(initialValue);
  }

  public long increment() {
    return value.incrementAndGet();
  }

  public long decrement() {
    return value.decrementAndGet();
  }

  /**
   * Atomically sets the new value and returns the previous value.
   */
  public long getAndSet(long newValue) {
    return value.getAndSet(newValue);
  }

  /**
   * Performs an atomic getAndSet(0).
   */
  public long getAndReset() {
    return getAndSet(0L);
  }

  public long getValue() {
    return value.get();
  }

  public long increment(long amount) {
    return value.addAndGet(amount);
  }

  public long decrement(long amount) {
    return value.addAndGet(amount * -1);
  }

  public void setValue(long newValue) {
    value.set(newValue);
  }

}
