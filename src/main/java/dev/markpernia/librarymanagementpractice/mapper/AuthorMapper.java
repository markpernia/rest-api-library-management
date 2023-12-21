package dev.markpernia.librarymanagementpractice.mapper;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorMapper {

    private ModelMapper modelMapper;

    @Autowired
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorDTO toDTO(Author author) {
        return Objects.isNull(author) ? null : modelMapper.map(author, AuthorDTO.class);
    }

    public Author toEntity(AuthorDTO authorDTO) {
        return Objects.isNull(authorDTO) ? null : modelMapper.map(authorDTO, Author.class);
    }
}
