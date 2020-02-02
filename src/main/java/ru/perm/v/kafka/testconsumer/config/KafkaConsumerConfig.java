package ru.perm.v.kafka.testconsumer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.perm.v.kafka.dto.RequestDTO;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  // Группа consumer-ов
  @Value("${group-id}")
  private String groupId;

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return props;
  }

  /**
   * Для Json Обязательно назвать один из бинов consumerFactory() Будет
   * использоваться по умолчанию
   */
  @Bean
  public ConsumerFactory<String, RequestDTO> consumerFactory() {
    final JsonDeserializer<RequestDTO> jsonDeserializer = new JsonDeserializer<>();
    jsonDeserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(
        consumerConfigs(),
        new StringDeserializer(),
        jsonDeserializer);
  }

  /**
   * Обязательно назвать один из бинов kafkaListenerContainerFactory() Будет
   * использоваться по умолчанию
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, RequestDTO> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, RequestDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
