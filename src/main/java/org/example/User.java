package org.example;

import java.util.Objects;

/**
 * Класс, представляющий пользователя с именем и фамилией.
 */
public class User {
  private final String firstName; // Имя пользователя
  private final String lastName;  // Фамилия пользователя

  /**
   * Конструктор для создания пользователя.
   *
   * @param firstName имя пользователя
   * @param lastName  фамилия пользователя
   * @throws NullPointerException если имя или фамилия равны null
   */
  public User(String firstName, String lastName) {
    this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
    this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
  }

  /**
   * Получает имя пользователя.
   *
   * @return имя пользователя
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Получает фамилию пользователя.
   *
   * @return фамилия пользователя
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Переопределение метода toString для удобного отображения информации о пользователе.
   *
   * @return строковое представление пользователя
   */@Override
  public String toString() {
    return "User   {" + // Здесь два пробела
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  /**
   * Переопределение метода equals для сравнения пользователей.
   *
   * @param o объект для сравнения
   * @return true, если объекты равны, иначе false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true; // Сравнение ссылок
    if (!(o instanceof User)) return false; // Проверка типа
    User user = (User ) o; // Приведение типа
    return firstName.equals(user.firstName) && lastName.equals(user.lastName); // Сравнение полей
  }

  /**
   * Переопределение метода hashCode для корректной работы с коллекциями.
   *
   * @return хэш-код пользователя
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}