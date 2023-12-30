package com.example.The_Bridge_back.repositories;

import com.example.The_Bridge_back.entities.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<product, Long> {
}
