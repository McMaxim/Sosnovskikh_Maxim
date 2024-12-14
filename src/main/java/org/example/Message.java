package org.example;

import java.util.Map;

/**
 * Класс, представляющий сообщение с содержимым и типом обогащения.
 */
public class Message {
  // Содержимое сообщения в виде пары "ключ-значение"
  private Map<String, String> content;

  // Тип обогащения сообщения
  private EnrichmentType enrichmentType;

  /**
   * Перечисление типов обогащения.
   */
  public enum EnrichmentType {
    MSISDN // Обогащение по номеру телефона
  }

  /**
   * Конструктор для создания сообщения.
   *
   * @param content        содержимое сообщения
   * @param enrichmentType тип обогащения
   */
  public Message(Map<String, String> content, EnrichmentType enrichmentType) {
    this.content = content;
    this.enrichmentType = enrichmentType;
  }

  /**
   * Получает содержимое сообщения.
   *
   * @return содержимое сообщения
   */
  public Map<String, String> getContent() {
    return content;
  }

  /**
   * Получает тип обогащения сообщения.
   *
   * @return тип обогащения
   */
  public EnrichmentType getEnrichmentType() {
    return enrichmentType;
  }
}