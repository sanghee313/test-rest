package com.example.studio.thinkground.data.dao.impl;

import com.example.studio.thinkground.data.dao.ProductDAO;
import com.example.studio.thinkground.data.entity.ProductEntity;
import com.example.studio.thinkground.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOimpl implements ProductDAO {

    ProductRepository productRepository;

    @Autowired
    public ProductDAOimpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity){
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId){
        ProductEntity productEntity = productRepository.getReferenceById(productId);

        return productEntity;
    }
}
