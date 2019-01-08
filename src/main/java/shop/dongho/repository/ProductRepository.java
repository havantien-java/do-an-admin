package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);

    Page<Product> findAllByProducer_Id(Integer id,Pageable pageable);

    Page<Product> findAllByUnitPriceLessThan(Integer number, Pageable pageable);

    Page<Product> findAllByUnitPriceBetween(Integer pri3,Integer pri5,Pageable pageable);

    Page<Product> findAllByProductType_Id(Integer id, Pageable pageable);

}
