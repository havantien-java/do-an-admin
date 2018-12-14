package shop.dongho.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.dongho.model.BillDetail;

public interface BillDetailRepository extends PagingAndSortingRepository<BillDetail, Long> {
    Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable);
}
