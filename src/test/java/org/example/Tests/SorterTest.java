package org.example.Tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import SortStartegies.*; // Импортируем классы из исходного пакета

class SorterTest {

  @Test
  void testSuccessfulSortWithBubbleSort() {
    SortStrategy bubbleSort = new BubbleSortStrategy(10);
    SortStrategy mergeSort = new MergeSortStrategy(5); // Should fail for lists larger than 5

    Sorter sorter = new Sorter(Arrays.asList(bubbleSort, mergeSort));
    List<Integer> items = Arrays.asList(3, 1, 4, 1, 5);
    List<Integer> expected = Arrays.asList(1, 1, 3, 4, 5);

    List<Integer> sorted = sorter.sort(items);
    assertEquals(expected, sorted);
  }

  @Test
  void testSuccessfulSortWithMergeSort() {
    SortStrategy bubbleSort = new BubbleSortStrategy(4); // Should fail for lists larger than 4
    SortStrategy mergeSort = new MergeSortStrategy(10);

    Sorter sorter = new Sorter(Arrays.asList(bubbleSort, mergeSort));
    List<Integer> items = Arrays.asList(3, 1, 4, 1, 5);
    List<Integer> expected = Arrays.asList(1, 1, 3, 4, 5);

    List<Integer> sorted = sorter.sort(items);
    assertEquals(expected, sorted);
  }

  @Test
  void testAllStrategiesFail() {
    SortStrategy bubbleSort = new BubbleSortStrategy(3); // Should fail for lists larger than 3
    SortStrategy mergeSort = new MergeSortStrategy(4);   // Should fail for lists larger than 4

    Sorter sorter = new Sorter(Arrays.asList(bubbleSort, mergeSort));
    List<Integer> items = Arrays.asList(3, 1, 4, 1, 5);

    RuntimeException exception = assertThrows(RuntimeException.class, () -> sorter.sort(items));
    assertEquals("No valid sorting strategy found for the given list size", exception.getMessage());
  }

  @Test
  void testEmptyList() {
    SortStrategy bubbleSort = new BubbleSortStrategy(10);
    SortStrategy mergeSort = new MergeSortStrategy(10);

    Sorter sorter = new Sorter(Arrays.asList(bubbleSort, mergeSort));
    List<Integer> items = Collections.emptyList();

    List<Integer> sorted = sorter.sort(items);
    assertTrue(sorted.isEmpty());
  }

  @Test
  void testSingleElementList() {
    SortStrategy bubbleSort = new BubbleSortStrategy(10);
    SortStrategy mergeSort = new MergeSortStrategy(10);

    Sorter sorter = new Sorter(Arrays.asList(bubbleSort, mergeSort));
    List<Integer> items = Collections.singletonList(42);

    List<Integer> sorted = sorter.sort(items);
    assertEquals(Collections.singletonList(42), sorted);
  }

  @Test
  void testStrategyFailureLogging() {
    SortStrategy mergeSort = new MergeSortStrategy(4); // Will fail

    Sorter sorter = new Sorter(Collections.singletonList(mergeSort));
    List<Integer> items = Arrays.asList(3, 1, 4, 1, 5);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mergeSort.sort(items));
    assertEquals("Too many elements for MergeSort", exception.getMessage());
  }
}
