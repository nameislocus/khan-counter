package com.opennaru.khan.counter;

/**
 * A simple {@code long} based counter.
 * This class has been derived from TerraCotta Toolkit's Counter
 */
public interface Counter extends Statistic {

  /**
   * Increments the count by one.
   * 
   * @return the new count value
   */
  long increment();

  /**
   * Decrements the count by one.
   * 
   * @return the new count value
   */
  long decrement();

  /**
   * Set the count to the supplied value, and return the old value.
   * <p>
   * Implementations of this method need not be atomic, all though this 
   * method is obviously of little value if the implementation is not atomic.
   * 
   * @param newValue value to set to
   * @return the old value
   */
  long getAndSet(long newValue);

  /**
   * Increments the count by amount.
   * 
   * @param amount amount to increment by
   * @return the new value
   */
  long increment(long amount);

  /**
   * Decrements the count by amount.
   * 
   * @param amount amount to increment by
   * @return the new value
   */
  long decrement(long amount);

  /**
   * Set the count to the supplied value.
   * 
   * @param newValue value to set to
   */
  void setValue(long newValue);
}
