package org.example.test;

import org.junit.jupiter.api.Test;
import org.example.*;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Тесты для проверки работы сервиса обогащения в многопоточном окружении.
 */
class MultiprocessingTest {

  /**
   * Тест на успешное обогащение сообщений в многопоточном окружении.
   *
   * @throws InterruptedException если поток был прерван
   */
  @Test
  void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws InterruptedException {
    UserRep userRepository = new InMemoryUserRepository(); // Инициализация репозитория пользователей
    EnrichmentService enrichmentService = new EnrichmentService(userRepository); // Инициализация сервиса обогащения

    ExecutorService executorService = Executors.newFixedThreadPool(5); // Создание пула потоков
    CountDownLatch latch = new CountDownLatch(5); // Счетчик для ожидания завершения всех потоков

    // Запуск 5 потоков для обогащения сообщений
    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        Message message = new Message(new HashMap<>(), Message.EnrichmentType.MSISDN); // Создание сообщения
        enrichmentService.enrich(message); // Обогащение сообщения
        latch.countDown(); // Уменьшение счетчика
      });
    }

    latch.await(); // Ожидание завершения всех потоков
    executorService.shutdown(); // Завершение работы пула потоков
  }
}