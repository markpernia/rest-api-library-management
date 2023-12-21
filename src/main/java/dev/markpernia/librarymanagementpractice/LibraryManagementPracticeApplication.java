package dev.markpernia.librarymanagementpractice;

import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementPracticeApplication implements CommandLineRunner {

    private AuthorRepository authorRepository;

    @Autowired
    public LibraryManagementPracticeApplication(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementPracticeApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        //sample data
        Author shakespeare = new Author();
        shakespeare.setName("William Shakespeare");
        shakespeare.setEmail("w.shakespeare@gmail.com");

        Author austen = new Author();
        austen.setName("Jane Austen");
        austen.setEmail("j.austen@gmail.com");

        Author orwell = new Author();
        orwell.setName("George Orwell");
        orwell.setEmail("g.orwelln@gmail.com");

        Author rowling = new Author();
        rowling.setName("J.K. Rowling");
        rowling.setEmail("jk.rowling@gmail.com");

        Author marquez = new Author();
        marquez.setName("Gabriel Garcia Marquez");
        marquez.setEmail("gg.marquez@gmail.com");

        authorRepository.save(shakespeare);
        authorRepository.save(austen);
        authorRepository.save(orwell);
        authorRepository.save(rowling);
        authorRepository.save(marquez);
    }
}
