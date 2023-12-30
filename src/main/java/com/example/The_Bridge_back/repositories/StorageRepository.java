package com.example.The_Bridge_back.repositories;

import com.example.The_Bridge_back.entities.imageModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<imageModel, Long> {
}
