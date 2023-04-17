package com.example.studio.thinkground.data.handler.impl;

import com.example.studio.thinkground.data.dao.ProductDAO;
import com.example.studio.thinkground.data.entity.ProductEntity;
import com.example.studio.thinkground.data.handler.ProductDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductDataHandlerimpl implements ProductDataHandler  {

    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerimpl(ProductDAO productDAO){
        this.productDAO =productDAO;
    }

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = new ProductEntity(productId,productName,productPrice,productStock);
        return productDAO.saveProduct(productEntity);
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        return productDAO.getProduct(productId);
    }
}
