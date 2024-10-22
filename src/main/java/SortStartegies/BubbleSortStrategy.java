package SortStartegies;

import java.util.ArrayList;
import java.util.List;

public class BubbleSortStrategy implements SortStrategy {

  private final int maxElements;

  public BubbleSortStrategy(int maxElements) {
    this.maxElements = maxElements;
  }

  @Override
  public List<Integer> sort(List<Integer> items) {
    if (items.size() > maxElements) {
      throw new IllegalArgumentException("Too many elements for BubbleSort");
    }

    List<Integer> copy = new ArrayList<>(items);
    for (int i = 0; i < copy.size(); i++) {
      for (int j = i + 1; j < copy.size(); j++) {
        if (copy.get(i) > copy.get(j)) {
          int temp = copy.get(i);
          copy.set(i, copy.get(j));
          copy.set(j, temp);
        }
      }
    }
    return copy;
  }

  @Override
  public int getMaxElements() {
    return maxElements;
  }
}
