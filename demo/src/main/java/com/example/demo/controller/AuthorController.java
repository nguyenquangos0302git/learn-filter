package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.service.IAuthorService;
import com.example.demo.service.spec.AuthorSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final IAuthorService iAuthorService;

    public AuthorController(IAuthorService iAuthorService) {
        this.iAuthorService = iAuthorService;
    }

    @GetMapping()
    public Page<Author> findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Specification<Author> specificationFirst = AuthorSpecs.nameLike("Murakami");
        Specification<Author> specificationSecond = AuthorSpecs.nationalityIn(Arrays.asList("Japanese", "British"));
        Specification<Author> specificationSecondV1 = AuthorSpecs.nationalityInV1(Arrays.asList("Japanese", "British"));
        Specification<Author> specificationThird = AuthorSpecs.lessThan(1949);
        Specification<Author> specificationFourth = AuthorSpecs.lessThanOrEqualTo(1949);
        Specification<Author> specificationFifth = AuthorSpecs.greaterThan(1949);
        Specification<Author> specificationSixth = AuthorSpecs.greaterThanOrEqualTo(1949);
        Specification<Author> specificationSeventh = AuthorSpecs.between(1903, 1965);
        Specification<Author> specificationEighth = AuthorSpecs.customBirthYear(1904, 1950, 1951, 1965);
        Specification<Author> specificationEighthV1 = AuthorSpecs.customBirthYearV1(1904, 1950, 1951, 1965);
        Specification<Author> specificationEighthV2 = AuthorSpecs.customBirthYearV2(1904, 1950, 1951, 1965);

        iAuthorService.findAll(specificationFirst, pageable);
        iAuthorService.findAll(specificationSecond, pageable);
        iAuthorService.findAll(specificationSecondV1, pageable);
        iAuthorService.findAll(specificationThird, pageable);
        iAuthorService.findAll(specificationFourth, pageable);
        iAuthorService.findAll(specificationFifth, pageable);
        iAuthorService.findAll(specificationSixth, pageable);
        iAuthorService.findAll(specificationSeventh, pageable);
        iAuthorService.findAll(specificationEighth, pageable);
        iAuthorService.findAll(specificationEighthV1, pageable);
        iAuthorService.findAll(specificationEighthV2, pageable);

        return iAuthorService.findAll(specificationFirst, pageable);
    }

    @GetMapping("/v1")
    public Page<Author> findAllV1() {
        Pageable pageable = PageRequest.of(0, 10);

        Specification<Author> customSpec = Specification.where(null);

        if (true) {
            Specification<Author> specificationFirst = AuthorSpecs.nameLike("Murakami");
            customSpec = customSpec.and(specificationFirst);
        }

        if (true) {
            Specification<Author> specificationSecond = AuthorSpecs.nationalityIn(Arrays.asList("Japanese", "British"));
            customSpec = customSpec.and(specificationSecond);
        }

        return iAuthorService.findAll(customSpec, pageable);
    }

}
