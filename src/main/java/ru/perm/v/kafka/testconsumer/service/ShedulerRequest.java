package ru.perm.v.kafka.testconsumer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.perm.v.kafka.model.Request;
import ru.perm.v.kafka.model.Status;
import ru.perm.v.kafka.testconsumer.repository.RequestRepository;

@Component
public class ShedulerRequest {

  private static final Logger LOG = LoggerFactory
      .getLogger(ShedulerRequest.class);

  @Autowired
  RequestRepository requestRepository;
  @Autowired
  Processor processor;

  @Value("${threads-max}")
  private Integer limit;

  @Scheduled(fixedRateString = "${fixedRate}")
  private void checkWaitRequest() {
    List<Request> requsts =
        requestRepository.findByStatus(Status.WAIT, PageRequest.of(0, limit));
    for (Request r : requsts) {
      LOG.info("-----------------From Sheduler requst: {}", r);
      processor.runRequest(r);
    }
  }
}
