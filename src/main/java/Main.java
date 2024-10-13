/**
 * Пример использования кастомного списка CustomArrayList.
 */
public class Main {
  public static void main(String[] args) {
    CustomArrayList<Integer> list = new CustomArrayList<>();

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    System.out.println("Начальный список: " + list);
    System.out.println("Элемент по индексу 2: " + list.get(2));
    System.out.println("Удалённый элемент: " + list.remove(2));
    System.out.println("Список после удаления: " + list);

    for (int i = 6; i <= 20; i++) {
      list.add(i);
    }
    System.out.println("Список после добавления новых элементов: " + list);

    try {
      list.remove(25);
      System.out.println("Эта строка не должна быть выведена");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Обнаружено исключение IndexOutOfBoundsException");
    }

    try {
      list.add(null);
      System.out.println("Эта строка не должна быть выведена");
    } catch (IllegalArgumentException e) {
      System.out.println("Обнаружено исключение IllegalArgumentException");
    }
  }
}
