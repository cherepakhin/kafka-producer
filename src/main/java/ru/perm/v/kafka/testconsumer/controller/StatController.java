package ru.perm.v.kafka.testconsumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.kafka.dto.StatDTO;
import ru.perm.v.kafka.testconsumer.service.ConsumerService;

/**
 * Контроллер для получения статистики
 */
@RestController
@RequestMapping("/stat")
@Api(tags = "Контроллер для получения статистики")
public class StatController {

  @Autowired
  ConsumerService consumerService;

  /**
   * Получить статистику - Максимальное количество потоков; Число занятых
   * потоков; Среднее время работы потока; Число ожидающих процессов;
   *
   * @return - данные статистики
   */
  @GetMapping
  @ApiOperation(value = "Получить статистику", response =
      StatDTO.class)
  public StatDTO getStat() {
    return consumerService.getStat();
  }
}
