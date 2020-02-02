package ru.perm.v.kafka.testproducer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.perm.v.kafka.dto.RequestDTO;
import ru.perm.v.kafka.dto.ResultDTO;
import ru.perm.v.kafka.model.Result;
import ru.perm.v.kafka.model.Status;
import ru.perm.v.kafka.testproducer.repository.ResultRepository;

@SpringBootTest(classes = {ResultService.class})
class ResultServiceTest {

  @Autowired
  ResultService resultService;

  @MockBean
  ResultRepository resultRepository;

  @MockBean
  KafkaProducer kafkaProducer;

  @Test
  void generate() {
    final int qty = 10;
    Result result = mock(Result.class);
    ArgumentCaptor<Result> resultCapture =
        ArgumentCaptor.forClass(Result.class);
    when(resultRepository.save(resultCapture.capture())).thenReturn(result);

    ArgumentCaptor<RequestDTO> dtoCapture =
        ArgumentCaptor.forClass(RequestDTO.class);
    doNothing().when(kafkaProducer).send(dtoCapture.capture());

    resultService.generate(qty);

    resultCapture.getAllValues().forEach(r -> assertEquals(Status.WAIT,
        r.getStatus()));
    verify(resultRepository, times(qty)).save(any(Result.class));
    verify(kafkaProducer, times(qty)).send(any(RequestDTO.class));
  }

  @Test
  void updateNotExistGuid() {
    given(this.resultRepository.existsById(any()))
        .willReturn(false);
    assertThrows(NotFoundException.class,
        () -> resultService.update(new Result()));
  }

  @Test
  void update() throws NotFoundException {
    UUID guid = UUID.randomUUID();
    Result result = new Result(guid, Status.SUCCESS);

    when(this.resultRepository.existsById(guid))
        .thenReturn(true);
    when(this.resultRepository.save(result)).thenReturn(result);
    resultService.update(result);
    verify(resultRepository, times(1)).existsById(guid);
    verify(resultRepository, times(1)).save(result);
  }

  @Test
  void updateBySuccessDTO() throws NotFoundException {
    ResultService mockService = spy(resultService);

    UUID guid = UUID.randomUUID();
    ResultDTO dto = new ResultDTO(guid, true);
    Result result = new Result(guid, Status.SUCCESS);

    doNothing().when(mockService).update(result);

    mockService.updateByDTO(dto);
    verify(mockService, times(1)).update(result);
  }

  @Test
  void updateByFailDTO() throws NotFoundException {
    ResultService mockService = spy(resultService);

    UUID guid = UUID.randomUUID();
    ResultDTO dto = new ResultDTO(guid, false);
    Result result = new Result(guid, Status.FAIL);

    doNothing().when(mockService).update(result);

    mockService.updateByDTO(dto);
    verify(mockService, times(1)).update(result);
  }
}