package com.cafe.facade.manager;

import com.cafe.entity.Product;
import com.cafe.entity.Table;
import com.cafe.entity.User;
import com.cafe.entity.UserRoleTypes;
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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerFacadeImplTest {

    private ManagerFacade testSubject;

    @Mock
    private UserService userService;

    @Mock
    private TableService tableService;

    @Mock
    private ProductService productService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TableMapper tableMapper;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void init() {
        testSubject = new ManagerFacadeImpl(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testCreateUserWhenDtoIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.createUser(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateTableWhenDtoIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.createTable(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateProductWhenDtoIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.createProduct(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testAssignTableWhenDtoIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.assignTable(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUser() {
        final User user = new User(UserRoleTypes.ROLE_MANAGER,
                "username",
                "first",
                "second",
                LocalDate.now()
        );
        user.setId(1L);
        when(userService.create(new CreateUserParams(
                UserRoleTypes.ROLE_MANAGER,
                "first_second_" + LocalDate.now(),
                "first",
                "second",
                LocalDate.now()
        ))).thenReturn(user);

        when(userMapper.map(user))
                .thenReturn(new UserDto(List.of("test")));

        Assertions.assertThat(testSubject.createUser(new CreateUserRequestDto(
                "username",
                "first",
                "second",
                UserRoleTypes.ROLE_MANAGER
        ))).isEqualTo(new UserDto(List.of("test")));

        verify(userService).create(new CreateUserParams(
                UserRoleTypes.ROLE_MANAGER,
                "first_second_" + LocalDate.now(),
                "first",
                "second",
                LocalDate.now()
        ));
        verify(userMapper).map(user);
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testCreateProduct() {

        when(productService.create(new CreateProductParams("name", 12L)))
                .thenReturn(new Product());
        when(productMapper.map(new Product()))
                .thenReturn(new ProductDto(List.of("test")));

        Assertions.assertThat(testSubject.createProduct(new CreateProductRequestDto("name", 12L)))
                .isEqualTo(new ProductDto(List.of("test")));

        verify(productService).create(new CreateProductParams("name", 12L));
        verify(productMapper).map(new Product());
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testCreateTableWhenUserNotFound() {
        when(userService.findByUsername("username"))
                .thenReturn(Optional.empty());

        Assertions.assertThat(testSubject.createTable(new CreateTableRequestDto("username")))
                .isEqualTo(new TableDto(List.of("Not found user with username - username")));

        verify(userService).findByUsername("username");
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testCreateTable() {
        final User user = new User();
        user.setId(1L);
        when(userService.findByUsername("username"))
                .thenReturn(Optional.of(user));

        when(tableService.create(new CreateTableParams(1L)))
                .thenReturn(new Table());

        when(tableMapper.map(new Table()))
                .thenReturn(new TableDto(List.of("Test")));

        Assertions.assertThat(testSubject.createTable(new CreateTableRequestDto("username")))
                .isEqualTo(new TableDto(List.of("Test")));

        verify(userService).findByUsername("username");
        verify(tableService).create(new CreateTableParams(1L));
        verify(tableMapper).map(new Table());
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testAssignTableWhenTableNotFound() {
        when(tableService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThat(testSubject.assignTable(new AssignTableRequestDto(1L, "user")))
                .isEqualTo(new TableDto(List.of("Not found table with id - 1")));

        verify(tableService).findById(1L);
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testAssignTableWhenUserNotFound() {
        when(tableService.findById(1L))
                .thenReturn(Optional.of(new Table()));

        when(userService.findByUsername("user"))
                .thenReturn(Optional.empty());

        Assertions.assertThat(testSubject.assignTable(new AssignTableRequestDto(1L, "user")))
                .isEqualTo(new TableDto(List.of("Not found user with username - user")));

        verify(tableService).findById(1L);
        verify(userService).findByUsername("user");
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }

    @Test
    public void testAssignTable() {
        when(tableService.findById(1L))
                .thenReturn(Optional.of(new Table()));

        final User user = new User();
        user.setId(1L);
        when(userService.findByUsername("user"))
                .thenReturn(Optional.of(user));

        when(tableService.update(new UpdateTableParams(1L, 1L)))
                .thenReturn(new Table());

        when(tableMapper.map(new Table()))
                .thenReturn(new TableDto(List.of("test")));

        Assertions.assertThat(testSubject.assignTable(new AssignTableRequestDto(1L, "user")))
                .isEqualTo(new TableDto(List.of("test")));

        verify(tableService).findById(1L);
        verify(userService).findByUsername("user");
        verify(tableService).update(new UpdateTableParams(1L, 1L));
        verify(tableMapper).map(new Table());
        verifyNoMoreInteractions(
                userService,
                tableService,
                productService,
                userMapper,
                tableMapper,
                productMapper
        );
    }
}