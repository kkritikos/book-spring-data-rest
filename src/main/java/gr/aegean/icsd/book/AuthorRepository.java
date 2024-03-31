package gr.aegean.icsd.book;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends 
	PagingAndSortingRepository<Author, Long>, CrudRepository<Author, Long> {
	List<Book> findByFirstName(@Param("firstName") String firstName, Pageable p);
	List<Book> findByLastName(@Param("lastName") String lastName, Pageable p);
	List<Book> findByNationality(@Param("nationality") String nationality, Pageable p);
	List<Book> findByFirstNameAndLastName(
		@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable p);
}

