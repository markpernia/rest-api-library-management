package dev.markpernia.librarymanagementpractice.repository;

import dev.markpernia.librarymanagementpractice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
