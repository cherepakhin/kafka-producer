package ru.perm.v.testproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ru.perm.v.*")
@SpringBootApplication
public class KafkaProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaProducerApplication.class, args);
  }

}
