package com.tbgdn.polar.catalog.controller;

import com.tbgdn.polar.catalog.domain.Book;
import com.tbgdn.polar.catalog.domain.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public Iterable<Book> findAll() {
    return bookService.findAll();
  }

  @GetMapping("{isbn}")
  public Book findByIsbn(@PathVariable String isbn) {
    return bookService.findByIsbn(isbn);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book add(@Valid @RequestBody Book book) {
    return bookService.add(book);
  }

  @DeleteMapping("{isbn}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable String isbn) {
    bookService.remove(isbn);
  }

  @PutMapping("{isbn}")
  public Book edit(@PathVariable String isbn, @Valid @RequestBody Book book) {
    return bookService.edit(isbn, book);
  }
}
