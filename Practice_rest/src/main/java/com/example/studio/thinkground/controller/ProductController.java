package com.example.studio.thinkground.controller;

import com.example.studio.thinkground.common.Constants;
import com.example.studio.thinkground.common.exception.AroundHubException;
import com.example.studio.thinkground.data.dto.ProductDTO;
import com.example.studio.thinkground.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping(value = "/product/{productId}") //CRUD - R
    public ProductDTO getProduct(@PathVariable String productId){

        long startTime =System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of Around Hub API.","getPtoduct");
        ProductDTO productDTO = productService.getProduct(productId);
        LOGGER.info("[ProductController] Response :: productId ={}, productPrice ={} ,productStock ={},Response Time ={}ms" ,
                productDTO.getProductId(),productDTO.getProductPrice(),productDTO.getProductStock(),(System.currentTimeMillis()-startTime));

        return productDTO;
    }

    // http://localhost:8080/api/v1/product-api/product
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){  //CRUD - C
        String productId = productDTO.getProductId();
        String productName = productDTO.getProductName();
        int productPrice = productDTO.getProductPrice();
        int productStock = productDTO.getProductStock();

        ProductDTO response = productService
                .saveProduct(productId,productName,productPrice,productStock);

        LOGGER.info(
                "[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getProductId(), response.getProductName(), response.getProductPrice(),
                response.getProductStock());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }
    // http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId){
        return null;
    }

    @PostMapping(value = "/product/exception")
    public  void exceptionTest() throws AroundHubException{
        throw  new AroundHubException(Constants.ExceptionClass.PRODUCT,HttpStatus.BAD_REQUEST,"의도한 에러가 발생하였습니다.");

    }
}
