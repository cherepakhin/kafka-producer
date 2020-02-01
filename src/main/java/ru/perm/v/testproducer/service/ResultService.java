package ru.perm.v.testproducer.service;

import javassist.NotFoundException;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.model.Result;
import ru.perm.v.testproducer.repository.ResultRepository;

/**
 * Сервис запросов на обработку
 */
@Service
public class ResultService {

  private static final Logger LOG =
      LoggerFactory.getLogger(ResultService.class);

  @Autowired
  ResultRepository resultRepository;

  /**
   * Генерация запросов
   *
   * @param qty - количество запросов для генерации
   */
  public void generate(Integer qty) {
  }

  /**
   * Обработка результата
   */
  @Transactional
  public void update(Result result) throws NotFoundException {
    if (resultRepository.existsById(result.getGuid())) {
      throw new NotFoundException(String.format("Process not found. GUID: "
          + "%s", result.getGuid()));
    }
    resultRepository.save(result);
  }
}
