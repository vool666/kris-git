package ee.bcs.valiit.tasks.solution.controller;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @GetMapping("public/login")
    public String login() {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60);
        JwtBuilder builder = Jwts.builder()
                .setExpiration(expiration)
                .setIssuedAt(new Date())
                .setIssuer("vali-it")
                .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                .claim("username", "admin");
        return builder.compact();
    }
}
