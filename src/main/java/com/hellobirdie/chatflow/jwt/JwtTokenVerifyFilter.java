package com.hellobirdie.chatflow.jwt;

import com.hellobirdie.chatflow.auth.ChatflowAuthenticationToken;
import com.hellobirdie.chatflow.auth.ChatflowUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenVerifyFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final ChatflowUserDetailService chatflowUserDetailService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {

        String authHeader = request.getHeader(jwtConfig.getAuthorization());

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.replace(BEARER, "");

        try {

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String email = body.getSubject();
            long userId = (Integer) body.get("userId");
            UserDetails userDetails = chatflowUserDetailService.loadUserByUsername(email);

            ChatflowAuthenticationToken authentication = new ChatflowAuthenticationToken(
                    userId,
                    userDetails,
                    null,
                    null
            );


            System.out.println("UserDetails: " + userDetails.getPassword());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            response.getWriter().flush();
        }

    }

}
