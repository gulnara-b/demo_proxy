package com.adex.customerapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class AcceptResponse {
  @Getter
  @Setter
  private boolean accepted;
  @Getter
  @Setter
  private String message;

}
