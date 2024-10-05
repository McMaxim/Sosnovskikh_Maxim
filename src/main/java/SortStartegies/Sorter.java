package SortStartegies;

import java.util.List;

public class Sorter {
  private final List<SortStrategy> strategies;

  public Sorter(List<SortStrategy> strategies) {
    this.strategies = strategies;
  }

  public List<Integer> sort(List<Integer> items) {
    for (SortStrategy strategy : strategies) {
      try {
        return strategy.sort(items);
      } catch (IllegalArgumentException e) {
        System.out.println("Failed with strategy: " + strategy.getClass().getSimpleName() + " - " + e.getMessage());
      }
    }
    throw new RuntimeException("No valid sorting strategy found for the given list size");
  }
}
