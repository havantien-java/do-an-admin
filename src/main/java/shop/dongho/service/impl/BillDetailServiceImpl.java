package shop.dongho.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.BillDetail;
import shop.dongho.repository.BillDetailRepository;
import shop.dongho.service.BillDetailService;

import java.util.Optional;

public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BillDetailRepository BillDetailRepository;

    @Override
    public Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable) {
        return BillDetailRepository.findByUnitPriceContaining(s, pageable);
    }

    @Override
    public Page<BillDetail> findAll(Pageable pageable) {
        return BillDetailRepository.findAll(pageable);
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return BillDetailRepository.findById(id);
    }

    @Override
    public void save(BillDetail BillDetail) {
        BillDetailRepository.save(BillDetail);

    }

    @Override
    public void remove(Long id) {
        BillDetailRepository.deleteById(id);

    }
}
