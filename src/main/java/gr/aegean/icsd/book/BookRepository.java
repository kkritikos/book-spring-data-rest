package gr.aegean.icsd.book;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends 
	PagingAndSortingRepository<Book, String>, CrudRepository<Book, String> {
	List<Book> findByTitle(@Param("title") String title, Pageable p);
	List<Book> findByPublisher(@Param("publisher") String publisher, Pageable p);
	List<Book> findByAuthors(@Param("author") String author, Pageable p);
	List<Book> findByPublisherAndAuthors(
		@Param("publisher") String publisher, @Param("author") String author, Pageable p);
}

