package ru.perm.v.testproducer.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import ru.perm.v.kafka.dto.RequestDTO;

/**
 * Настройка kafka producer
 */
@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    // Сериализовать объекты в json
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        JsonSerializer.class);
    return props;
  }

  @Bean
  public ProducerFactory<String, RequestDTO> producerFactory() {
    DefaultKafkaProducerFactory<String, RequestDTO> factory = new DefaultKafkaProducerFactory<>(
        producerConfigs());
    factory.setTransactionIdPrefix("tx-kafka-");
    return factory;
  }

  @Bean
  public KafkaTemplate<String, RequestDTO> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public KafkaTransactionManager kafkaTransactionManager() {
    KafkaTransactionManager ktm = new KafkaTransactionManager(
        producerFactory());
    ktm.setTransactionSynchronization(
        AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
    return ktm;
  }


  @Bean
  @Primary
  public JpaTransactionManager transactionManager(EntityManagerFactory em) {
    return new JpaTransactionManager(em);
  }

  @Bean(name = "chainedTransactionManager")
  public ChainedTransactionManager chainedTransactionManager(
      JpaTransactionManager jpaTransactionManager,
      KafkaTransactionManager kafkaTransactionManager) {
    return new ChainedTransactionManager(kafkaTransactionManager,
        jpaTransactionManager);
  }
}
