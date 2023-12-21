package dev.markpernia.librarymanagementpractice.controller;

import dev.markpernia.librarymanagementpractice.entity.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("/authors")
    public ResponseEntity<?> listOfAuthors() {

        try {
            //todo return ResponseEntity.ok().body(List<AuthorDTO>)
        } catch (Exception e) {
            //todo return ResponseEntity.badRequest().body(errorDTO)
        }
    }
}
