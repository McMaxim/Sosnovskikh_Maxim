package Tests;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortStrategyTest {

  @Test
  public void testBubbleSort() {
    SortStartegies.SortStrategy sortStrategy = (SortStartegies.SortStrategy) new SortStartegies.BubbleSortStrategy(10);
    List<Integer> items = Arrays.asList(5, 3, 8, 1, 9, 2);
    List<Integer> sorted = sortStrategy.sort(items);
    assertEquals(Arrays.asList(1, 2, 3, 5, 8, 9), sorted);
  }

  @Test
  public void testTooManyElements() {
    SortStartegies.SortStrategy sortStrategy = (SortStartegies.SortStrategy) new SortStartegies.BubbleSortStrategy(5);
    List<Integer> items = Arrays.asList(5, 3, 8, 1, 9, 2);
    assertThrows(IllegalArgumentException.class, () -> sortStrategy.sort(items));
  }
}
