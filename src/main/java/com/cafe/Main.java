package com.cafe;

import com.cafe.entity.UserRoleTypes;
import com.cafe.facade.dto.CreateProductRequestDto;
import com.cafe.facade.dto.CreateTableRequestDto;
import com.cafe.facade.dto.CreateUserRequestDto;
import com.cafe.facade.dto.entity.UserDto;
import com.cafe.facade.manager.ManagerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication(/*exclude = {SecurityAutoConfiguration.class}*/)
@EnableJpaRepositories
public class Main {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        final ManagerFacade managerFacade = context.getBean(ManagerFacade.class);
        final UserDto user1Dto = managerFacade.createUser(new CreateUserRequestDto(
                "First1",
                "Second1",
                List.of(UserRoleTypes.ROLE_MANAGER, UserRoleTypes.ROLE_WAITER)
        ));
        final UserDto user2Dto = managerFacade.createUser(new CreateUserRequestDto(
                "First2",
                "Second2",
                List.of(UserRoleTypes.ROLE_WAITER)
        ));
        final UserDto user3Dto = managerFacade.createUser(new CreateUserRequestDto(
                "First3",
                "Second3",
                List.of(UserRoleTypes.ROLE_WAITER)
        ));

        managerFacade.createTable(new CreateTableRequestDto(user1Dto.getUsername()));
        managerFacade.createTable(new CreateTableRequestDto(user1Dto.getUsername()));

        managerFacade.createTable(new CreateTableRequestDto(user2Dto.getUsername()));
        managerFacade.createTable(new CreateTableRequestDto(user2Dto.getUsername()));

        managerFacade.createTable(new CreateTableRequestDto(user3Dto.getUsername()));
        managerFacade.createTable(new CreateTableRequestDto(user3Dto.getUsername()));

        managerFacade.createProduct(new CreateProductRequestDto("Fanta", 10L));
        managerFacade.createProduct(new CreateProductRequestDto("Cola", 10L));
        managerFacade.createProduct(new CreateProductRequestDto("Taco", 50L));
        managerFacade.createProduct(new CreateProductRequestDto("BBQ", 50L));

    }
}
