package dev.markpernia.librarymanagementpractice;

import dev.markpernia.librarymanagementpractice.entity.Author;
import dev.markpernia.librarymanagementpractice.entity.Book;
import dev.markpernia.librarymanagementpractice.repository.AuthorRepository;
import dev.markpernia.librarymanagementpractice.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementPracticeApplication implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public LibraryManagementPracticeApplication(AuthorRepository authorRepository,
                                                BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        Book hamlet = new Book();
        hamlet.setTitle("Hamlet");
        hamlet.setIsbn("978-0743477123");
        hamlet.setAuthor(shakespeare);

        Book sevenNovels = new Book();
        sevenNovels.setTitle("Seven Novels");
        sevenNovels.setIsbn("9781435167964");
        sevenNovels.setAuthor(austen);

        Book nineteenEightyFour = new Book();
        nineteenEightyFour.setTitle("1984");
        nineteenEightyFour.setIsbn("9780141036144");
        nineteenEightyFour.setAuthor(orwell);

        Book harryPotter = new Book();
        harryPotter.setTitle("Harry Potter and the Philosopher's Stone");
        harryPotter.setIsbn("978-1408855652");
        harryPotter.setAuthor(rowling);

        Book harryPotterChamber = new Book();
        harryPotterChamber.setTitle("Harry Potter and the Chamber of Secrets");
        harryPotterChamber.setIsbn("978-1408855669");
        harryPotterChamber.setAuthor(rowling);

        Book harryPotterPrisoner = new Book();
        harryPotterPrisoner.setTitle("Harry Potter and the Prisoner of Azkaban");
        harryPotterPrisoner.setIsbn("978-1408855676");
        harryPotterPrisoner.setAuthor(rowling);

        Book oneHundredYearsOfSolitude = new Book();
        oneHundredYearsOfSolitude.setTitle("One Hundred Years of Solitude");
        oneHundredYearsOfSolitude.setIsbn("9780241968581");
        oneHundredYearsOfSolitude.setAuthor(marquez);

        authorRepository.save(shakespeare);
        authorRepository.save(austen);
        authorRepository.save(orwell);
        authorRepository.save(rowling);
        authorRepository.save(marquez);

        bookRepository.save(hamlet);
        bookRepository.save(sevenNovels);
        bookRepository.save(nineteenEightyFour);
        bookRepository.save(harryPotter);
        bookRepository.save(oneHundredYearsOfSolitude);
        bookRepository.save(harryPotterChamber);
        bookRepository.save(harryPotterPrisoner);

    }
}
