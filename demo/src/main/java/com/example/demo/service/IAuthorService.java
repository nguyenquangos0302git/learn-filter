package com.example.demo.service;

import com.example.demo.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IAuthorService {

    Page<Author> findAll(Specification<Author> specification, Pageable pageable);

}
