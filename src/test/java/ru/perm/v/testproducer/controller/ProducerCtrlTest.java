package ru.perm.v.testproducer.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ProducerCtrlTest {

  //  @Autowired
  ProducerCtrl producerCtrl = new ProducerCtrl();

  @Test
  void generateRequest() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> producerCtrl.generateRequest(1)
    );
  }
}