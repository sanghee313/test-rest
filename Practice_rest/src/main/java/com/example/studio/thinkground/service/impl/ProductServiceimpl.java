package com.example.studio.thinkground.service.impl;

import com.example.studio.thinkground.data.dto.ProductDTO;
import com.example.studio.thinkground.data.entity.ProductEntity;
import com.example.studio.thinkground.data.handler.ProductDataHandler;
import com.example.studio.thinkground.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceimpl implements ProductService {
    ProductDataHandler productDataHandler;


    @Autowired
    public ProductServiceimpl(ProductDataHandler productDataHandler){
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId,productName,productPrice,productStock);

        ProductDTO productDTO = new ProductDTO(productEntity.getProductId(),productEntity.getProductName(),productEntity.getProductPrice(),productEntity.getProductStock());
        return productDTO;
    }

    @Override
    public ProductDTO getProduct(String productId) {
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);
        ProductDTO productDTO = new ProductDTO(productEntity.getProductId(),productEntity.getProductName(),productEntity.getProductPrice(),productEntity.getProductPrice());

        return productDTO;
    }
}
