package com.example.jwtserving;

//import com.example.jwtserving.util.UserUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtServingApplication {

    public static void main(String[] args) {

        SpringApplication.run(JwtServingApplication.class, args);

        // Ленивое создание пользователей системы
        //ConfigurableApplicationContext context = SpringApplication.run(JwtServingApplication.class, args);
        //UserUtil userUtil = context.getBean(UserUtil.class);
        //userUtil.createUsers();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
