package shop.dongho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shop.dongho.service.ProductService;
import shop.dongho.service.impl.ProductServiceImpl;

@SpringBootApplication
public class DonghoApplication {
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(DonghoApplication.class, args);
	}
}
