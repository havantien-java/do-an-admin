package shop.dongho.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.ProductType;


public interface ProductTypeRepository extends PagingAndSortingRepository<ProductType, Integer> {
    Page<ProductType> findByNameContaining(String s, Pageable pageable);
}
