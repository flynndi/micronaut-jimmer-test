package io.micronaut.test.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.test.entity.Book;
import io.micronaut.test.entity.BookFetcher;
import io.micronaut.test.entity.Tables;
import io.micronaut.transaction.annotation.Transactional;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.babyfish.jimmer.sql.JSqlClient;

import java.util.List;

@Controller("/bookResource")
@Api
@EnableImplicitApi
public class BookController {

	private final JSqlClient sqlClient;

	public BookController(JSqlClient sqlClient) {
		this.sqlClient = sqlClient;
	}

	@Get("/all")
	@Api
	public HttpResponse<List<Book>> getAllBooks() {
		return HttpResponse.ok(sqlClient.createQuery(Tables.BOOK_TABLE).select(Tables.BOOK_TABLE.fetch(BookFetcher.$.allTableFields()))
				.execute());
	}

	@Post("/save")
	@Transactional(rollbackFor = Exception.class)
	@Api
	public HttpResponse<Book> saveBook(@Body Book book) {
		return HttpResponse.ok(sqlClient.saveCommand(book).execute().getModifiedEntity());
	}
}
