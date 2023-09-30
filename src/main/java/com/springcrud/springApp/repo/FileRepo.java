package com.springcrud.springApp.repo;

import com.springcrud.springApp.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface FileRepo extends JpaRepository<FileEntity, Integer> {
}
