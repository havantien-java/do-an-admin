package shop.dongho.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.Bill;


public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {

    Page<Bill> findByDateOrderContaining(String s, Pageable pageable);
}
