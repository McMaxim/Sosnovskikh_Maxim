package SortStartegies;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortStrategyTest {

  @Test
  public void testMergeSort() {
    SortStrategy sortStrategy = new MergeSortStrategy(100);
    List<Integer> items = Arrays.asList(5, 3, 8, 1, 9, 2);
    List<Integer> sorted = sortStrategy.sort(items);
    assertEquals(Arrays.asList(1, 2, 3, 5, 8, 9), sorted);
  }

  @Test
  public void testTooManyElements() {
    SortStrategy sortStrategy = new MergeSortStrategy(5);
    List<Integer> items = Arrays.asList(5, 3, 8, 1, 9, 2);
    assertThrows(IllegalArgumentException.class, () -> sortStrategy.sort(items));
  }
}
