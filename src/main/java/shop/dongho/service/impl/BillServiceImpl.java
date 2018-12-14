package shop.dongho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Bill;
import shop.dongho.repository.BillRepository;
import shop.dongho.service.BillService;

import java.util.Optional;

public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;


    @Override
    public Page<Bill> findByDateOrderContaining(String s, Pageable pageable) {
        return billRepository.findByDateOrderContaining(s, pageable);
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.deleteById(id);

    }
}
