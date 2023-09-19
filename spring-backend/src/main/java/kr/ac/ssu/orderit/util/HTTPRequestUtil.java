package kr.ac.ssu.orderit.util;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HTTPRequestUtil {
    /**
     * Get auth key from HTTP Request
     * @param request HttpServletRequest
     * @return auth key (optional)
     * @author jonghokim27
     */
    @NotNull
    public Optional<String> getAuthKey(@NotNull HttpServletRequest request) {
        final String HEADER_KEY = "Auth-Key";

        return Optional.ofNullable(request.getHeader(HEADER_KEY));
    }
}
