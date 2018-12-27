package shop.dongho;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import shop.dongho.formatter.UserRoleFormatter;
import shop.dongho.service.*;
import shop.dongho.service.impl.*;
import shop.dongho.storage.StorageProperties;
import shop.dongho.storage.StorageService;

import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DonghoApplication {
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}

	@Bean
	UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public BillService billService() {
		return new BillServiceImpl();
	}

	@Bean
	public ProductTypeService productTypeService() {
		return new ProductTypeServiceImpl();
	}

	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}

	@Bean
	public BillDetailService billDetailService() {
		return new BillDetailServiceImpl();
	}

	@Bean
	public ProducerService producerService() {
		return new ProducerServiceImpl();
	}

	@Bean
	public UserRoleService userRoleService() {
		return new UserRoleServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Configuration
	static class MyConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addFormatter(new UserRoleFormatter());
		}
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}


	public static void main(String[] args)
	{
		SpringApplication.run(DonghoApplication.class, args);
	}
}
