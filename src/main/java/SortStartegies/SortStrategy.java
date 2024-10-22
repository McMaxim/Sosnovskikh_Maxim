package SortStartegies;

import java.util.List;

public interface SortStrategy {
  List<Integer> sort(List<Integer> items);
  int getMaxElements();
}
