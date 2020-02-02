package ru.perm.v.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan("ru.perm.v.*")
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class KafkaTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaTestApplication.class, args);
  }

}
