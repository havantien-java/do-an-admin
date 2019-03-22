package shop.create;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.create.service.*;
import shop.create.service.impl.*;


@SpringBootApplication

public class CreateUserApplication {

	@Bean
	UserService userService() {
		return new UserServiceImpl();
	}



	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}




	public static void main(String[] args)
	{
		SpringApplication.run(CreateUserApplication.class, args);
	}
}
