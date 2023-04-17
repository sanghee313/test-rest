package com.example.studio.thinkground.service;

import com.example.studio.thinkground.data.dto.ProductDTO;

public interface ProductService {

    ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);

    ProductDTO getProduct(String productId);
}
