package dev.markpernia.librarymanagementpractice.mapper;

import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.entity.Book;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
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
        configureToDTO();
    }

    private void configureToDTO() {
        TypeMap<Book, BookDTO> typeMap = modelMapper.createTypeMap(Book.class, BookDTO.class);
        typeMap.addMapping(b -> b.getAuthor().getName(), BookDTO::setAuthorName);
        typeMap.addMapping(b -> b.getAuthor().getEmail(), BookDTO::setAuthorsEmail);
    }

    public BookDTO toDTO(Book book) {
        return Objects.isNull(book) ? null : modelMapper.map(book, BookDTO.class);
    }

    public Book toEntity(BookDTO bookDTO) {

        if (bookDTO == null) {
            return null;
        }

        Book book = modelMapper.map(bookDTO, Book.class);
        book.setAuthor(null);
        book.setId(null);

        return book;

    }

}
