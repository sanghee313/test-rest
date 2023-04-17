package com.example.studio.thinkground.data.handler;

import com.example.studio.thinkground.data.entity.ProductEntity;

public interface ProductDataHandler {

    ProductEntity saveProductEntity(String productId,String productName,int productPrice,int productStock);

    ProductEntity getProductEntity(String productId);

}
