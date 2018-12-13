package shop.dongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shop.dongho.service.ProductService;
import shop.dongho.service.UserService;
import shop.dongho.service.impl.ProductServiceImpl;
import shop.dongho.service.impl.UserServiceImpl;

@SpringBootApplication
public class DonghoApplication {
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}

	@Bean
	UserService userService() {
		return new UserServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(DonghoApplication.class, args);
	}
}
