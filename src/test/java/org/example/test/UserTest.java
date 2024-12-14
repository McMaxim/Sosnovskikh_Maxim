package org.example.test;

import org.junit.jupiter.api.Test;
import org.example.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  /**
   * Тест на создание пользователя с корректными данными.
   */
  @Test
  void shouldCreateUserWithValidData() {
    User user = new User("Vasya", "Ivanov");
    assertEquals("Vasya", user.getFirstName());
    assertEquals("Ivanov", user.getLastName());
  }

  /**
   * Тест на создание пользователя с null в имени.
   */
  @Test
  void shouldThrowExceptionWhenFirstNameIsNull() {
    assertThrows(NullPointerException.class, () -> new User(null, "Ivanov"));
  }

  /**
   * Тест на создание пользователя с null в фамилии.
   */
  @Test
  void shouldThrowExceptionWhenLastNameIsNull() {
    assertThrows(NullPointerException.class, () -> new User("Vasya", null));
  }

  /**
   * Тест на корректное сравнение пользователей.
   */
  @Test
  void shouldBeEqualWhenUsersHaveSameData() {
    User user1 = new User("Vasya", "Ivanov");
    User user2 = new User("Vasya", "Ivanov");
    assertEquals(user1, user2);
  }

  /**
   * Тест на неравенство пользователей с разными данными.
   */
  @Test
  void shouldNotBeEqualWhenUsersHaveDifferentData() {
    User user1 = new User("Vasya", "Ivanov");
    User user2 = new User("Petya", "Ivanov");
    User user3 = new User("Vasya", "Petrov");
    assertNotEquals(user1, user2);
    assertNotEquals(user1, user3);
  }

  /**
   * Тест на корректный хэш-код для одинаковых пользователей.
   */
  @Test
  void shouldHaveSameHashCodeForEqualUsers() {
    User user1 = new User("Vasya", "Ivanov");
    User user2 = new User("Vasya", "Ivanov");
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  /**
   * Тест на корректное строковое представление пользователя.
   */
  @Test
  void shouldReturnCorrectStringRepresentation() {
    User user = new User("Vasya", "Ivanov");
    String expectedString = "User   {firstName='Vasya', lastName='Ivanov'}";
    assertEquals(expectedString, user.toString());
  }
}
