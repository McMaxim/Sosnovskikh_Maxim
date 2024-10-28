package org.example.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Тесты для сервиса обогащения данных.
 */
class EnrichmentServiceTest {
  private UserRep userRepository; // Репозиторий пользователей
  private EnrichmentService enrichmentService; // Сервис обогащения данных

  /**
   * Настройка тестового окружения перед каждым тестом.
   */
  @BeforeEach
  void setUp() {
    userRepository = new InMemoryUserRepository(); // Инициализация репозитория пользователей
    enrichmentService = new EnrichmentService(userRepository); // Инициализация сервиса обогащения
  }

  /**
   * Тест на обогащение сообщения данными пользователя.
   */
  @Test
  void shouldEnrichMessageWithUserDetails() {
    // Создание пользователя и добавление его в репозиторий
    User user = new User("Vasya", "Ivanov");
    userRepository.updateUserByMsisdn("88005553535", user);

    // Создание сообщения с содержимым
    Map<String, String> content = new HashMap<>();
    content.put("action", "button_click");
    content.put("page", "book_card");
    content.put("msisdn", "88005553535");
    Message message = new Message(content, Message.EnrichmentType.MSISDN);

    // Обогащение сообщения данными пользователя
    Message enrichedMessage = enrichmentService.enrich(message);

    // Проверка, что данные пользователя были добавлены в сообщение
    assertEquals("Vasya", enrichedMessage.getContent().get("firstName"));
    assertEquals("Ivanov", enrichedMessage.getContent().get("lastName"));
  }
}