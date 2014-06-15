package com.opennaru.khan.counter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * CircularLossyQueue
 * 
 * This class has been derived from TerraCotta Toolkit's Counter
 *
 * @param <T>
 */
public class CircularLossyQueue<T> {
  private final AtomicReferenceArray<T> circularArray;
  private final int                     maxSize;

  private final AtomicLong              currentIndex = new AtomicLong(-1);

  public CircularLossyQueue(int size) {
    this.maxSize = size;
    this.circularArray = new AtomicReferenceArray<T>(maxSize);
  }

  public void push(T newVal) {
    int index = (int) (currentIndex.incrementAndGet() % maxSize);
    circularArray.set(index, newVal);
  }

  public T[] toArray(T[] type) {
    if (type.length > maxSize) { throw new IllegalArgumentException("Size of array passed in cannot be greater than "
                                                                    + maxSize); }

    int curIndex = getCurrentIndex();
    for (int k = 0; k < type.length; k++) {
      int index = getIndex(curIndex - k);
      type[k] = circularArray.get(index);
    }
    return type;
  }

  private int getIndex(int index) {
    index = index < 0 ? index + maxSize : index;
    return index;
  }

  public T peek() {
    if (depth() == 0) return null;
    return circularArray.get(getIndex(getCurrentIndex()));
  }

  public boolean isEmtpy() {
    return depth() == 0;
  }

  private int getCurrentIndex() {
    return (int) (currentIndex.get() % maxSize);
  }

  public int depth() {
    long currInd = currentIndex.get() + 1;
    return currInd >= maxSize ? maxSize : (int) currInd;
  }
}
