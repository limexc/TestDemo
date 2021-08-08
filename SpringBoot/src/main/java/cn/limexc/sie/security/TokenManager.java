package cn.limexc.sie.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * token管理
 * token操作工具类
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
@Component
public class TokenManager {

    private long tokenExpiration = 24*60*60*1000;
    private String tokenSignKey = "zhiyuan";

    public String createToken(String userAlias) {
        String token = Jwts.builder().setSubject(userAlias)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public String getUserFromToken(String token) {
        String userAlias;
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
            log.info(claims.toString());
            userAlias = claims.getSubject();
            //claims.getAudience();
            //userAlias = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getAudience();
        }
        catch (Exception e) {
            System.out.println("validate is token error");
            return null;
        }
        //String userAlias = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return userAlias;
    }

    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

}
