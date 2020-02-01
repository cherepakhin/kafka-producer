package ru.perm.v.testproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
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
  String TOPIC;

  @Autowired
  private KafkaTemplate<String, RequestDTO> kafkaTemplate;

  /**
   * Отправка соощения в Kafka
   *
   * @param dto - запрос на выполнение
   */
  public void send(RequestDTO dto) {
    LOG.info(String.format("Sending REQUEST: %s", dto));
    ListenableFuture<SendResult<String, RequestDTO>> result = kafkaTemplate
        .send(TOPIC, dto);
    result.completable().thenAccept(r -> LOG.info(String.format("Sending result: %s", r.toString())));
  }
}
