package com.mill.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.mill.app.model.FlourOrder;

public interface FlourOrderRepository extends CrudRepository<FlourOrder, Long> {
}
