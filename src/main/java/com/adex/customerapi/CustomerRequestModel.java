package com.adex.customerapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CustomerRequestModel {

  @Min(1)
  int customerID;

  @Min(1)
  @NotNull
  int tagID;

  @NotNull
  @Pattern(message = "Wrong UUID", regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
  String userID;

  @NotNull
  @Pattern(message = "Wrong IP", regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
  String remoteIP;

  @NotNull
  @PastOrPresent
  Instant timestamp;

}
