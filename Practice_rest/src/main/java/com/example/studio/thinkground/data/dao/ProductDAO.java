package com.example.studio.thinkground.data.dao;

import com.example.studio.thinkground.data.entity.ProductEntity;

public interface ProductDAO {

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String productId);
}
