package com.example.API;

import com.example.API.Model.Role;
import com.example.API.Model.User;
import com.example.API.Repo.UserRepo;
import com.example.API.Services.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);
	}
//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//
//		return String.format("Hello %s!" + serviceUser.findAll());
//	}
//    @GetMapping("/greet")
//    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
//	@Bean
//	CommandLineRunner runner(UserRepo serviceUser){
//		return args -> {
//			User user2 = new User("Anmol","Shah","Anmolnshah@gmail.com", Role.ADMIN,"123456",9426);
//			serviceUser.insert(user2);
//		};
//	}

}
