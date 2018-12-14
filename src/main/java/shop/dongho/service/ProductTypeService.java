package shop.dongho.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.ProductType;

import java.util.Optional;

public interface ProductTypeService {
    Page<ProductType> findByNameContaining(String name, Pageable pageable);

    Page<ProductType> findAll(Pageable pageable);

    Optional<ProductType> findById(Long id);

    void save(ProductType productType);

    void remove(Long id);
}
