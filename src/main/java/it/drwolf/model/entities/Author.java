package it.drwolf.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends PanacheEntity {
	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = Book_.AUTHOR)
	@OrderBy(Book_.PUBLICATION)
	@JsonIgnore
	private Set<Book> books = new HashSet<>();

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
