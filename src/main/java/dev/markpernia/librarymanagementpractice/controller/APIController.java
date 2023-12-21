package dev.markpernia.librarymanagementpractice.controller;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.dto.ErrorDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private AuthorService authorService;

    @Autowired
    public APIController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<?> listOfAuthors() {

        try {
            List<AuthorDTO> authors = authorService.findAllAuthors();
            return ResponseEntity.ok().body(authors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("No data was found"));
        }

    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {

        try {
            AuthorDTO author = authorService.findAuthorById(id);
            return ResponseEntity.ok().body(author);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("No data was found"));
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
            return ResponseEntity.badRequest().body(new ErrorDTO("invalid data provided"));
        }
        return ResponseEntity.created(URI.create("/")).body(authorDTO);
    }
}
