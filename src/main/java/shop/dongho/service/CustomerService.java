package shop.dongho.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Page<Customer> findAllByNameContaining(String s, Pageable pageable);
}
