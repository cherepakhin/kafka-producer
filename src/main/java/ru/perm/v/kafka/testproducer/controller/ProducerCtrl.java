package ru.perm.v.kafka.testproducer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.kafka.dto.ResultDTO;
import ru.perm.v.kafka.testproducer.service.ResultService;

@RestController
@Api(tags = "Producer Kafka")
@RequestMapping("")
public class ProducerCtrl {

  private static final Logger LOG = LoggerFactory.getLogger(ProducerCtrl.class);

  @Autowired
  ResultService resultService;

  @GetMapping("/echo")
  public String echo(@RequestParam String msg) {
    LOG.info("----------- Echo: {}", msg);
    return msg;
  }

  /**
   * Получение результата обработки
   *
   * @param dto - результат обработки
   */
  @PostMapping("/set-result")
  @ApiOperation(value = "Получение результата обработки")
  public void recieveResult(@RequestBody ResultDTO dto) {
    try {
      resultService.updateByDTO(dto);
    } catch (NotFoundException e) {
      LOG.error("Not found entity with GUID: {}",dto.getGuid());
    }
  }

  /**
   * Генерирование запросов для Kafka
   *
   * @param qty -Кол-во запросов
   */
  @PostMapping("/generate-req")
  @ApiOperation(value = "Генерирование запросов для Kafka")
  public void generateRequest(
      @ApiParam(value = "Кол-во запросов", required = true)
      @RequestParam Integer qty) {
    resultService.generate(qty);
  }
}
