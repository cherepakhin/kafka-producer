package ru.perm.v.kafka.testproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.dto.RequestDTO;

/**
 * Producer для Kafka
 */
@Service
public class KafkaProducer {

  private static final Logger LOG = LoggerFactory
      .getLogger(KafkaProducer.class);

  // Тема
  @Value("${topic}")
  String topic;

  @Autowired
  private KafkaTemplate<String, RequestDTO> kafkaTemplate;

  /**
   * Отправка соощения в Kafka
   *
   * @param dto - запрос на выполнение
   */
  public void send(RequestDTO dto) {
    LOG.info("Sending REQUEST: {}", dto);
    kafkaTemplate.executeInTransaction(t -> t.send(topic, dto));
  }
}
