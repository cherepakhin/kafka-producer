package ru.perm.v.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;

/**
 * Запрос на получение результата
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Запрос на получение результата")
public class RequestDTO {

  @JsonProperty(required = true)
  @ApiModelProperty(notes = "Идентификатор запроса")
  private UUID guid;

  public UUID getGuid() {
    return guid;
  }

  public void setGuid(UUID guid) {
    this.guid = guid;
  }

  @Override
  public String toString() {
    return "RequestDTO{" +
        "guid=" + guid +
        '}';
  }
}
