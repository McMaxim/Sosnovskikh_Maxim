package org.example;

import java.util.Map;

/**
 * Сервис для обогащения сообщений данными пользователей.
 */
public class EnrichmentService {
  private final UserRep userRep;

  /**
   * Конструктор для инициализации EnrichmentService с репозиторием пользователей.
   *
   * @param userRep репозиторий пользователей
   */
  public EnrichmentService(UserRep userRep) {
    this.userRep = userRep;
  }

  /**
   * Обогащает сообщение данными пользователя на основе MSISDN.
   *
   * @param message сообщение, которое нужно обогатить
   * @return обогащенное сообщение
   */
  public Message enrich(Message message) {
    // Проверяем, требуется ли обогащение по MSISDN
    if (message.getEnrichmentType() == Message.EnrichmentType.MSISDN) {
      Map<String, String> content = message.getContent();
      String msisdn = content.get("msisdn");

      // Если MSISDN не null, ищем пользователя
      if (msisdn != null) {
        User user = userRep.findByMsisdn(msisdn);
        // Если пользователь найден, добавляем его данные в сообщение
        if (user != null) {
          content.put("firstName", user.getFirstName());
          content.put("lastName", user.getLastName());
        }
      }
    }
    // Возвращаем обогащенное сообщение
    return message;
  }
}
