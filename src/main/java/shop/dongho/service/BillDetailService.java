package shop.dongho.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.BillDetail;

import java.util.Optional;

public interface BillDetailService {
    Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable);

    Page<BillDetail> findAll(Pageable pageable);

    Optional<BillDetail> findById(Long id);

    void save(BillDetail BillDetail);

    void remove(Long id);
}
