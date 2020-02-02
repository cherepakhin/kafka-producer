package ru.perm.v.kafka.testproducer.service;

import java.util.UUID;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.dto.RequestDTO;
import ru.perm.v.kafka.dto.ResultDTO;
import ru.perm.v.kafka.model.Result;
import ru.perm.v.kafka.model.Status;
import ru.perm.v.kafka.testproducer.repository.ResultRepository;

/**
 * Сервис запросов на обработку
 */
@Service
public class ResultService {

  private static final Logger LOG =
      LoggerFactory.getLogger(ResultService.class);

  @Autowired
  ResultRepository resultRepository;

  @Autowired
  KafkaProducer kafkaProducer;

  /**
   * Генерация запросов
   *
   * @param qty - количество запросов для генерации
   */
  public void generate(Integer qty) {
    for (int i = 0; i < qty; i++) {
      Result result = new Result(UUID.randomUUID(), Status.WAIT);
      resultRepository.save(result);
      kafkaProducer.send(new RequestDTO(result));
    }
  }

  /**
   * Обработка результата
   */
  public void update(Result result) throws NotFoundException {
    if (!resultRepository.existsById(result.getGuid())) {
      String msgError = String.format("Process with GUID not found. "
          + "GUID: "
          + "%s", result.getGuid());
      LOG.error(msgError);
      throw new NotFoundException(msgError);
    }
    resultRepository.save(result);
  }

  public void updateByDTO(ResultDTO dto) throws NotFoundException {
    Result result = new Result(dto.getGuid(),
        Boolean.TRUE.equals(dto.getSuccess()) ? Status.SUCCESS :
            Status.FAIL);
    update(result);
  }
}
