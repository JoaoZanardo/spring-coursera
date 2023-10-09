package com.zanardo.todo.filters;

import com.zanardo.todo.adapters.Token.Token;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.repositories.UsersRepository;
import com.zanardo.todo.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    Token token;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token != null) {
            String account = this.token.validate(token);

            UserModel user = this.usersService.findByAccount(account);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;

        return authHeader.replace("Bearer ", "");

    }
}
