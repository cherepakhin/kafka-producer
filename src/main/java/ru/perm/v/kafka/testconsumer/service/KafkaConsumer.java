package ru.perm.v.kafka.testconsumer.service;

import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.dto.RequestDTO;

@Service
public class KafkaConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(
      KafkaConsumer.class);

  @Value("${threads-max}")
  private Integer threadsMax;

  @KafkaListener(topics = "${topic}", groupId = "${group-id}",
      containerFactory = "kafkaListenerContainerFactory")
  public void consumeRequestDTO(ConsumerRecord<String, RequestDTO> cr,
      @Payload RequestDTO data) {
    LOG.info("Consumed RequestDTO Message: {}", data);
    //TODO: Обработать сообщение
  }

  @PostConstruct
  public void init() {
    LOG.info("Start consumer");
  }
}
