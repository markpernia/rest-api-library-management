package dev.markpernia.librarymanagementpractice.repository;

import dev.markpernia.librarymanagementpractice.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
