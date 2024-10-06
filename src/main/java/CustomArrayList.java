import java.util.Arrays;

/**
 * Собственная реализация списка с поддержкой динамического изменения размера.
 *
 * @param <A> тип данных, хранящийся в списке.
 */
public class CustomArrayList<A> implements Methods<A> {
  private static final int INITIAL_CAPACITY = 10;
  private A[] elements;
  private int size;

  /**
   * Конструктор создает массив с начальным размером.
   */
  @SuppressWarnings("unchecked")
  public CustomArrayList() {
    elements = (A[]) new Object[INITIAL_CAPACITY];
    size = 0;
  }

  /**
   * Добавляет элемент в конец списка.
   *
   * @param element добавляемый элемент.
   * @throws IllegalArgumentException если передан null.
   */
  @Override
  public void add(A element) {
    if (element == null) {
      throw new IllegalArgumentException("Запрещено добавлять null-значения");
    }
    ensureCapacity();
    elements[size++] = element;
  }

  /**
   * Возвращает элемент по указанной позиции в списке.
   *
   * @param index позиция элемента в списке.
   * @return элемент, который находится на указанной позиции.
   * @throws IndexOutOfBoundsException если индекс некорректен.
   */
  @Override
  public A get(int index) {
    checkIndex(index);
    return elements[index];
  }

  /**
   * Удаляет элемент по индексу и сдвигает последующие элементы влево.
   *
   * @param index позиция удаляемого элемента.
   * @return элемент, который был удален.
   * @throws IndexOutOfBoundsException если индекс не входит в допустимый диапазон.
   */
  @Override
  public A remove(int index) {
    checkIndex(index);
    A removedElement = elements[index];
    int elementsToMove = size - index - 1;
    if (elementsToMove > 0) {
      System.arraycopy(elements, index + 1, elements, index, elementsToMove);
    }
    elements[--size] = null;
    return removedElement;
  }

  /**
   * Проверяет, нужно ли увеличивать ёмкость массива для новых элементов.
   * При необходимости увеличивает массив в два раза.
   */
  private void ensureCapacity() {
    if (size == elements.length) {
      int newCapacity = elements.length * 2;
      elements = Arrays.copyOf(elements, newCapacity);
    }
  }

  /**
   * Проверяет, находится ли индекс в пределах списка.
   *
   * @param index проверяемый индекс.
   * @throws IndexOutOfBoundsException если индекс некорректен.
   */
  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Некорректный индекс: " + index + ", Размер: " + size);
    }
  }

  /**
   * Возвращает строковое представление текущего списка.
   *
   * @return строка, описывающая элементы списка.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < size; i++) {
      sb.append(elements[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
