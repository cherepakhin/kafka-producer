package ru.perm.v.kafka.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Результат выполнения запроса, отправленного в Kafka
 */
@Entity
public class Result {

  // Идентификатор запроса
  @Id
  private UUID guid;

  // Результат запроса
  private Status status = Status.WAIT;

  public Result() {
  }


  public Result(UUID guid, Status status) {
    this.guid = guid;
    this.status = status;
  }

  public UUID getGuid() {
    return guid;
  }

  public void setGuid(UUID guid) {
    this.guid = guid;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Result)) {
      return false;
    }

    Result result = (Result) o;

    return guid != null ? guid.equals(result.guid) : result.guid == null;
  }

  @Override
  public int hashCode() {
    return guid != null ? guid.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Result{" +
        "guid=" + guid +
        ", status=" + status +
        '}';
  }
}
