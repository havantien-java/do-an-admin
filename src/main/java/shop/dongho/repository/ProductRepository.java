package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);
}
