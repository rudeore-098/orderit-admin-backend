/**
 * @filename    : JWTAuthFilter.java
 * @description : Custom auth filter for JSON Web Tokens
 * @author      : jonghokim27
 */

package kr.ac.ssu.orderit.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.ssu.orderit.util.HTTPRequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthKeyFilter extends OncePerRequestFilter {
    /**
     * DI
     */
    private final HTTPRequestUtil httpRequestUtil;
    @Value("${spring.auth.key}")
    private String authKey;

    /**
     * Do filter
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> authKey = httpRequestUtil.getAuthKey(request);
            if (authKey.isEmpty()) {
                throw new Exception("No auth key provided.");
            }

            if (!authKey.get().equals(this.authKey)) {
                throw new Exception("Auth key does not match.");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken("Admin", "", List.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request, response);
    }

}
