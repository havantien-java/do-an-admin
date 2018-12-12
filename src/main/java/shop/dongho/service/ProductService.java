package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Product;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAllByContaining( String s, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer id);

    void save(Product product);

    void remove(Integer id);


}
