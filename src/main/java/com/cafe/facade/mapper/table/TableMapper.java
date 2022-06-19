package com.cafe.facade.mapper.table;

import com.cafe.entity.Table;
import com.cafe.facade.dto.entity.TableDto;

public interface TableMapper {

    TableDto map(Table table);
}
