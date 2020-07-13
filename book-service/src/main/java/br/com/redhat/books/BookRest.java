package br.com.redhat.books;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRest {

	@Inject
	BookService service;
	
	@GET
	public Response books() {
		return Response.ok(service.all()).build();
	}

	@GET
	@Path("/{author}")
	public Response booksFromAuthor(@PathParam("author") String authorId) {
		List<Book> booksFromAuthor = service.fromAuthor(authorId);
		if(booksFromAuthor.isEmpty())
			return Response.status(Status.NOT_FOUND).build();

		return Response.ok(booksFromAuthor).build();
	}
}
