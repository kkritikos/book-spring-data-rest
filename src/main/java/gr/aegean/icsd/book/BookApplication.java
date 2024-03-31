package gr.aegean.icsd.book;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner mappingDemo(AuthorRepository authorRepository,
                                         BookRepository bookRepository) {
        return args -> {

            // create a student
            Author author = new Author.Builder("FName1", "LName").build();

            // save the student
            authorRepository.save(author);

            // create three courses
            Book book1 = new Book.Builder("978 0 596 52068 4","Title1","Publisher1").build();
            Book book2 = new Book.Builder("978 0 596 52068 2","Title1","Publisher1").build();
            Book book3 = new Book.Builder("978 0 596 52068 3","Title2","Publisher2").build();

            // save courses
            bookRepository.saveAll(Arrays.asList(book1, book2, book3));

            // add courses to the student
            author.getBooks().addAll(Arrays.asList(book1, book2, book3));

            // update the student
            authorRepository.save(author);
        };
    }
}
