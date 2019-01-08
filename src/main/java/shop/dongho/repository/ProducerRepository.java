package shop.dongho.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Producer;

public interface ProducerRepository extends PagingAndSortingRepository<Producer, Integer> {
    Page<Producer> findByNameContaining(String s, Pageable pageable);
}
