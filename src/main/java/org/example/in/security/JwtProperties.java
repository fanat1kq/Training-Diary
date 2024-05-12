package org.example.in.security;

import lombok.Data;
import org.example.util.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * POJO for jwt properties
 */
@Component
@Data
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access}")
    private Long access;
    @Value("${jwt.refresh}")
    private Long refresh;
}
