package com.cafe.facade.manager;

import com.cafe.entity.*;
import com.cafe.facade.dto.AssignTableRequestDto;
import com.cafe.facade.dto.CreateProductRequestDto;
import com.cafe.facade.dto.CreateTableRequestDto;
import com.cafe.facade.dto.CreateUserRequestDto;
import com.cafe.facade.dto.entity.ProductDto;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.dto.entity.UserDto;
import com.cafe.facade.mapper.product.ProductMapper;
import com.cafe.facade.mapper.table.TableMapper;
import com.cafe.facade.mapper.user.UserMapper;
import com.cafe.service.core.product.CreateProductParams;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.table.CreateTableParams;
import com.cafe.service.core.table.TableService;
import com.cafe.service.core.table.UpdateTableParams;
import com.cafe.service.core.user.CreateUserParams;
import com.cafe.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ManagerFacadeImpl implements ManagerFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerFacadeImpl.class);

    private final UserService userService;
    private final TableService tableService;
    private final ProductService productService;
    private final UserMapper userMapper;
    private final TableMapper tableMapper;
    private final ProductMapper productMapper;

    public ManagerFacadeImpl(
            UserService userService,
            TableService tableService,
            ProductService productService,
            UserMapper userMapper,
            TableMapper tableMapper,
            ProductMapper productMapper) {
        this.userService = userService;
        this.tableService = tableService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.tableMapper = tableMapper;
        this.productMapper = productMapper;
    }

    @Override
    public UserDto createUser(CreateUserRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - ManagerFactoryImpl, method - createUser(CreateUserRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Creating user for provided request - {}", dto);

        final LocalDate date = LocalDate.now();
        final User user = userService.create(new CreateUserParams(
                dto.getUserRoles(),
                dto.getFirstName() + "_" + dto.getSecondName() + "_" + date,
                dto.getFirstName(),
                dto.getSecondName(),
                date
        ));

        final UserDto userDto = userMapper.map(user);

        LOGGER.info("Successfully created user for provided request - {}, response - {}", dto, userDto);
        return userDto;
    }

    @Override
    @Transactional
    public TableDto createTable(CreateTableRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - ManagerFactoryImpl, method - createTable(CreateTableRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Creating table for provided request - {}", dto);

        final Optional<User> optionalUser = userService.findByUsername(dto.getWaiterUsername());

        if (optionalUser.isEmpty()) {
            return new TableDto(List.of("Not found user with username - " + dto.getWaiterUsername()));
        }

        final Table table = tableService.create(new CreateTableParams(optionalUser.get().getId()));

        final TableDto tableDto = tableMapper.map(table);

        LOGGER.info("Successfully created table for provided request - {}, response - {}", dto, tableDto);
        return tableDto;
    }

    @Override
    public ProductDto createProduct(CreateProductRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - ManagerFactoryImpl, method - createProduct(CreateProductRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Creating product for provided request - {}", dto);

        final Product product = productService.create(new CreateProductParams(dto.getName(), dto.getAmount()));

        final ProductDto productDto = productMapper.map(product);

        LOGGER.info("Successfully created product for provided request - {}, response - {}", dto, productDto);
        return productDto;
    }

    @Override
    public TableDto assignTable(AssignTableRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - ManagerFactoryImpl, method - assignTable(AssignTableRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Assigning table for provided request - {}", dto);

        final Optional<Table> optionalTable = tableService.findById(dto.getTableId());

        if (optionalTable.isEmpty()) {
            return new TableDto(List.of("Not found table with id - " + dto.getTableId()));
        }

        final Optional<User> optionalUser = userService.findByUsername(dto.getWaiterUsername());

        if (optionalUser.isEmpty()) {
            return new TableDto(List.of("Not found user with username - " + dto.getWaiterUsername()));
        }

        final Table table = tableService.update(new UpdateTableParams(dto.getTableId(), optionalUser.get().getId()));

        final TableDto tableDto = tableMapper.map(table);

        LOGGER.info("Successfully assigned table for provided request - {}, response - {}", dto, tableDto);
        return tableDto;
    }
}
