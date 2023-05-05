package com.tbgdn.polar.catalog.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Iterable<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
  }

  public Book add(Book book) {
    if (bookRepository.existsByIsbn(book.isbn())) {
      throw new BookAlreadyExistsException(book.isbn());
    } else {
      return bookRepository.save(book);
    }
  }

  public void remove(String isbn) {
    bookRepository.deleteByIsbn(isbn);
  }

  public Book edit(String isbn, Book book) {
    return bookRepository.findByIsbn(isbn).map(existing -> {
      var bookToUpdate = new Book(
          existing.isbn(),
          book.title(),
          book.author(),
          book.price()
      );
      return bookRepository.save(bookToUpdate);
    }).orElseGet(() -> add(book));
  }

}
