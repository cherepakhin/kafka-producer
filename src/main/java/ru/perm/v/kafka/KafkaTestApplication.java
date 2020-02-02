package ru.perm.v.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ru.perm.v.*")
@SpringBootApplication
public class KafkaTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaTestApplication.class, args);
  }

}
