package dev.markpernia.librarymanagementpractice.service;

import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.mapper.BookMapper;
import dev.markpernia.librarymanagementpractice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public List<BookDTO> findAllBooks() throws Exception {

        List<BookDTO> books;

        try {
            books = bookRepository.findAll()
                    .stream()
                    .map(b -> bookMapper.toDTO(b))
                    .toList();
        } catch (Exception e) {
            throw new Exception("no books was found");
        }

        if (books.isEmpty()) {
            throw new Exception("no authors was found");
        }

        for (BookDTO bookDTO : books) {
            if (isNotValid(bookDTO)) {
                throw new Exception("found a book with null fields");
            }
        }

        return books;
    }

    public boolean isNotValid(BookDTO bookDTO) {

        if (bookDTO.getTitle() == null) {
            return true;
        }

        if (bookDTO.getIsbn() == null) {
            return true;
        }

        if (bookDTO.getAuthorName() == null) {
            return true;
        }

        if (bookDTO.getAuthorsEmail() == null) {
            return true;
        }

        return false;
    }
}
