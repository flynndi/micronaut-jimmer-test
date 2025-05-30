package io.micronaut.test.repository;

import io.micronaut.repo.support.AbstractJavaRepository;
import io.micronaut.test.entity.Book;
import jakarta.inject.Singleton;
import org.babyfish.jimmer.sql.JSqlClient;

@Singleton
public class BookJavaRepository extends AbstractJavaRepository<Book, Long> {
	public BookJavaRepository(JSqlClient sql) {
		super(sql);
	}
}
