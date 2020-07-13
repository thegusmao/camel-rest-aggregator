package br.com.redhat.books;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BookService {

	public List<Book> all() {
		return Arrays.asList(
			new Book("The shining", "", "1"),
			new Book("IT", "", "1"),
			new Book("Harry Potter and the Goblet of Fire", "", "2"),
			new Book("Fantastic Beasts and Where to Find Them", "", "2"),
			new Book("The Hobbit", "", "3"),
			new Book("The Lord of the Rings", "", "3"),
			new Book("Beowulf", "", "3"),
			new Book("Murder on the Orient Express", "", "4"),
			new Book("Death on the Nile", "", "4"),
			new Book("And Then There Were None", "", "4"),
			new Book("The A.B.C. Murders", "", "4")
		);
	}

	public List<Book> fromAuthor(String authorId) {
		return this.all().stream().filter(b -> b.getAuthorId().equals(authorId)).collect(Collectors.toList());
	}
}
