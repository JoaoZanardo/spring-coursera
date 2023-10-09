package com.zanardo.todo.services;

import com.zanardo.todo.adapters.Encrypter.Encrypter;
import com.zanardo.todo.adapters.Token.Token;
import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.models.User.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AuthServiceTest {

    @Mock
    UsersService usersService;

    @Mock
    Token token;

    @Mock
    Encrypter encrypter;

    AuthService authService;

    private final UserModel user = new UserModel(new UserDTO(
            "lucas",
            "123",
            UserRole.USER
    ));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authService = new AuthService(
                this.usersService,
                this.token,
                this.encrypter
        );
    }

    @Test()
    void shouldLogin() {
        Mockito.when(this.usersService.findByAccount("account")).thenReturn(this.user);
        Mockito.when(this.encrypter.compare("rawPassword", "encodedPassword" )).thenReturn(true);

        String token = this.authService.login(new AuthDTO("account", "rawPassword"));

        Assertions.assertNotNull(token);
        Assertions.assertTrue(!token.isEmpty());
    }
}
