package com.adex.customerapi;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Entity
public class HourlyStats {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Customer customer;

  @Column(name = "time")
  private Instant timestamp;

  public void setTimestamp(Instant timestamp) {
    this.timestamp = Converters.truncateMinutes(timestamp);
  }

  private long validCount;

  private long invalidCount;

}
