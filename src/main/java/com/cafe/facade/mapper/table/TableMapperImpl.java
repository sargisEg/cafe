package com.cafe.facade.mapper.table;

import com.cafe.entity.Table;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.mapper.user.UserMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TableMapperImpl implements TableMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableMapperImpl.class);

    private final UserMapper userMapper;

    public TableMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public TableDto map(Table table) {
        Assert.notNull(
                table,
                "Class - TableMapperImpl, method - map(Table table) " +
                        "table should not be null"
        );
        LOGGER.debug("Mapping table - {}", table);

        final TableDto tableDto = new TableDto(
                table.getId(),
                userMapper.map(table.getWaiter())
        );

        LOGGER.debug("Successfully mapped table - {}, result - {}", table, tableDto);
        return tableDto;
    }
}
