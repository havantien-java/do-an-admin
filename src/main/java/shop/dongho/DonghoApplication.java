package shop.dongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shop.dongho.service.*;
import shop.dongho.service.impl.*;

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

	public static void main(String[] args) {
		SpringApplication.run(DonghoApplication.class, args);
	}
}
