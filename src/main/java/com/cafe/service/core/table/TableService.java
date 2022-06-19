package com.cafe.service.core.table;

import com.cafe.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TableService {

    Table create(CreateTableParams params);

    Table update(UpdateTableParams params);

    Optional<Table> findById(Long id);

    List<Table> findByWaiterId(Long id);
}
