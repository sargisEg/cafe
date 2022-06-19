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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TableServiceImplTest {

    private TableService testSubject;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        testSubject = new TableServiceImpl(tableRepository, userService);
    }

    @Test
    public void testCreateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.create(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUpdateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.update(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.findById(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByWaiterIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.findByWaiterId(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateWhenWaiterNotFound() {
        when(userService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.create(new CreateTableParams(1L)))
                .isExactlyInstanceOf(UserNotFoundException.class);

        verify(userService).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testCreate() {
        final Table table = new Table();
        table.setId(1L);

        when(userService.findById(1L))
                .thenReturn(Optional.of(new User()));

        when(tableRepository.save(new Table(new User())))
                .thenReturn(table);

        Assertions.assertThat(testSubject.create(new CreateTableParams(1L)))
                        .isEqualTo(table);

        verify(tableRepository).save(new Table(new User()));
        verify(userService).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testUpdateWhenTableNotFound() {
        when(tableRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.update(new UpdateTableParams(1L, 1L)))
                .isExactlyInstanceOf(TableNotFoundException.class);

        verify(tableRepository).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testUpdateWhenWaiterNotFound() {
        when(tableRepository.findById(1L))
                .thenReturn(Optional.of(new Table()));

        when(userService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.update(new UpdateTableParams(1L, 1L)))
                .isExactlyInstanceOf(UserNotFoundException.class);

        verify(tableRepository).findById(1L);
        verify(userService).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testUpdate() {
        when(tableRepository.findById(1L))
                .thenReturn(Optional.of(new Table()));

        when(userService.findById(1L))
                .thenReturn(Optional.of(new User()));

        final Table expectedTable = new Table(new User());
        expectedTable.setId(1L);
        Assertions.assertThat(testSubject.update(new UpdateTableParams(1L, 1L)))
                .isEqualTo(expectedTable);

        verify(tableRepository).findById(1L);
        verify(userService).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testFindById() {
        when(tableRepository.findById(1L))
                .thenReturn(Optional.of(new Table()));

        Assertions.assertThat(testSubject.findById(1L))
                .isEqualTo(Optional.of(new Table()));

        verify(tableRepository).findById(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    public void testFindByWaiterId() {
        when(tableRepository.findByWaiterId(1L))
                .thenReturn(List.of(new Table()));

        Assertions.assertThat(testSubject.findByWaiterId(1L))
                        .isEqualTo(List.of(new Table()));

        verify(tableRepository).findByWaiterId(1L);
        verifyNoMoreInteractions(tableRepository, userService);
    }
}