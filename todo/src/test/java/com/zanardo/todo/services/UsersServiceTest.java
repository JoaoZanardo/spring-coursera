package com.zanardo.todo.services;

import com.zanardo.todo.customExceptions.Conflict;
import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.models.User.UserRole;
import com.zanardo.todo.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {
    @Mock
    private UsersRepository usersRepository;

    private UsersService usersService;

    private final UserModel user = new UserModel(new UserDTO(
            "lucas",
            "123",
            UserRole.USER
    ));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usersService = new UsersService(this.usersRepository);
    }

    @Test
    void shouldFindById() {
        Mockito.when(this.usersRepository.findById("userId")).thenReturn(Optional.of(this.user));

        UserModel actualUser = this.usersService.findById("userId");

        assertEquals(this.user, actualUser);
    }

    @Test
    void shouldFindByAccount() {
        Mockito.when(this.usersRepository.findByAccount("account")).thenReturn(Optional.of(this.user));

        UserModel actualUser = this.usersService.findByAccount("account");

        assertEquals(this.user, actualUser);
    }

    @Test
    void shouldThrowNotFoundWhenFindById() {
        Mockito.when(this.usersRepository.findById("userId")).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> this.usersService.findById("userId"));
    }

    @Test
    void shouldCreate() {
        Mockito.when(this.usersRepository.save(this.user)).thenReturn(this.user);

        assertEquals(this.usersService.create(this.user), this.user);
    }

    @Test
    void shouldThrowConflictWhenCreate() {
        Mockito.when(this.usersRepository.findByAccount(this.user.getAccount())).thenReturn(Optional.of(this.user));

        assertThrows(Conflict.class, () -> this.usersService.create(this.user));
    }

    @Test
    void shouldList() {
        List<UserModel> userList = new ArrayList<>();
        Mockito.when(this.usersRepository.findAll()).thenReturn(userList);

        List<UserModel> actualUserList = this.usersService.list();

        assertEquals(userList, actualUserList);
    }

    @Test
    void shouldReturnTrueInExistsAccount() {
        Mockito.when(this.usersRepository.findByAccount("account")).thenReturn(Optional.of(this.user));

        boolean exists = this.usersService.existsAccount("account");

        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseInExistsAccount() {
        Mockito.when(this.usersRepository.findByAccount("account")).thenReturn(Optional.empty());

        boolean exists = this.usersService.existsAccount("account");

        assertFalse(exists);
    }
}