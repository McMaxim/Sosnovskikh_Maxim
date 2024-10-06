/**
 * Интерфейс для создания базовых операций с коллекцией.
 *
 * @param <A> тип данных, который хранится в коллекции.
 */
public interface Methods<A> {
  /**
   * Добавляет новый элемент в конец коллекции.
   *
   * @param element объект, который нужно добавить.
   * @throws IllegalArgumentException если элемент равен null.
   */
  void add(A element);

  /**
   * Возвращает элемент по заданной позиции в коллекции.
   *
   * @param index позиция элемента.
   * @return элемент на указанной позиции.
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
   */
  A get(int index);

  /**
   * Удаляет элемент на основе указанного индекса.
   *
   * @param index позиция удаляемого элемента.
   * @return удалённый элемент.
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
   */
  A remove(int index);
}
