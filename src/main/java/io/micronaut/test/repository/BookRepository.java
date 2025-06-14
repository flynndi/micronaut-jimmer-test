package io.micronaut.test.repository;

import io.micronaut.jimmer.repository.JRepository;
import io.micronaut.jimmer.repository.annotation.Repository;
import io.micronaut.test.entity.Book;

@Repository
public interface BookRepository extends JRepository<Book, Long> {
}
