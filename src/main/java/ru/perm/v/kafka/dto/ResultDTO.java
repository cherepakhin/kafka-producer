package ru.perm.v.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;

/**
 * DTO-результат выполнения запроса, отправленного в Kafka
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Результат выполнения запроса.")
public class ResultDTO {

  @JsonProperty(required = true)
  @ApiModelProperty(notes = "Идентификатор запроса")
  private UUID guid;

  @JsonProperty(required = true)
  @ApiModelProperty(notes = "Результат запроса (успешно/не успешно)")
  private Boolean success = false;

  public ResultDTO() {
  }

  public ResultDTO(UUID guid, Boolean success) {
    this.guid = guid;
    this.success = success;
  }

  public UUID getGuid() {
    return guid;
  }

  public void setGuid(UUID guid) {
    this.guid = guid;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return "ResultDTO{" +
        "guid=" + guid +
        ", success=" + success +
        '}';
  }
}
