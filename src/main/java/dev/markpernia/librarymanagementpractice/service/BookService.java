package dev.markpernia.librarymanagementpractice.service;

import dev.markpernia.librarymanagementpractice.dto.AuthorDTO;
import dev.markpernia.librarymanagementpractice.dto.BookDTO;
import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.entity.Book;
import dev.markpernia.librarymanagementpractice.mapper.BookMapper;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import dev.markpernia.librarymanagementpractice.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
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


    public BookDTO findBookById(Long id) throws Exception {

        Optional<Book> book = bookRepository.findBookById(id);
        if (book.isEmpty()) {
            throw new Exception("no book was found");
        }

        BookDTO bookDTO = bookMapper.toDTO(book.get());
        if (bookDTO == null) {
            throw new Exception("no book was found");
        }

        if (isNotValid(bookDTO)) {
            throw new Exception("found a book with null fields");
        }

        return bookDTO;
    }

    @Transactional
    public void add(BookDTO bookDTO) throws Exception {

        Optional<Author> author = authorRepository.findAuthorById(bookDTO.getAuthorId());
        if (author.isEmpty()) {
            throw new Exception("no author was found");
        }

        Book book = bookMapper.toEntity(bookDTO);
        if (book == null) {
            throw new Exception("no data was found");
        }

        book.setAuthor(author.get());
        bookRepository.save(book);
    }

    public void update(Long bookId, BookDTO bookDTO) throws Exception {

        Optional<Book> book = bookRepository.findBookById(bookId);
        if (book.isEmpty()) {
            throw new Exception("no book was found");
        }

        bookMapper.updateEntity(bookDTO, book.get());
        bookRepository.save(book.get());
    }


    public boolean isNotValid(BookDTO bookDTO) {

        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            return true;
        }

        if (bookDTO.getIsbn() == null || bookDTO.getIsbn().trim().isEmpty()) {
            return true;
        }

        return false;
    }
}
