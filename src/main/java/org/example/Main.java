package org.example;

import SortStartegies.BubbleSortStrategy;
import SortStartegies.MergeSortStrategy;
import SortStartegies.SortStrategy;
import SortStartegies.Sorter;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    List<Integer> items = Arrays.asList(5, 3, 8, 1, 9, 2);

    SortStrategy mergeSort = (SortStrategy) new MergeSortStrategy(100);  // Ограничение на 100 элементов
    SortStrategy bubbleSort = (SortStrategy) new BubbleSortStrategy(10); // Ограничение на 10 элементов

    Sorter sorter = new Sorter(Arrays.asList(mergeSort, bubbleSort));

    List<Integer> sortedItems = sorter.sort(items);

    System.out.println("Отсортированный список: " + sortedItems);
  }
}
