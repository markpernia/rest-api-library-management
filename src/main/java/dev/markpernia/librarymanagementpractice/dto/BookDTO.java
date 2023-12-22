package dev.markpernia.librarymanagementpractice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private String title;
    private String isbn;
    private String authorName;
    private String authorsEmail;
    private Long authorId;
}
