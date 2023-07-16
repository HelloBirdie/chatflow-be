package com.hellobirdie.chatflow.auth;

import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.exception.ResourceNotFoundException;
import com.hellobirdie.chatflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/* Use: Tell spring security how to authenticate the user*/
@Service
@RequiredArgsConstructor
@Slf4j
public class ChatflowUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User {} not found", email);
                    return new ResourceNotFoundException("User", email);
                });


        return ChatflowUserDetail.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }
}
