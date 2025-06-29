package com.example.demo.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FileEntity;

@Repository
public interface FileRepositry extends JpaRepository<FileEntity, Long> {

}
