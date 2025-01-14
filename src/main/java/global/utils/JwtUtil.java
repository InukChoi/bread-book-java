package global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz01234567890123456789";
    private static final int EXP = 30 * 60 * 1000;
    public static int getUserIdx(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userIdx", Integer.class);
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다.");
            return 0;
        }
    }
    public static String generateToken(int userIdx, String userName) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);
        claims.put("userIdx", userIdx);

        String token = Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        return token;
    }

    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return false;
        }

        return true;
    }
}
