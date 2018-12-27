package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);

    Page<Product> findAllByProducer_Id(Long id,Pageable pageable);

    Page<Product> findAllByUnitPriceLessThan(Integer number, Pageable pageable);

    Page<Product> findAllByUnitPriceBetween(Integer pri1,Integer pri2,Pageable pageable);
}
