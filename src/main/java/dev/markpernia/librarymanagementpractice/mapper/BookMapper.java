package dev.markpernia.librarymanagementpractice.mapper;

import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookMapper {

    private ModelMapper modelMapper;

    @Autowired
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDTO toDTO(Book book) {
        modelMapper.typeMap(Book.class, BookDTO.class)
                .addMapping(b -> b.getAuthor().getName(), BookDTO::setAuthorName);

        modelMapper.typeMap(Book.class, BookDTO.class)
                .addMapping(b -> b.getAuthor().getEmail(), BookDTO::setAuthorsEmail);

        return Objects.isNull(book) ? null : modelMapper.map(book, BookDTO.class);
    }

    public Book toEntity(BookDTO bookDTO) {
        return Objects.isNull(bookDTO) ? null : modelMapper.map(bookDTO, Book.class);
    }

    public void updateEntity(BookDTO bookDTO, Book book) {
        modelMapper.typeMap(BookDTO.class, Book.class)
                .addMappings(b -> b.skip(Book::setId));
        modelMapper.map(bookDTO, book);
    }

}
