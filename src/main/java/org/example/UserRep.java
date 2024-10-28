package org.example;

/**
 * Интерфейс для репозитория пользователей.
 * Определяет методы для поиска и обновления пользователей по их MSISDN.
 */
public interface UserRep {

  /**
   * Находит пользователя по его MSISDN.
   *
   * @param msisdn номер телефона пользователя
   * @return объект User, если найден, иначе null
   */
  User findByMsisdn(String msisdn);

  /**
   * Обновляет информацию о пользователе по его MSISDN.
   *
   * @param msisdn номер телефона пользователя
   * @param user   объект User с обновленной информацией
   */
  void updateUserByMsisdn(String msisdn, User user);
}