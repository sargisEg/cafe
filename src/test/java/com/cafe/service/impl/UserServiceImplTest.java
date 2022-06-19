package com.cafe.service.impl;

import com.cafe.entity.User;
import com.cafe.entity.UserRoleTypes;
import com.cafe.repository.UserRepository;
import com.cafe.service.core.user.CreateUserParams;
import com.cafe.service.core.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService testSubject;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        testSubject = new UserServiceImpl(userRepository);
    }

    @Test
    public void testCreateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(
                () -> testSubject.create(null)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(
                () -> testSubject.findById(null)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByUsernameWhenUsernameIsNull() {
        Assertions.assertThatThrownBy(
                () -> testSubject.findByUsername(null)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreate() {
        final User user = new User(
                UserRoleTypes.ROLE_MANAGER,
                "username",
                "first",
                "second",
                LocalDate.now()
        );
        user.setId(1L);
        when(userRepository.save(new User(
                UserRoleTypes.ROLE_MANAGER,
                "username",
                "first",
                "second",
                LocalDate.now()
                ))
        ).thenReturn(user);

        Assertions.assertThat(
                testSubject.create(new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        "first",
                        "second",
                        LocalDate.now()
                ))
        ).isEqualTo(user);

        verify(userRepository).save(new User(
                UserRoleTypes.ROLE_MANAGER,
                "username",
                "first",
                "second",
                LocalDate.now()
        ));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindById() {
        final User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        Assertions.assertThat(testSubject.findById(1L))
                .isEqualTo(Optional.of(user));

        verify(userRepository).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindByUsername() {
        final User user = new User();
        user.setUsername("username");

        when(userRepository.findByUsername("username"))
                .thenReturn(Optional.of(user));

        Assertions.assertThat(testSubject.findByUsername("username"))
                .isEqualTo(Optional.of(user));

        verify(userRepository).findByUsername("username");
        verifyNoMoreInteractions(userRepository);
    }
}