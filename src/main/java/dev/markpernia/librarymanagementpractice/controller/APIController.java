package dev.markpernia.librarymanagementpractice.controller;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.dto.ErrorDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.service.AuthorService;
import dev.markpernia.librarymanagementpractice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class APIController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public APIController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authors")
    public ResponseEntity<?> listOfAuthors() {

        try {
            List<AuthorDTO> authors = authorService.findAllAuthors();
            return ResponseEntity.ok().body(authors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }

    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {

        try {
            AuthorDTO author = authorService.findAuthorById(id);
            return ResponseEntity.ok().body(author);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }

    }

    @PostMapping("/authors")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDTO authorDTO) {

        if (authorService.isNotValid(authorDTO)) {
            return ResponseEntity.badRequest().body(new ErrorDTO("invalid data provided"));
        }

        try {
            authorService.addAuthor(authorDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/")).body(authorDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<?> listOfBooks() {

        try {
            List<BookDTO> books = bookService.findAllBooks();
            return ResponseEntity.ok().body(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {

        try {
            BookDTO book = bookService.findBookById(id);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {

        if (bookService.isNotValid(bookDTO)) {
            return ResponseEntity.badRequest().body(new ErrorDTO("invalid data provided"));
        }

        try {
            bookService.add(bookDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/")).body(bookDTO);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId,
                                        @RequestBody BookDTO bookDTO) {

        if (bookService.isNotValid(bookDTO)) {
            return ResponseEntity.badRequest().body(new ErrorDTO("invalid data provided"));
        }

        try {
            bookService.update(bookId, bookDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/")).body(bookDTO);
    }
}
