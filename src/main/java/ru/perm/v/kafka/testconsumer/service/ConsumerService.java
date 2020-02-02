package ru.perm.v.kafka.testconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.dto.StatDTO;
import ru.perm.v.kafka.testconsumer.repository.RequestRepository;

@Service
public class ConsumerService {

  @Autowired
  RequestRepository requestRepository;

  /**
   * Получить статистику
   *
   * @return - статистика
   */
  //TODO: Получить статистику
  public StatDTO getStat() {
    return new StatDTO();
  }
}
