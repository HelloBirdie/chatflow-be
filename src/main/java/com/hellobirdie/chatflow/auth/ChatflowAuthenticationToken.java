package com.hellobirdie.chatflow.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class ChatflowAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Long userId;

    public ChatflowAuthenticationToken(Long userId, Object principal, Object credential) {
        super(principal, credential);
        this.userId = userId;
    }

    @Override
    public Map<String, Long> getDetails() {
        return Map.of("UserId", userId);
    }
}
