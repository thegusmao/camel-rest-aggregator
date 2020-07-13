package br.com.redhat.authors;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AuthorService {

	public List<Author> all() {
		return Arrays.asList(
			new Author("1", "stephen-king", "Stephen King"),
			new Author("2", "jk-rowling", "J. K. Rowling"),
			new Author("3", "jr-tolkien", "J. R. Tolkien"),
			new Author("4", "agatha-christie", "Agatha Christie"),
			new Author("5", "jane-austin", "Jane Austin")
		);
	}
	
	public Author byName(String name) {
		return this.all().stream()
					.filter(a -> a.getName().equals(name))
						.findAny()
						.orElse(null);
	}

	public Author byId(String id) {
		return this.all().stream()
					.filter(a -> a.getId().equals(id))
						.findAny()
						.orElse(null);
	}
	
}
