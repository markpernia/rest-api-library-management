package dev.markpernia.librarymanagementpractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private String title;
    private String isbn;
    private String authorName;
    private String authorsEmail;
}
