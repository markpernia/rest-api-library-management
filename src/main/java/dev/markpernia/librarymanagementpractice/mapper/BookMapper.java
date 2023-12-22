package dev.markpernia.librarymanagementpractice.mapper;

import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.entity.Book;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

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
