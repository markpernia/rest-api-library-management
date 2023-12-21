package dev.markpernia.librarymanagementpractice.service;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.mapper.AuthorMapper;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        try {
            return authorRepository.findAll()
                    .stream()
                    .map(a -> authorMapper.toDTO(a))
                    .toList();
        } catch (Exception e) {
            throw new Exception("no authors was found");
        }

    }

    public AuthorDTO findAuthorById(Long id) throws Exception {

        Optional<Author> author = authorRepository.findAuthorById(id);
        if (author.isEmpty()) {
            throw new Exception("no author was found");
        }

        return authorMapper.toDTO(author.get());

    }
}
