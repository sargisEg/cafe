package com.cafe.service.impl;

import com.cafe.entity.Table;
import com.cafe.entity.User;
import com.cafe.repository.TableRepository;
import com.cafe.service.core.table.CreateTableParams;
import com.cafe.service.core.table.TableService;
import com.cafe.service.core.table.UpdateTableParams;
import com.cafe.service.core.user.UserService;
import com.cafe.service.impl.exceptions.TableNotFoundException;
import com.cafe.service.impl.exceptions.UserNotFoundException;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
class TableServiceImpl implements TableService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);

    private final TableRepository tableRepository;

    private final UserService userService;

    public TableServiceImpl(TableRepository tableRepository, UserService userService) {
        this.tableRepository = tableRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Table create(CreateTableParams params) {
        Assert.notNull(
                params,
                "Class - TableServiceImpl, method - create(CreateTableParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Creating table with params - {}", params);

        final User user = userService.findById(params.getWaiterId())
                .orElseThrow(() -> {
                    throw new UserNotFoundException(params.getWaiterId());
                });

        final Table tableFromParams = new Table(user);

        final Table table = tableRepository.save(tableFromParams);

        LOGGER.info("Successfully created table with params - {}, result - {}", params, table);
        return table;
    }

    @Override
    @Transactional
    public Table update(UpdateTableParams params) {
        Assert.notNull(
                params,
                "Class - TableServiceImpl, method - update(UpdateTableParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Updating table with params - {}", params);

        final Table table = tableRepository.findById(params.getId())
                .orElseThrow(() -> {
                    throw new TableNotFoundException(params.getId());
                });

        final User user = userService.findById(params.getWaiterId())
                .orElseThrow(() -> {
                    throw new UserNotFoundException(params.getWaiterId());
                });

        table.setId(params.getId());
        table.setWaiter(user);

        LOGGER.info("Successfully updated table with params - {}, result - {}", params, table);
        return table;
    }

    @Override
    public Optional<Table> findById(Long id) {
        Assert.notNull(
                id,
                "Class - TableServiceImpl, method - findById(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding table with id - {}", id);

        final Optional<Table> optionalTable = tableRepository.findById(id);

        LOGGER.info("Successfully found table with id - {}, result - {}", id, optionalTable);
        return optionalTable;
    }

    @Override
    public List<Table> findByWaiterId(Long id) {
        Assert.notNull(
                id,
                "Class - TableServiceImpl, method - findByWaiterId(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding tables with waiter id - {}", id);

        final List<Table> tableList = tableRepository.findByWaiterId(id);

        LOGGER.info("Successfully found tables with waiter id - {}, result - {}", id, tableList);
        return tableList;
    }
}
