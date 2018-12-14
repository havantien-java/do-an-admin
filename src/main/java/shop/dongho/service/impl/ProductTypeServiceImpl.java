package shop.dongho.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.dongho.model.ProductType;
import shop.dongho.repository.ProductTypeRepository;
import shop.dongho.service.ProductTypeService;

import java.util.Optional;

public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;


    @Override
    public Page<ProductType> findByNameContaining(String s, Pageable pageable) {
        return productTypeRepository.findByNameContaining(s, pageable);
    }

    @Override
    public Page<ProductType> findAll(Pageable pageable) {
        return productTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductType> findById(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void save(ProductType productType) {
        productTypeRepository.save(productType);

    }

    @Override
    public void remove(Long id) {
        productTypeRepository.deleteById(id);

    }
}
