package ru.perm.v.kafka.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Стат.данные работы consumer-а
 * <p>
 * Максимальное количество потоков; Число занятых потоков; Среднее время работы
 * потока; Число ожидающих процессов;
 */
@ApiModel(description = "Стат.данные работы consumer-а")
public class StatDTO {

  @ApiModelProperty(notes = "Максимальное кол-во потоков")
  private Integer maxThreadQty = 0;
  @ApiModelProperty(notes = "Число занятых потоков")
  private Integer busyThreadQty = 0;
  @ApiModelProperty(notes = "Среднее время работы потока в миллисекундах")
  private Integer avgTimeThreadMs = 0;
  @ApiModelProperty(notes = "Число ожидающих процессов")
  private Integer waitThreadQty = 0;

  public Integer getMaxThreadQty() {
    return maxThreadQty;
  }

  public void setMaxThreadQty(Integer maxThreadQty) {
    this.maxThreadQty = maxThreadQty;
  }

  public Integer getBusyThreadQty() {
    return busyThreadQty;
  }

  public void setBusyThreadQty(Integer busyThreadQty) {
    this.busyThreadQty = busyThreadQty;
  }

  public Integer getAvgTimeThreadMs() {
    return avgTimeThreadMs;
  }

  public void setAvgTimeThreadMs(Integer avgTimeThreadMs) {
    this.avgTimeThreadMs = avgTimeThreadMs;
  }

  public Integer getWaitThreadQty() {
    return waitThreadQty;
  }

  public void setWaitThreadQty(Integer waitThreadQty) {
    this.waitThreadQty = waitThreadQty;
  }
}
