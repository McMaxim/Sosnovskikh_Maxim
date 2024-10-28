package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация репозитория пользователей, хранящая данные в памяти.
 * Использует ConcurrentHashMap для потокобезопасного доступа к данным.
 */
public class InMemoryUserRepository implements UserRep {
  // Хранилище пользователей, где ключом является MSISDN, а значением - объект User
  private final Map<String, User> userStore = new ConcurrentHashMap<>();

  /**
   * Находит пользователя по его MSISDN.
   *
   * @param msisdn номер телефона пользователя
   * @return объект User, если найден, иначе null
   */
  @Override
  public User findByMsisdn(String msisdn) {
    return userStore.get(msisdn);
  }

  /**
   * Обновляет информацию о пользователе по его MSISDN.
   *
   * @param msisdn номер телефона пользователя
   * @param user   объект User с обновленной информацией
   */
  @Override
  public void updateUserByMsisdn(String msisdn, User user) {
    userStore.put(msisdn, user);
  }
}
