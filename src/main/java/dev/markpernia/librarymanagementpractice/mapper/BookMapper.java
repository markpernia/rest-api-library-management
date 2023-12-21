package dev.markpernia.librarymanagementpractice.mapper;

import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.entity.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookMapper {

    private ModelMapper modelMapper;

    @Autowired
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
        TypeMap<Book, BookDTO> typeMap = modelMapper.createTypeMap(Book.class, BookDTO.class);
        typeMap.addMapping(b -> b.getAuthor().getName(), BookDTO::setAuthorName);
        typeMap.addMapping(b -> b.getAuthor().getEmail(), BookDTO::setAuthorsEmail);
    }

    public BookDTO toDTO(Book book) {
        return Objects.isNull(book) ? null : modelMapper.map(book, BookDTO.class);
    }
}
