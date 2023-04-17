package com.example.studio.thinkground.data.repository;

import com.example.studio.thinkground.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
