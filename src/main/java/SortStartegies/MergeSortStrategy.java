package SortStartegies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortStrategy implements SortStrategy {

  private final int maxElements;

  public MergeSortStrategy(int maxElements) {
    this.maxElements = maxElements;
  }

  @Override
  public List<Integer> sort(List<Integer> items) {
    if (items.size() > maxElements) {
      throw new IllegalArgumentException("Too many elements for MergeSort");
    }

    List<Integer> copy = new ArrayList<>(items);
    Collections.sort(copy);
    return copy;
  }

  @Override
  public int getMaxElements() {
    return maxElements;
  }
}

