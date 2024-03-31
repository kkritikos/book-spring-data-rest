package gr.aegean.icsd.book;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Basic(optional = false)
	@NotBlank(message = "First name cannot be blank!")
	private String firstName;
	@Basic(optional = false)
	@NotBlank(message = "Last name cannot be blank!")
	private String lastName;
	private String nationality;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", 
      joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "book_id", 
      referencedColumnName = "isbn"))
    private List<Book> books = new ArrayList<Book>();
	
	public Author(){}
	
	private Author(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		setNationality(builder.nationality);
		setBooks(builder.books);
	}
	
	public static class Builder{
        private String firstName = null;
        private String lastName = null;
        private String nationality = null;
        private List<Book> books = new ArrayList<Book>();
        
        private static void checkSingleValue(String value, String message) throws IllegalArgumentException{
        	if (value == null || value.trim().equals("")) throw new IllegalArgumentException(message + " cannot be null or empty");
        }
        
        public Builder(String firstName, String lastName) throws IllegalArgumentException{
        	checkSingleValue(firstName, "First Name");
        	checkSingleValue(lastName, "Last Name");
        	
        	this.firstName = firstName;
        	this.lastName = lastName;
        }
        
        public Builder nationality(String value) {
        	this.nationality = value;
        	return this;
        }
        
        public Builder books(List<Book> books) {
        	this.books = books;
        	return this;
        }
        
        public Author build() {
        	return new Author(this);
        }
    }

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException{
		Builder.checkSingleValue(firstName, "First Name");
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException{
		Builder.checkSingleValue(lastName, "Last Name");
		this.lastName = lastName;
	}
	
	public String toString(){
    	return "Person(" + firstName + ", " + lastName + ")";
    }
	
	public boolean equals(Object o) {
		if (o instanceof Author) {
			Author p = (Author)o;
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) return true;
		}
		
		return false;
	}
}
