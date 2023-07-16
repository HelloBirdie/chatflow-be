package com.hellobirdie.chatflow.config;

import com.hellobirdie.chatflow.auth.ChatflowUserDetailService;
import com.hellobirdie.chatflow.jwt.JwtService;
import com.hellobirdie.chatflow.jwt.JwtConfig;
import com.hellobirdie.chatflow.jwt.JwtUsernameAndPasswordAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "cors")
public class SecurityConfig {

    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;

    private final ChatflowUserDetailService chatflowUserDetailService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final JwtTokenVerifyFilter jwtTokenVerifyFilter;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll() // Allow all requests
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable() // Disable CSRF protection for simplicity during development, not recommended for production
//                .oauth2Login()  // Enable OAuth2 login
//                .defaultSuccessURL("/loginSuccess") // Redirect to /loginSuccess URL after successful login
//                .failureURL("/loginFailure"); // Redirect to /loginFailure URL after login failure

        return http
                .csrf().disable()
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(allowedOrigins);
                    cors.setAllowedMethods(allowedMethods);
                    cors.setAllowedHeaders(allowedHeaders);
                    cors.setExposedHeaders(exposedHeaders);
                    return cors;
                }).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(), secretKey, jwtConfig, jwtService))
                .addFilterAfter(jwtTokenVerifyFilter, JwtUsernameAndPasswordAuthFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(new ChatflowAccessDeniedHandler())
                .authenticationEntryPoint(new AuthEntryPoint())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*To tell the JwtUsernameAndPasswordAuthFilter how to authenticate username and password*/
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(chatflowUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }
}
