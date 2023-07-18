package com.hellobirdie.chatflow.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellobirdie.chatflow.auth.ChatflowUserDetail;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    private static final String EMAIL_LOGIN_URL = "/auth/login/email";
    private static final String BEARER = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final JwtService jwtService;

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager, SecretKey secretKey, JwtConfig jwtConfig, JwtService jwtService) {
        super.setFilterProcessesUrl(EMAIL_LOGIN_URL);
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.jwtService = jwtService;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthenticationRequest authenticationRequest = new ObjectMapper()
                .readValue(request.getInputStream(), AuthenticationRequest.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        String email = authResult.getName();
//        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        long userId = ((ChatflowUserDetail) authResult.getPrincipal()).getId();

        String jwtToken = jwtService.createJwt(email, userId, secretKey, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", email);
        ObjectMapper objectMapper = new ObjectMapper();
        String userInfoJson = objectMapper.writeValueAsString(userInfo);

        response.addHeader(jwtConfig.getAuthorization(), BEARER + jwtToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(userInfoJson); // TODO: add the rest user information. e.g. avatar, user setting ...
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        if (failed.getCause() instanceof InternalAuthenticationServiceException) {
            response.sendError((HttpServletResponse.SC_UNAUTHORIZED), failed.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print("Login failed, wrong password or username or not activate account");
        response.getWriter().flush();
        response.getWriter().close();
    }

}
