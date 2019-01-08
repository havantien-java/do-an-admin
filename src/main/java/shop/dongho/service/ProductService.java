package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Product;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAllByNameContaining( String s, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer id);

    void save(Product product);

    void remove(Integer id);

    Page<Product> findAllByProducer_Id(Integer id, Pageable pageable);

    Page<Product> findAllByUnitPriceLessThan(Integer number, Pageable pageable);

    Page<Product> findAllByUnitPriceBetween(Integer pri1, Integer pri2, Pageable pageable);

    Page<Product> findAllByProductType_Id(Integer id, Pageable pageable);
}
