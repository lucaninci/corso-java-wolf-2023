package it.drwolf.services;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.runtime.StartupEvent;
import it.drwolf.model.entities.Author;
import it.drwolf.model.entities.Book;
import it.drwolf.repositories.AuthorRepository;
import it.drwolf.repositories.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.stream.IntStream;

@ApplicationScoped
public class Startup {

	@Inject
	BookRepository bookRepository;

	@Inject
	AuthorRepository authorRepository;
	public void onStart(@Observes StartupEvent event){
		QuarkusTransaction.requiringNew().run(()->{
				if (bookRepository.count()==0){
					Author a = new Author();
					a.setFirstName("Nome");
					a.setLastName("Cognome");
					authorRepository.persist(a);
					IntStream.range(0,100).forEach(i->{
						Book b = new Book();
						b.setTitle("Book %d".formatted(i));
						b.setPublication(new Date());
						b.setAuthor(a);
						bookRepository.persist(b);
					});

				}

		});
	}
}
