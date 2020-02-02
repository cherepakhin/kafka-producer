package ru.perm.v.kafka.testconsumer.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javassist.NotFoundException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.perm.v.kafka.dto.RequestDTO;
import ru.perm.v.kafka.model.Request;
import ru.perm.v.kafka.model.Result;
import ru.perm.v.kafka.model.Status;
import ru.perm.v.kafka.testconsumer.repository.RequestRepository;
import ru.perm.v.kafka.testproducer.service.ResultService;

@Service
public class Processor {

  private static final Logger LOG = LoggerFactory.getLogger(Processor.class);
  @Autowired
  RequestRepository requestRepository;
  @Autowired
  ResultService resultService;
  @Value("${threads-max}")
  private Integer threadsMax;
  @Value("${delay-user-process}")
  private Long delayUserProcess;

  private ThreadPoolExecutor executorService;


  @PostConstruct
  public void init() {
    LOG.info("Start PROCESSOR");
    executorService = (ThreadPoolExecutor) Executors
        .newFixedThreadPool(threadsMax);
  }

  public void runDTO(RequestDTO dto) {
    Request request = new Request(dto.getGuid(), Status.WAIT);
    requestRepository.save(request);

    LOG.info("PROCESS DTO: {}", dto);

    runRequest(request);
  }

  public void runRequest(Request request) {
    int countFreeThreads =
        threadsMax - executorService.getActiveCount();
    LOG.info("countFreeThreads {}", countFreeThreads);
    if (countFreeThreads > 0) {
      CompletableFuture
          .supplyAsync(new UserTask(request, delayUserProcess),
              executorService).thenAccept(r -> {
        request.setStatus(Status.SUCCESS);
        requestRepository.save(request);

        try {
          resultService
              .update(new Result(request.getGuid(), request.getStatus()));
        } catch (NotFoundException e) {
          LOG.error(e.getLocalizedMessage());
        }
      });
    }
  }

}
