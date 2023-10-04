package it.drwolf.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.drwolf.model.entities.Author;
import it.drwolf.model.entities.Book;
import it.drwolf.model.entities.Book_;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

	public List<Book> getByAuthor(Author author){
		return  find("%s=?1".formatted(Book_.AUTHOR) ,author).list();
	}
}
