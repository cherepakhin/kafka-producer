package ru.perm.v.kafka.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Запрос, отправленный в Kafka
 */
@Entity
public class Request {

  @Id
  private UUID guid;

  // Результат запроса
  private Status status = Status.WAIT;

  public Request() {
  }

  public Request(UUID guid, Status status) {
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
    if (!(o instanceof Request)) {
      return false;
    }

    Request request = (Request) o;

    return guid.equals(request.guid);
  }

  @Override
  public int hashCode() {
    return guid.hashCode();
  }

  @Override
  public String toString() {
    return "Request{" +
        "guid=" + guid +
        ", status=" + status +
        '}';
  }
}
