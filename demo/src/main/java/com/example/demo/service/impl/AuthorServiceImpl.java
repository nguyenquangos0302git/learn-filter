package com.example.demo.service.impl;

import com.example.demo.entity.Author;
import com.example.demo.repository.IAuthorRepository;
import com.example.demo.service.IAuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository iAuthorRepository;

    public AuthorServiceImpl(IAuthorRepository iAuthorRepository) {
        this.iAuthorRepository = iAuthorRepository;
    }


    @Override
    public Page<Author> findAll(Specification<Author> specification, Pageable pageable) {
        return iAuthorRepository.findAll(specification, pageable);
    }
}
