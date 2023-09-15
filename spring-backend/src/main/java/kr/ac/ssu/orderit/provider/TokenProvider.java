package kr.ac.ssu.orderit.provider;

import io.jsonwebtoken.*;
import kr.ac.ssu.orderit.vo.JWTPayloadVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {
    /**
     * JWT secret key from environment variable
     */
    @Value("${spring.jwt.secret}")
    private String JWT_SECRET_KEY;

    /**
     * Expire time for access token
     */
    private final Long ACCESS_TOKEN_EXP = 1000L * 60 * 60 * 2;

    /**
     * Generate access token
     * @param jwtPayloadVO jwt token payload
     * @return generated access token
     * @author jonghokim27
     */
    @NotNull
    public String generateAccessToken(@NotNull JWTPayloadVo jwtPayloadVO) {
        Claims claims = Jwts.claims();
        claims.put("tableNo", jwtPayloadVO.getTableNo());
        claims.put("sessionId", jwtPayloadVO.getSessionId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration((new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP)))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    /**
     * Validates access token
     * @param token access token
     * @return jwt token payload (JWTPayloadVO)
     * @throws BadCredentialsException thrown when access token is invalid
     * @throws ExpiredJwtException thrown when access token is expired
     * @author jonghokim27
     */
    @NotNull
    public JWTPayloadVo validateAccessToken(@NotNull String token) throws BadCredentialsException, ExpiredJwtException{
        Claims claims;
        Integer tableNo;
        Integer sessionId;

        try {
            claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
            tableNo = (Integer) claims.get("tableNo");
            sessionId = (Integer) claims.get("sessionId");
        } catch (SignatureException e) {
            throw new BadCredentialsException("Invalid JWT_SECRET_KEY.", e);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid access token.", e);
        }

        return JWTPayloadVo.builder()
                .tableNo(tableNo)
                .sessionId(sessionId)
                .build();

    }


}
