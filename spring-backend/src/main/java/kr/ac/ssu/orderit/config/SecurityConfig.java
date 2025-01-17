/**
 * @filename    : SecurityConfig.java
 * @description : Config for Spring Security
 * @author      : jonghokim27
 */

package kr.ac.ssu.orderit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    /**
     * DI
     */
    private final AuthKeyFilter authKeyFilter;
    private final StatusCode statusCode;

    /**
     * Security filter chain config
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.sessionManagement(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().authenticated()
        );
        http.exceptionHandling((exceptionHandling) -> exceptionHandling
                .authenticationEntryPoint(getUnauthorizedEntryPoint())
                .accessDeniedHandler(getAccessDeniedHandler())
        );
        http.addFilterBefore(authKeyFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "POST"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private AccessDeniedHandler accessDeniedHandler = null;

    /**
     * Access Denied Handler (Lazy)
     */
    private AccessDeniedHandler getAccessDeniedHandler() {
        if (accessDeniedHandler == null) {
            return accessDeniedHandler = (request, response, accessDeniedException) -> {
                log.debug(accessDeniedException.getMessage(), accessDeniedException);

                response.setStatus(400);
                response.setContentType("application/json");
                CommonResponse res = new CommonResponse(statusCode.SSU4001, null, statusCode.SSU4001_MSG);
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(res));
            };
        }
        return accessDeniedHandler;
    }

    private AuthenticationEntryPoint authenticationEntryPoint = null;

    /**
     * Unauthorized entry point (Lazy)
     */
    private AuthenticationEntryPoint getUnauthorizedEntryPoint() {
        if (authenticationEntryPoint == null) {
            return authenticationEntryPoint = (request, response, authException) -> {
                log.debug(authException.getMessage(), authException);

                response.setStatus(400);
                response.setContentType("application/json");
                CommonResponse res = new CommonResponse(statusCode.SSU4001, null, statusCode.SSU4001_MSG);
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(res));
            };
        }
        return authenticationEntryPoint;
    }

}