package com.adex.customerapi;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ua_blacklist")
@Data
public class UaModel {

  @Id
  @Setter
  String ua;
}
