package com.example.demo.service.spec;

import com.example.demo.entity.Author;
import com.example.demo.entity.Author_;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AuthorSpecs {

    public static Specification<Author> nameLike(String keyword) {
        return (root, query, cb) -> cb.like(root.get(Author_.NAME), "%" + keyword + "%");
    }

    public static Specification<Author> nationalityIn(List<String> nationalities) {
        return (root, query, cb) -> root.get(Author_.NATIONALITY).in(nationalities);
    }

    public static Specification<Author> nationalityInV1(List<String> nationalities) {
        return (root, query, cb) -> cb.in(root.get(Author_.NATIONALITY)).value(nationalities);
    }

    public static Specification<Author> lessThanOrEqualTo(Integer birthYear) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Author_.BIRTH_YEAR), birthYear);
    }

    public static Specification<Author> lessThan(Integer birthYear) {
        return (root, query, cb) -> cb.lessThan(root.get(Author_.BIRTH_YEAR), birthYear);
    }

    public static Specification<Author> greaterThanOrEqualTo(Integer birthYear) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Author_.BIRTH_YEAR), birthYear);
    }

    public static Specification<Author> greaterThan(Integer birthYear) {
        return (root, query, cb) -> cb.greaterThan(root.get(Author_.BIRTH_YEAR), birthYear);
    }

    public static Specification<Author> between(Integer birthYearFirst, Integer birthYearSecond) {
        return (root, query, cb) -> cb.between(root.get(Author_.BIRTH_YEAR), birthYearFirst, birthYearSecond);
    }

    public static Specification<Author> customBirthYear(Integer birthYearFirst, Integer birthYearSecond, Integer birthYearThird, Integer birthYearFourth) {
        Specification<Author> specificationFirst = (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Author_.BIRTH_YEAR), birthYearFirst, birthYearSecond);
        Specification<Author> specificationSecond = (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Author_.BIRTH_YEAR), birthYearThird, birthYearFourth);
        return specificationFirst.or(specificationSecond);
    }

    public static Specification<Author> customBirthYearV1(Integer birthYearFirst, Integer birthYearSecond, Integer birthYearThird, Integer birthYearFourth) {
        Specification<Author> specification = (root, query, cb) -> cb.disjunction();
        specification = specification.or((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Author_.BIRTH_YEAR), birthYearFirst, birthYearSecond));
        specification = specification.or((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Author_.BIRTH_YEAR), birthYearThird, birthYearFourth));
        return specification;
    }

    public static Specification<Author> customBirthYearV2(Integer birthYearFirst, Integer birthYearSecond, Integer birthYearThird, Integer birthYearFourth) {
        return (root, query, cb) -> cb.or(
                cb.between(root.get(Author_.BIRTH_YEAR), birthYearFirst, birthYearSecond),
                cb.between(root.get(Author_.BIRTH_YEAR), birthYearThird, birthYearFourth)
        );
    }

}
