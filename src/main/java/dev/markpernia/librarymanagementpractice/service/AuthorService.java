package dev.markpernia.librarymanagementpractice.service;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.mapper.AuthorMapper;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> findAllAuthors() throws Exception {

        List<AuthorDTO> authors;

        try {
            authors = authorRepository.findAll()
                    .stream()
                    .map(a -> authorMapper.toDTO(a))
                    .toList();
        } catch (Exception e) {
            throw new Exception("no authors was found");
        }

        if (authors.isEmpty()) {
            throw new Exception("no authors was found");
        }

        for (AuthorDTO authorDTO : authors) {
            if (isNotValid(authorDTO)) {
                throw new Exception("found an author with null fields");
            }
        }

        return authors;

    }

    public AuthorDTO findAuthorById(Long id) throws Exception {

        Optional<Author> author = authorRepository.findAuthorById(id);
        if (author.isEmpty()) {
            throw new Exception("no author was found");
        }

        AuthorDTO authorDTO = authorMapper.toDTO(author.get());
        if (authorDTO == null) {
            throw new Exception("no author was found");
        }

        return authorDTO;

    }

    public void addAuthor(AuthorDTO authorDTO) throws Exception {

        Author author = authorMapper.toEntity(authorDTO);

        if (author == null) {
            throw new Exception("failed to add author, author is null");
        }

        authorRepository.save(author);
    }

    public boolean isNotValid(AuthorDTO authorDTO) {

        if (authorDTO.getName() == null || authorDTO.getName().trim().isEmpty()) {
            return true;
        }

        if (authorDTO.getEmail() == null || authorDTO.getEmail().trim().isEmpty()) {
            return true;
        }

        if (emailIsInvalid(authorDTO.getEmail())) {
            return true;
        }

        return false;
    }

    private boolean emailIsInvalid(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return !Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

}
