package ru.perm.v.kafka.testconsumer.service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.perm.v.kafka.model.Request;

public class UserTask implements Supplier<Request> {

  private static final Logger LOG = LoggerFactory.getLogger(UserTask.class);
  private final Long delayMs;

  Request request;

  public UserTask(Request request, Long delayMs) {
    this.request = request;
    this.delayMs = delayMs;
  }

  @Override
  public Request get() {
    LOG.info("UserTask Request: {}", request);
    try {
      TimeUnit.MILLISECONDS.sleep(delayMs);
    } catch (InterruptedException e) {
      LOG.info(e.getLocalizedMessage());
    }
    return request;
  }
}
