package com.adex.customerapi;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ip_blacklist")
@Data
public class IpModel {

  @Id
  long ip;
}
