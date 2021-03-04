package com.adex.customerapi.data;

import com.adex.customerapi.UaModel;
import org.springframework.data.repository.CrudRepository;

public interface UaRepository extends CrudRepository<UaModel, String> {
}
